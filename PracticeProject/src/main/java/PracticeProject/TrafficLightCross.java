package PracticeProject;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by lyoumi on 05.12.2016.
 */
class TrafficLightCross extends JFrame{
    private JButton button;                                                                                             //кнопка для создания машин (тех самых квадратиков)))
    private JButton buttonU = new JButton();                                                                            //кнопка для включения светофора
    private JButton buttonInfo = new JButton();
    private JLabel waitingList = new JLabel();
    private JLabel runningList = new JLabel();
    private JLabel summaryList = new JLabel();

    private BlockingDeque<Thread> threads = new LinkedBlockingDeque<>();                                                //наши потоки-машины
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(500);               //пул на который мы будем вешать наши потоки-машины

    private Random random = new Random();

    private int positionWayVertical = resizebleH()/2;                                                                   //точка, закрепленная за машиной по вертикали вниз
    private int positionReverseWayVertical = resizebleH()/2;                                                            //точка, закрепленная за машиной по вертикали вверх
    private int positionWayHorizontal = resizebleW()/2;                                                                 //точка, закрепленная за машиной по горизонтали вправо
    private int positionReverseWayHorizontal = resizebleW()/2;                                                          //точка, закрепленная за машиной по горизонтали влево

    //набор булек определяющих возможность передвижения вперед
    private boolean firstTime = true;
    private boolean firstStart = true;
    private boolean canTraffic = false;
    private boolean trafficCross = true;

//набор окончен
    private int timeOut = 3000;                                                                                         //таймаут для создания новых потоков
    private int countDead;


    TrafficLightCross(String s){                                                                                        //конструктор класса
        super(s);
        super.setVisible(true);
        super.setBackground(Color.BLACK);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setSize(400, 400);
        super.setResizable(true);

        setLayout(new GroupLayout(getContentPane()));
        button = new JButton();

        button.setBounds(10,10, 20, 20);
        buttonU.setBounds(30, 10, 20, 20);
        buttonInfo.setBounds(50, 10, 20,20);
        add(button);
        add(buttonU);
        add(buttonInfo);

        waitingList.setBounds((resizebleW()-100),10, 70,20);
        runningList.setBounds((resizebleW()-100),40, 70,20);
        summaryList.setBounds((resizebleW()-100),70, 70,20);
        add(waitingList);
        add(runningList);
        add(summaryList);



//        Runner runner = new Runner();
        Handler handler = new Handler();
        button.addActionListener(handler);
        buttonU.addActionListener(handler);
        buttonInfo.addActionListener(handler);
    }                                                                                    //конструктор класса


    public class Handler implements ActionListener {                                                                    //класс-слушатель
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==button) {
                Redraw redraw = new Redraw();
                if ((canTraffic)||(firstStart)){
                    timeOut += 3000;
                    threads.addFirst(new Thread(redraw));
                    executorService.schedule(threads.peekFirst(), timeOut, TimeUnit.MILLISECONDS);
                    System.out.println(threads.size());
                }
            }
            if (e.getSource()==buttonU){
                TrafficLight light = new TrafficLight();
                Thread thread = new Thread(light);
                thread.start();
            }
            if (e.getSource()==buttonInfo){
                UpdateScreen screen = new UpdateScreen();
                Thread threadInfo = new Thread(screen);
                threadInfo.start();
            }
        }
    }


    public class Redraw implements Runnable{                                                                            //класс для рисования фигур

        Lock lock = new ReentrantLock();
        int speed = random.nextInt(20);

        @Override
        public void run() {                                                                                             //отправляет созданный поток на рисование
            lock.lock();
            repaint();
        }

        void repaint(){                                                                                                 //случайным образом выбранная фигура отрисовывается на экране
            timeOut -= 3000;
            canTraffic = false;
            firstStart = false;
            lock.lock();
            Graphics g = getGraphics();
            Graphics2D d = (Graphics2D) g;
            d.setBackground(Color.WHITE);

            int length = 30;                                                                                            //размеры фигуры
            int choice = random.nextInt(2);

            switch (choice){
                case 0:{                                                                                                //рисуем первый квадрат
                    int yO = (resizebleH()/2+20);
                    int xO = 0;
                    boolean bolX = true;                                                                                //направление

                    d.setPaint(Color.GREEN);

                    while (true) {                                                                                      //в цикле изменяем координаты

                        if(xO==-30) bolX = true;
                        if(xO==(resizebleW())) {
                            bolX = false;
                        }

                        xO = change(bolX, xO);

                        d.setPaint(Color.blue);                                                                         //цвет
                        d.fillRect(xO, yO, length, length);
                        d.setPaint(Color.BLACK);
                        d.drawRect(xO, yO, length+1, length+1);

                        if (bolX){                                                                                      //проверка можно ли рисовать дальше

                            if (xO > resizebleW()/3) canTraffic = true;
                            yO = (resizebleH()/2+20);
                            if (xO < resizebleW()/3) canTraffic = false;
                            if (collisionHorizontal(xO, length, true)) {
                                countDead++;
                                while (!trafficCross){
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                countDead--;
                            }
                        } else  {
                            yO = (resizebleH()/2-20);
                            if (collisionHorizontal(xO, length, false)) {
                                countDead++;
                                while (!trafficCross){
                                    try {
                                        Thread.sleep(20);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                countDead--;
                            }
                        }

                        try {
                            Thread.sleep(speed+3);                                                                     //задаем скорость передвижения в пилисекундах, на которые while будет останавливаться
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if ((firstTime)&&(xO == 25)){
                            lock.unlock();
                            firstTime = false;
                        }
                    }
                }

                case 1:{                                                                                                //по аналогии только те, что двигаются о вертикали
                    int xO = (resizebleW()/2 - 20);
                    int yO = 0;
                    boolean bolY = random.nextBoolean();

                    d.setPaint(Color.GREEN);

                    while (true) {
                        if(yO==0) bolY = true;
                        if(yO==(resizebleH())) {
                            bolY = false;
                        }
                        yO = change(bolY, yO);

                        d.setPaint(Color.blue);
                        d.fillRect(xO, yO, length, length);
                        d.setPaint(Color.BLACK);
                        d.drawRect(xO, yO, length+1, length+1);

                        if (bolY){
                            if (yO > resizebleH()/3) canTraffic = true;
                            if (yO < resizebleH()/3) canTraffic = false;
                            xO = (resizebleW()/2+20);
                            if (collisionVertical(yO, length, true)) {
                                countDead++;
                                while (trafficCross){
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                countDead--;
                            }
                        } else  {
                            xO = (resizebleW()/2-20);
                            if (collisionVertical(yO, length, false)) {
                                countDead++;
                                while (trafficCross) {
                                    try {
                                        Thread.sleep(20);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                countDead--;
                            }
                        }

                        try {
                            Thread.sleep(speed+3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if ((firstTime)&&(xO == 25)){
                            lock.unlock();
                            firstTime = false;
                        }
                    }
                }
            }
        }

        synchronized int change(boolean b, int temp){                                                                   //метод для смены направления при достижения границ и для изменения положения
            if (b) {
                temp++;
            } else {
                temp--;
            }
            return temp;
        }
    }

    private synchronized boolean collisionHorizontal(int x, int l, Boolean road){                                       //метод для для того, чтоб машины не наезжали друг на друга
        boolean b = false;
        if (road){
            if ((x==(resizebleW()/2-70))||(x==(positionWayHorizontal -l-5))) {
                b = true;
                positionWayHorizontal = x;
            }
        } else {
            if ((x==(resizebleW()/2+50))||(x==(positionReverseWayHorizontal +l+5))) {
                b = true;
                positionReverseWayHorizontal = x;
            }
        }
        return b;
    }

    private synchronized boolean collisionVertical(int x, int l, Boolean road){                                         //метод для для того, чтоб машины не наезжали друг на друга
        boolean b = false;
        if (road){
            if ((x==(resizebleH()/2-100))||(x==(positionWayVertical -l-5))) {
                b = true;
                positionWayVertical = x;
            }
        } else {
            if ((x==(resizebleH()/2+100))||(x==(positionReverseWayVertical +l+5))) {
                b = true;
                positionReverseWayVertical = x;
            }
        }
        return b;
    }

    class TrafficLight implements Runnable{                                                                             //класс в котором меняем нашу цвет светофора

        Graphics2D graphics2D = (Graphics2D) getGraphics();

        @Override
        public void run() {
            infinityCycle();
        }

        private void infinityCycle(){
            while (true){
                trafficCross = !trafficCross;
                if (trafficCross) {
                    graphics2D.setPaint(Color.RED);
                    graphics2D.fillOval((resizebleW()-60), 40, 20, 20);
                    graphics2D.setPaint(Color.black);
                    graphics2D.fillOval((resizebleW()-60), 60, 20, 20);

                    graphics2D.setPaint(Color.black);
                    graphics2D.fillOval((resizebleW()-30), 40, 20, 20);
                    graphics2D.setPaint(Color.green);
                    graphics2D.fillOval((resizebleW()-30), 60, 20, 20);
                } else {
                    graphics2D.setPaint(Color.black);
                    graphics2D.fillOval((resizebleW()-60), 40, 20, 20);
                    graphics2D.setPaint(Color.green);
                    graphics2D.fillOval((resizebleW()-60), 60, 20, 20);

                    graphics2D.setPaint(Color.red);
                    graphics2D.fillOval((resizebleW()-30), 40, 20, 20);
                    graphics2D.setPaint(Color.black);
                    graphics2D.fillOval((resizebleW()-30), 60, 20, 20);
                }
                System.out.println(trafficCross);
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class UpdateScreen implements  Runnable{
        @Override
        public void run() {
            updateScreenInfo();
        }

        private void updateScreenInfo(){
            while (true){
//                i++;
                waitingList.setText(String.valueOf("Waiting: " + countDead));
                runningList.setText(String.valueOf("Running: " + (threads.size()-countDead)));
                summaryList.setText(String.valueOf("Summary: " + threads.size()));
                waitingList.update(getGraphics());
                runningList.update(getGraphics());
                summaryList.update(getGraphics());

            }
        }
    }

    private int resizebleW(){                                                                                                   //метод пересчета ширины
        return (int) super.getSize().getWidth();
    }

    private int resizebleH(){                                                                                                   //метод пересчета высоты
        return (int) super.getSize().getHeight();
    }

}
