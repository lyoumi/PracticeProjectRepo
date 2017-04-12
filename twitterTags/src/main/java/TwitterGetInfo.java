import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by lyoumi on 20.03.2017.
 */

/**Данная программа собирает статистику твиттера по различным странам в виде топа хэштегов
 и по аналогии делает запросы по ним раз в константу времени. Из полученного JSON вытаскиваем
 id твитов и формируем массивы с уникальными идентификаторами. На экран выводим информацию в виде
 движущихся кнопок с наименованием хєштега, при нажатии на которую выводится окно с количеством твитов на заданную тематику,
 а также кнопкой, при нажатии откроется браузер с новой вкладкой, на которой пользователь сможет увидеть
 твиты по данному хэштэгу*/

public class TwitterGetInfo {

    public ComboBox boxCountry;
    public Button buttonProcess;
    public AnchorPane absolutePane;
    public Button aboutDev;
    public Button listOf;
    public SplitPane splitPane;

    private ObservableList<String> countryList = FXCollections.observableArrayList(
            "Ukraine", "USA", "Japan", "Germany", "World"
    );

    private ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
    private Twitter twitter;

    private List<String> names;
    private int country;
    private List<List<List<Long>>> listOfTweetsId = new ArrayList<>();                                                  //список id нвших твитов по тегам
    /**
     * Для реализации выбран трехмерный массив, в котором первый массив - массив по странам, который
     * хранит массив по тегам, которые в свою очередь хранят id твитов.
     */

    private List <List> trendNames = new ArrayList();                                                                   //лист хэштегов
    private Trend[] trend;                                                                                              //массив хэщтегов
    private double height;                                                                                              //высота pane
    private double width;                                                                                               //ширина pane
    private Random random = new Random();
    private List<List<Button>> buttonList = new ArrayList<>();                                                          //массив массивов кнопок

    private List<List<Integer>> listX = new ArrayList<>();                                                              //массив массивов координат х
    private List<List<Integer>> listY = new ArrayList<>();                                                              //массив массивов координат у

    private List<List<Boolean>> directionListX = new ArrayList<>();                                                     //массив массивов булек, определяющих направление по Х
    private List<List<Boolean>> directionListY = new ArrayList<>();                                                     //массив массивов булек, определяющх направление по У

    private int i = 0;
    private int lastButton = -1;                                                                                         //индекс последнего добавленного массива кнопок (для класса обработчика)

    private int lastButtonDraw = -1;                                                                                     //индекс последнего добавленного массива кнопок (для класса визуализатора)

    @FXML
    public void initialize() {                                                                                          //метод initialize, в котором мы сетим в комбо-бокс значения
        boxCountry.setValue("Choose country...");
        boxCountry.setItems(countryList);

        configurationBuilder.setDaemonEnabled(true)
                .setOAuthConsumerKey("DDe9Pr6bx5YvXuK2XX6pyQhFu")
                .setOAuthConsumerSecret("URn6NuOT822ViLUDTvdRM6Jthss0exL24qiyu0qkcavP1a8Y6M")
                .setOAuthAccessToken("599464837-4P4yXM1c5ndrIo922Xv4pczbozr7GtQoP67DS4fP")
                .setOAuthAccessTokenSecret("CYbgMSf6bpTVjjZNAAJzWt4BWc14gm4Gn0ozB2oWwktZt");

        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
    }

    private int getLastButtonDraw(){
        lastButtonDraw = lastButtonDraw + 1;
        return lastButtonDraw;
    }

    private int getLastButton(){
        lastButton = lastButton + 1;
        return lastButton;
    }

    public class Draw implements Runnable {

        @Override
        public void run() {

            runResizeble();                                                                                             //запускаем метод обновляющий значения высоты и ширины

            /**
             * создаем внутренние листы
             */
            List<Integer> innerListX = new ArrayList<>();
            listX.add(innerListX);
            List<Integer> innerListY = new ArrayList<>();
            listY.add(innerListY);
            List<Boolean> innerDirectionListX = new ArrayList<>();
            directionListX.add(innerDirectionListX);
            List<Boolean> innerDirectionListY = new ArrayList<>();
            directionListY.add(innerDirectionListY);

            /**
             * Добавляем кнопки на экран в рандомно инициализированных координатах и направлениях
             * Также объявляем слушатель для кнопки
             */
            int lastIndex = getLastButtonDraw();

            for (int j = 0; j < listOfTweetsId.get(lastIndex).size(); j++) {
                listX.get(lastIndex).add(random.nextInt(333)+200);
                listY.get(lastIndex).add(random.nextInt(333)+200);
                directionListX.get(lastIndex).add(random.nextBoolean());
                directionListY.get(lastIndex).add(random.nextBoolean());
                buttonList.get(lastIndex).get(j).setLayoutX(listX.get(lastIndex).get(j));
                buttonList.get(lastIndex).get(j).setLayoutY(listY.get(lastIndex).get(j));

                int finalJ = j;
                Platform.runLater((() -> absolutePane.getChildren().add(buttonList.get(lastIndex).get(finalJ))));
                buttonList.get(lastIndex).get(j).setOnAction(((ActionEvent event) -> {
                    Stage dialog = new Stage();
                    dialog.initStyle(StageStyle.DECORATED);
                    Button buttonSearch = new Button("Go");
                    buttonSearch.setStyle("-fx-background-color: #1d1d1d");
                    buttonSearch.setStyle("-fx-text-fill: #2f4b8f");
                    buttonSearch.setOnAction(((ActionEvent event1) -> {
                        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                            try {
                                desktop.browse(new URI("https://twitter.com/hashtag/" +
                                        trendNames.get(finalJ).toString().replaceAll("#", "")));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }));

                    Text text = (new Text(40, 40, "Size: " + listOfTweetsId.get(lastIndex).get(finalJ).size()));
                    text.setStyle("-fx-text-fill: #3c7fb1");
                    Group group = new Group(text, buttonSearch);
                    group.setStyle("-fx-background-color: #1d1d1d");
                    Scene scene = new Scene(group);
                    dialog.setScene(scene);
                    dialog.setResizable(false);
                    dialog.show();
                }));
            }
            moving(buttonList.get(lastIndex), lastIndex);

        }

        /**
         * Метод меняющий определяющий направление и изменение координат
         * @param movingList
         * @param position
         */

        private synchronized void moving(List<Button> movingList, int position){
            Platform.setImplicitExit(false);
            while (true) {
                Platform.runLater(() -> {
                    for (int j = 0; j < listOfTweetsId.get(position).size(); j++) {
                        movingList.get(j).setLayoutX(listX.get(position).get(j));
                        movingList.get(j).setLayoutY(listY.get(position).get(j));
                        int tempWidth = (int) (movingList.get(j).getWidth());
                        int tempHeight = (int) (movingList.get(j).getHeight());

                        if (listX.get(position).get(j) == 0) {
                            directionListX.get(position).set(j, true);
                        }

                        if (listY.get(position).get(j) == 0) {
                            directionListY.get(position).set(j, true);
                        }

                        if (listX.get(position).get(j) == (width-tempWidth)) {
                            directionListX.get(position).set(j, false);
                        }

                        if (listY.get(position).get(j) == (height-tempHeight)) {
                            directionListY.get(position).set(j, false);
                        }

                        listX.get(position).set(j, change(directionListX.get(position).get(j), listX.get(position).get(j)));
                        listY.get(position).set(j, change(directionListY.get(position).get(j), listY.get(position).get(j)));
                    }
                });

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * Метод меняющий координаты
         * @param b
         * @param temp
         * @return
         */

        private int change(boolean b, int temp) {
            if (b) ++temp;
            else --temp;
            return temp;
        }

        /**
         * Метод записывающий значение высоты и ширины в переменные
         */

        void runResizeble() {
            new Thread(() -> {
                while (true) {
                    width = absolutePane.getWidth();
                    height = absolutePane.getHeight();
                }
            }).start();
        }

    }


    public void about(){
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        Text text = (new Text(40, 40, "Данная программа собирает статистику твиттера по различным странам в виде топа хэштегов  \n" +
                " и по аналогии делает запросы по ним раз в константу времени. На экран выводим информацию в виде  \n" +
                " движущихся кнопок с наименованием хєштега, при нажатии на которую выводится окно с количеством твитов на заданную тематику,  \n" +
                " а также кнопкой, при нажатии откроется браузер с новой вкладкой, на которой пользователь сможет увидеть  \n" +
                " твиты по данному хэштэгу.  \n" +
                "Development by Artjom Nikulin\n" +
                "Contact email: it.chubaka@gmail.com\n"));
        text.setStyle("-fx-text-fill: #3c7fb1");
        text.setTextAlignment(TextAlignment.CENTER);
        Group group = new Group(text);
        group.setStyle("-fx-background-color: #1d1d1d");
        Scene scene = new Scene(group);
        dialog.setScene(scene);
        dialog.setResizable(false);
        dialog.show();
    }

    public void listOfTweets(){
        Stage dialogList = new Stage();
        dialogList.initStyle(StageStyle.UTILITY);
        Text text = new Text();
        String temp = "\n";

        for (int j = 0; j < listOfTweetsId.size(); j++) {
            temp += (trendNames.get(j).toString() + ": " + listOfTweetsId.get(j).size() + "\n");
        }

        text.setText(temp);
        text.setStyle("-fx-text-fill: #3c7fb1");
        text.setTextAlignment(TextAlignment.CENTER);
        Group group = new Group(text);
        group.setStyle("-fx-background-color: #1d1d1d");
        Scene scene = new Scene(group);
        dialogList.setScene(scene);
        dialogList.setResizable(false);
        dialogList.show();
    }

    /**
     * Метод-слушатель для кнопки GO
     *
     */

    public void startCalculate() {

        if (boxCountry.getValue().equals("Ukraine")) country = 23424976;
        if (boxCountry.getValue().equals("USA")) country = 23424977;
        if (boxCountry.getValue().equals("Japan")) country = 23424856;
        if (boxCountry.getValue().equals("Germany")) country = 23424829;
        if (boxCountry.getValue().equals("World")) country = 1;

        Trends trends = null;
        try {
            trends = twitter.getPlaceTrends(country);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        trend = trends.getTrends();

        List<Button> innerButtonList = new ArrayList<>();
        buttonList.add(innerButtonList);
        List<List<Long>> innerCountryList = new ArrayList<>();
        listOfTweetsId.add(innerCountryList);
        List innerTrendNames = new ArrayList();
        trendNames.add(innerTrendNames);

        int lastIndex = getLastButton();

        for (Trend t :
                trend) {
            buttonList.get(lastIndex).add(new Button(t.getName()));
            List<Long> innerTrendList = new ArrayList<>();
            listOfTweetsId.get(lastIndex).add(innerTrendList);
            trendNames.get(lastIndex).add(t.getName());
        }

        Draw draw = new Draw();
        Thread graphicsThread = new Thread(draw);
        graphicsThread.start();

        System.out.println(trend);
        GetInfo getInfo = new GetInfo();
        Thread calculateThread = new Thread(getInfo);
        calculateThread.start();
    }

    /**
     * Данный класс занимается отправкой запросов и обработкой результатов
     */
    public class GetInfo implements Runnable {

        @Override
        public void run() {

            int currentPosition = lastButton;
            while (true) {

                for (int j = 0; j < trendNames.get(currentPosition).size(); j++) {
                    Query query = new Query(trendNames.get(currentPosition).get(j).toString());
                    query.setCount(100);
                    QueryResult result;
                    try {
                        result = twitter.search(query);
                        calculateSummaryOfTweets(result, i, currentPosition);

                        if (i < (listOfTweetsId.get(currentPosition).size() - 1)) ++i;
                        else i = 0;

                    } catch (TwitterException e) {
                        Platform.runLater((() -> {
                            Stage dialog = new Stage();
                            dialog.initStyle(StageStyle.UTILITY);
                            Text alertText = new Text("\nThe connection is lost. Try again in two minutes...");
                            alertText.setStyle("-fx-text-fill: #ecebe9");
                            alertText.setStyle("-fx-animated: true");
                            Group group = new Group(alertText);
                            group.setStyle("-fx-background-color: #1d1d1d");
                            Scene scene = new Scene(group);
                            dialog.setScene(scene);
                            dialog.setResizable(false);
                            dialog.show();
                        }));
                        try {
                            Thread.sleep(120000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

                System.out.println(trendNames.get(currentPosition));

                for (int j = 0; j < listOfTweetsId.get(currentPosition).size(); j++) {
                    System.out.print(trendNames.get(currentPosition).get(j) + ": ");
                    System.out.println(listOfTweetsId.get(currentPosition).get(j).size());
                }


                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * Данный метод сравнивает полученный id твита и српанивает его с хранимыми в массиве. Если данного id
         * не найдено - он добавляется в массив. На вход принимает результат запроса, номер массива страны, а также номер
         * массива твитов
         *
         * @param buffer
         * @param k
         * @param pos
         */

        private synchronized void calculateSummaryOfTweets(QueryResult buffer, int k, Integer pos) {

            for (Status tweet : buffer.getTweets()) {
                if (!(listOfTweetsId.get(pos).get(k).contains(tweet.getId()))) listOfTweetsId.get(pos).get(k).add(tweet.getId());
            }
        }
    }
}