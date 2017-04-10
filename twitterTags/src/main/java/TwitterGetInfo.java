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


public class TwitterGetInfo {

    public ComboBox boxCountry;
    public Button buttonProcess;
    public HBox hBox;
    public AnchorPane absolutePane;
    public Button aboutDev;
    public Button listOf;
    public SplitPane splitPane;
    private ObservableList<String> countryList = FXCollections.observableArrayList(
            "Ukraine", "USA", "Japan", "Germany", "World"
    );

    private List<String> names;
    private int country;
    private List<List<Long>> listOfTweetsId = new ArrayList<>();                                                        //список id нвших твитов по тегам

    private List trendNames = new ArrayList();
    private Trend[] trend;
    private double height;
    private double width;
    private Random random = new Random();
    private List<Button> buttonList = new ArrayList<>();
    private Stage primaryStage;

    private int i = 0;

    @FXML
    public void initialize() {
        boxCountry.setValue("Choose country...");
        boxCountry.setItems(countryList);
    }

    public class Draw implements Runnable {

        private List<Integer> listX = new ArrayList<>();
        private List<Integer> listY = new ArrayList<>();
        private List<Boolean> directionListX = new ArrayList<>();
        private List<Boolean> directionListY = new ArrayList<>();

        @Override
        public void run() {

//            Platform.runLater(()->{
//                absolutePane.getChildren().clear();
//                absolutePane.getChildren().add(hBox);
//                initialize();
//            });

            runResizeble();

            for (int j = 0; j < listOfTweetsId.size(); j++) {
                int w = random.nextInt(333)+200;
                int h = random.nextInt(333)+200;
                listX.add(w);
                listY.add(h);
                directionListX.add(random.nextBoolean());
                directionListY.add(random.nextBoolean());
                buttonList.get(j).setLayoutX(listX.get(j));
                buttonList.get(j).setLayoutY(listY.get(j));

                int finalJ = j;
                Platform.runLater((() -> absolutePane.getChildren().add(buttonList.get(finalJ))));
                buttonList.get(j).setOnAction(((ActionEvent event) -> {
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

                    Text text = (new Text(40, 40, "Size: " + listOfTweetsId.get(finalJ).size()));
                    text.setStyle("-fx-text-fill: #3c7fb1");
                    Group group = new Group(text, buttonSearch);
                    group.setStyle("-fx-background-color: #1d1d1d");
                    Scene scene = new Scene(group);
                    dialog.setScene(scene);
                    dialog.setResizable(false);
                    dialog.show();
                }));
            }
            moving();
        }

        private void moving(){
            Platform.setImplicitExit(false);
            while (true) {
                if (buttonList.isEmpty()) break;
                Platform.runLater(() -> {
                    for (int j = 0; j < listOfTweetsId.size(); j++) {
                        if (buttonList.isEmpty()) break;
                        buttonList.get(j).setLayoutX(listX.get(j));
                        buttonList.get(j).setLayoutY(listY.get(j));
                        int tempWidth = (int) (buttonList.get(j).getWidth());
                        int tempHeight = (int) (buttonList.get(j).getHeight());

                        final int painHeight = (int) absolutePane.getHeight();
                        final int painWidth = (int) absolutePane.getWidth();

                        if (listX.get(j) == 0) {
                            directionListX.set(j, true);
                        }

                        if (listY.get(j) == 0) {
                            directionListY.set(j, true);
                        }

                        if (listX.get(j) == (width-tempWidth)) {
                            directionListX.set(j, false);
                        }

                        if (listY.get(j) == (height-tempHeight)) {
                            directionListY.set(j, false);
                        }

                        listX.set(j, change(directionListX.get(j), listX.get(j)));
                        listY.set(j, change(directionListY.get(j), listY.get(j)));
                    }
                });

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private int change(boolean b, int temp) {
            if (b) ++temp;
            else --temp;
            return temp;
        }

        void runResizeble() {
            new Thread(() -> {
                while (true) {
                    width = absolutePane.getWidth();
                    height = absolutePane.getHeight();
                }
            }).start();
        }

    }

    public void startCalculate() {

        if (boxCountry.getValue().equals("Ukraine")) country = 23424976;
        if (boxCountry.getValue().equals("USA")) country = 23424977;
        if (boxCountry.getValue().equals("Japan")) country = 23424856;
        if (boxCountry.getValue().equals("Germany")) country = 23424829;
        if (boxCountry.getValue().equals("World")) country = 1;

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDaemonEnabled(true)
                .setOAuthConsumerKey("DDe9Pr6bx5YvXuK2XX6pyQhFu")
                .setOAuthConsumerSecret("URn6NuOT822ViLUDTvdRM6Jthss0exL24qiyu0qkcavP1a8Y6M")
                .setOAuthAccessToken("599464837-4P4yXM1c5ndrIo922Xv4pczbozr7GtQoP67DS4fP")
                .setOAuthAccessTokenSecret("CYbgMSf6bpTVjjZNAAJzWt4BWc14gm4Gn0ozB2oWwktZt");

        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter4j.Twitter twitter = twitterFactory.getInstance();


        Trends trends = null;
        try {
            trends = twitter.getPlaceTrends(country);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        trend = trends.getTrends();

        buttonList.clear();
        for (Trend t :
                trend) {
            buttonList.add(new Button(t.getName()));
            List<Long> innerList = new ArrayList<>();
            listOfTweetsId.add(innerList);
        }

        Draw draw = new Draw();
        Thread graphicsThread = new Thread(draw);
        graphicsThread.start();

        System.out.println(trend);
        GetInfo getInfo = new GetInfo();
        Thread calculateThread = new Thread(getInfo);
        calculateThread.start();
    }

    public void listOfTweets(){
        List<List<Long>> sortedList = new ArrayList<>(bsort(listOfTweetsId));
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

    public List<List<Long>> bsort(List<List<Long>> a) {
        boolean t = true;
        names = new ArrayList<>(trendNames);
        while (t == true) {
            t = false;
            for (int i = 1; i < a.size(); i++)                                                                                 //сортируем
            {
                if (a.get(i).size() > a.get(i-1).size())
                {
                    Collections.swap(a, i, (i-1));
                    Collections.swap(names, i, (i-1));
                    t = true;
                }
            }
        }
        return a;
    }

    public void about(){
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        Text text = (new Text(40, 40, "Development by Artjom Nikulin\n" +
                "Contact email: it.chubaka@gmail.com"));
        text.setStyle("-fx-text-fill: #3c7fb1");
        text.setTextAlignment(TextAlignment.CENTER);
        Group group = new Group(text);
        group.setStyle("-fx-background-color: #1d1d1d");
        Scene scene = new Scene(group);
        dialog.setScene(scene);
        dialog.setResizable(false);
        dialog.show();
    }

    public void mouseEntered() {
        if(hBox.isVisible()) hBox.setVisible(false);
        else hBox.setVisible(true);
    }

    public class GetInfo implements Runnable {

        @Override
        public void run() {

            ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
            configurationBuilder.setDaemonEnabled(true)
                    .setOAuthConsumerKey("DDe9Pr6bx5YvXuK2XX6pyQhFu")
                    .setOAuthConsumerSecret("URn6NuOT822ViLUDTvdRM6Jthss0exL24qiyu0qkcavP1a8Y6M")
                    .setOAuthAccessToken("599464837-4P4yXM1c5ndrIo922Xv4pczbozr7GtQoP67DS4fP")
                    .setOAuthAccessTokenSecret("CYbgMSf6bpTVjjZNAAJzWt4BWc14gm4Gn0ozB2oWwktZt");                        //вводим логи

            TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());                           //логинимся под собой
            Twitter twitter = twitterFactory.getInstance();                                                             //создаем инстанс

            while (true) {

                for (Trend t : trend) {
                    Query query = new Query(t.getName());
                    query.setCount(100);
                    QueryResult result = null;
                    try {
                        result = twitter.search(query);
                        calculateSummaryOfTweets(result, i);

                        if (i < (listOfTweetsId.size() - 1)) ++i;
                        else i = 0;

                    } catch (TwitterException e) {
                        Platform.runLater((() -> {
                            Stage dialog = new Stage();
                            dialog.initStyle(StageStyle.UTILITY);
                            Text alertText = new Text("Connection lost, try for 3 minute...");
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
                    trendNames.add(t.getName());
                }

                System.out.println(trendNames);

                for (int j = 0; j < listOfTweetsId.size(); j++) {
                    System.out.print(trendNames.get(j) + ": ");
                    System.out.println(listOfTweetsId.get(j).size());
                }


                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private synchronized void calculateSummaryOfTweets(QueryResult buffer, int k) {

            for (Status tweet : buffer.getTweets()) {
                if (!(listOfTweetsId.get(k).contains(tweet.getId()))) listOfTweetsId.get(i).add(tweet.getId());
            }
        }
    }
}