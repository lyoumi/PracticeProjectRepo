package PracticeProject;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by lyoumi on 05.12.2016.
 */
public class KingOfTheBrush extends JFrame implements Serializable{
    Handler handler = new Handler();
    JButton button;
    JButton buttonU;
    ArrayList<Thread> threads = new ArrayList<>();
    Random random = new Random();



    public KingOfTheBrush(String s){                                                                                    //конструктор класса
        super(s);
        super.setVisible(true);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setSize(400, 400);
        super.setResizable(true);

        setLayout(new GroupLayout(getContentPane()));
        button = new JButton();
        buttonU = new JButton();

        button.setBounds(10,10, 20, 20);
        buttonU.setBounds(30, 10, 20, 20);
        add(button);
        add(buttonU);

        button.addActionListener(handler);
        buttonU.addActionListener(handler);
    }

    public class Handler implements ActionListener {                                                                    //класс-слушатель
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == button){
                Redraw redraw = new Redraw();
                threads.add(new Thread(redraw));
                threads.get(threads.size()-1).start();
                System.out.println(threads.size());
            }

//            if (e.getSource() == buttonU){
//                UpdateScreen screen = new UpdateScreen();
//                Thread thread = new Thread(screen);
//                thread.start();
//            }
        }
    }

//    public class UpdateScreen implements Runnable{
//        @Override
//        public void run() {
//            UScreen();
//        }
//
//        void UScreen(){
//            while (true){
//                update(getGraphics());
//                try {
//                    wait(31);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    public class Redraw implements Runnable{                                                                            //класс для рисования фигур
        

        @Override
        public void run() {                                                                                             //отправляет созданный поток на рисование
            repaint();
        }

        public synchronized void repaint(){                                                                             //случайным образом выбранная фигура отрисовывается на экране
            Graphics g = getGraphics();
            Graphics2D d = (Graphics2D) g;
            d.setBackground(Color.WHITE);

            int a = random.nextInt(100);                                                                         //размеры фигуры
            int choice = random.nextInt(3);

            switch (choice){
                case 0:{                                                                                                //рисуем треуольник (по трем точкам линиями)

                    int x1 = random.nextInt(400);
                    int y1 = random.nextInt(400);
                    int x2 = random.nextInt(400);
                    int x3 = random.nextInt(400);
                    int y2 = random.nextInt(400);
                    int y3 = random.nextInt(400);

                    boolean bolXt = random.nextBoolean();
                    boolean bolYt = random.nextBoolean();

                    while (true) {                                                                                      //отрисовываем при помощи while засыпая на 10 милисекунд
                        d.setPaint(Color.GRAY);
                        d.drawLine(x1, y1, x2, y2);
                        d.drawLine(x2, y2, x3, y3);
                        d.drawLine(x3, y3, x1, y1);

                        if((x1==0)||(x2==0)||(x3==0)) bolXt = true;
                        if((y1==20)||(y2==20)||(y3==20)) bolYt = true;
                        int w = resizebleW();
                        if((x1==(w))||(x2==(w))||(x3==(w))) bolXt = false;
                        int h = resizebleH();
                        if((y1==(h))||(y2==(h))||(y3==(h))) bolYt = false;

                        x1 = change(bolXt, x1);
                        x2 = change(bolXt, x2);
                        x3 = change(bolXt, x3);
                        y1 = change(bolYt, y1);
                        y2 = change(bolYt, y2);
                        y3 = change(bolYt, y3);

                        d.setPaint(Color.blue);
                        d.drawLine(x1, y1, x2, y2);
                        d.drawLine(x2, y2, x3, y3);
                        d.drawLine(x3, y3, x1, y1);

                        try {
                            wait(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                case 1:{                                                                                                //рисуем круг

                    int temp = resizebleW();
                    int xO = random.nextInt(temp-a);
                    temp = resizebleH();
                    int yO = random.nextInt(temp-a);
                    boolean bolX = random.nextBoolean();
                    boolean bolY = random.nextBoolean();

                    d.setPaint(Color.GREEN);

                    while (true) {                                                                                      //по аналогии (смотреть case 0)

                        if(xO==0) bolX = true;
                        if(yO==20) bolY = true;
                        int w = resizebleW();
                        if(xO==(w-a)) bolX = false;
                        int h = resizebleH();
                        if(yO==(h-a)) bolY = false;
                        //reverse(bolX);
                        xO = change(bolX, xO);
                        yO = change(bolY, yO);

                        d.setPaint(Color.yellow);
                        d.fillOval(xO, yO, a, a);
                        d.setPaint(Color.BLACK);
                        d.drawOval(xO, yO, a+1, a+1);
                        try {
                            wait(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                case 2:{                                                                                                //рисуем квадрат

                    int temp = resizebleW();
                    int xk = random.nextInt(temp-a);
                    temp = resizebleH();
                    int yk = random.nextInt(temp-a);
                    d.setPaint(Color.RED);
                    d.fillRect(xk, yk, a, a);
                    boolean bolXk = random.nextBoolean();
                    boolean bolYk = random.nextBoolean();


                    while (true) {                                                                                      //по аналогии (смотреть case 0)

                        if(xk==0) bolXk = true;
                        if(yk==20) bolYk = true;
                        int w = resizebleW();
                        if(xk==(w-a)) bolXk = false;
                        int h = resizebleH();
                        if(yk==(h-a)) bolYk = false;
                        xk = change(bolXk, xk);
                        yk = change(bolYk, yk);

                        d.setPaint(Color.PINK);
                        d.fillRect(xk, yk, a, a);
                        d.setPaint(Color.BLACK);
                        d.drawRect(xk, yk, a+1, a+1);
                        try {
                            wait(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

//                case 3:{
//                        while (true){
//                            updateScreen(g);
//                            try {
//                                wait(511);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
                }
            }
//        synchronized void updateScreen(Graphics g){
//            update(g);
//        }

//        void reverse (boolean revB){
//            IntStream.range(1, threads.size()).forEach((i) -> {
//                if (threads.get(i-1).getState()==threads.get(i).getState()) revB = !revB;
//            });
//        }

        int change(boolean b, int temp){                                                                                //метод для смены направления при достижения границ
            if (b) temp++;
            else temp--;
            return temp;
        }
    }

    private int resizebleW(){                                                                                                   //метод пересчета ширины
        return (int) super.getSize().getWidth();
    }

    private int resizebleH(){                                                                                                   //метод пересчета высоты
        return (int) super.getSize().getHeight();
    }

}
