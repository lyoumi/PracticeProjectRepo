import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.log4j.BasicConfigurator;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.util.*;
import java.util.List;

/**
 * Created by lyoumi on 20.03.2017.
 */

/**Данная программа собирает статистику твиттера по различным странам в виде топа хэштегов
 и по аналогии делает запросы по ним раз в константу времени. Из полученного JSON вытаскиваем
 id твитов и формируем массивы с уникальными идентификаторами. На экран выводим информацию в виде
 движущихся кнопок с наименованием хєштега, при нажатии на которую выводится окно с количеством твитов на заданную тематику,
 а также кнопкой, при нажатии откроется браузер с новой вкладкой, на которой пользователь сможет увидеть
 твиты по данному хэштэгу*/

public class TwitterGetInfo implements Serializable {

    public SplitPane splitPane;
    public AnchorPane absolutePane;
    public ComboBox boxCountry;
    public Button buttonProcess;
    public Button aboutDevButton;
    public Button showFullListButton;
    public Button histogramButton;
    public Button exportToExcelButton;
    public VBox vBox;
    public Button serializeButton;
    public Button deserializeButton;
    public ScrollPane propertyPane;
    public VBox propertyBar;

    private ObservableList<String> countryList = FXCollections.observableArrayList(                                     //лист значений для комбо-бокса
            "Ukraine", "USA", "Japan", "Germany", "World"
    );

    private List<SplitMenuButton> splitMenuButtonsColorsOfCountry = new ArrayList<>();
    private List<List<MenuItem>> buttonsColor = new ArrayList<>();

    private ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

    private Twitter twitter;
    private int country;
    private List<List<List<Long>>> listOfTweetsId = new ArrayList<>();                                                  //список id нвших твитов по тегам и странам
    /**
     * Для реализации выбран трехмерный массив, в котором первый массив - массив по странам, который
     * хранит массив по тегам, которые в свою очередь хранят id твитов.
     */

    private List <List> trendNames = new ArrayList();                                                                   //лист хэштегов
    private double height;                                                                                              //высота pane
    private double width;                                                                                               //ширина pane
    private Random random = new Random();

    private List<List<Button>> buttonList = new ArrayList<>();                                                          //массив массивов кнопок
    private List<List<Integer>> listX = new ArrayList<>();                                                              //массив массивов координат х

    private List<List<Integer>> listY = new ArrayList<>();                                                              //массив массивов координат у
    private List<List<Boolean>> directionListX = new ArrayList<>();                                                     //массив массивов булек, определяющих направление по Х

    private List<List<Boolean>> directionListY = new ArrayList<>();                                                     //массив массивов булек, определяющх направление по У
    private List<String> mapNames = new ArrayList<>();

    private List<Long> mapId = new ArrayList<>();

    private boolean isHidden = false;
    private int lastButton = -1;                                                                                         //индекс последнего добавленного массива кнопок (для класса обработчика)

    private int lastButtonDraw = -1;                                                                                     //индекс последнего добавленного массива кнопок (для класса визуализатора)

    private List<Long> summaryCountOfTweets = new ArrayList<>();

    private boolean bol = false;

    private boolean getBoolean(){
        return bol;
    }

    private boolean setBoolean(){
        bol = true;
        return bol;
    }

    private int incrementLastButtonDraw(){
        lastButtonDraw = lastButtonDraw + 1;
        return lastButtonDraw;
    }

    private int getIncrementLastButton(){
        lastButton = lastButton + 1;
        return lastButton;
    }

    @FXML
    public void initialize() {                                                                                          //метод initialize, в котором мы сетим в комбо-бокс значения
        boxCountry.setValue("Choose country...");
        boxCountry.setItems(countryList);
        splitPane.getItems().get(1).setManaged(true);

        System.out.println("LayoutX " + vBox.getLayoutX());
        System.out.println("LayoutY " + vBox.getLayoutY());
        System.out.println("ScaleX " + vBox.getScaleX());
        System.out.println("ScaleY " + vBox.getScaleY());
        System.out.println("Height " + vBox.getHeight());
        System.out.println("Width " + vBox.getWidth());


        configurationBuilder.setDaemonEnabled(true)
                .setOAuthConsumerKey("DDe9Pr6bx5YvXuK2XX6pyQhFu")
                .setOAuthConsumerSecret("URn6NuOT822ViLUDTvdRM6Jthss0exL24qiyu0qkcavP1a8Y6M")
                .setOAuthAccessToken("599464837-4P4yXM1c5ndrIo922Xv4pczbozr7GtQoP67DS4fP")
                .setOAuthAccessTokenSecret("CYbgMSf6bpTVjjZNAAJzWt4BWc14gm4Gn0ozB2oWwktZt");


//        BasicConfigurator.configure();

        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();

        GetInfo getInfo = new GetInfo();
        Thread calculateThread = new Thread(getInfo);
        calculateThread.start();

        SummaryCount summaryCount = new SummaryCount();
        Thread summaryThread = new Thread(summaryCount);
        summaryThread.start();
    }

    /**
     * Сетим в і-тое меню items
     * @param i
     */

    private void setItems(int i){
        buttonsColor.add(new ArrayList<>());
        buttonsColor.get(i).add(new MenuItem("white"));
        buttonsColor.get(i).add(new MenuItem("black"));
        buttonsColor.get(i).add(new MenuItem("brown"));
        buttonsColor.get(i).add(new MenuItem("green"));
        buttonsColor.get(i).add(new MenuItem("lime"));
        buttonsColor.get(i).add(new MenuItem("yellow"));
        buttonsColor.get(i).add(new MenuItem("red"));
        buttonsColor.get(i).add(new MenuItem("maroon"));
        buttonsColor.get(i).add(new MenuItem("cyan"));
        buttonsColor.get(i).add(new MenuItem("blue"));
        buttonsColor.get(i).add(new MenuItem("pink"));
        buttonsColor.get(i).add(new MenuItem("indigo"));
        splitMenuButtonsColorsOfCountry.get(i).getItems().addAll(buttonsColor.get(i));
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

    /**
     * Метод, который выводит на экран список тэгов
     */
    public void listOfTweets(){
        Stage dialogList = new Stage();
        dialogList.initStyle(StageStyle.UTILITY);
        Text text = new Text();
        String temp = "\n";


        for (int j = 0; j < listOfTweetsId.size(); j++) {

            Map <String, Long> stringLongMap;
            stringLongMap = sortMap(j);
            Iterator it = stringLongMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                temp += (pair.getKey() + " = " + pair.getValue() + "\n");
                it.remove();
            }
            temp += ("\n");
        }

        text.setText(temp);
        text.setStyle("-fx-text-fill: #3c7fb1");
        text.setTextAlignment(TextAlignment.CENTER);
        ScrollPane scrollPane = new ScrollPane(text);
        Scene scene = new Scene(scrollPane);
        dialogList.setScene(scene);
        dialogList.setWidth(200);
        dialogList.setHeight(400);
        dialogList.setResizable(false);
        dialogList.show();
    }

    /**
     * Создаем мапу с єлементами упорядочеными по возрастанию
     * @param pos
     * @return
     */
    private Map<String, Long> sortMap(int pos){

        Map<String, Long> mapOfTweets = new HashMap<>();
        for (int k = 0; k < listOfTweetsId.get(pos).size(); k++) {
            mapOfTweets.put(trendNames.get(pos).get(k).toString(), (long) listOfTweetsId.get(pos).get(k).size());
        }
        List<String> mapKeys = new ArrayList<>(mapOfTweets.keySet());
        List<Long> mapValues = new ArrayList<>(mapOfTweets.values());

        boolean p = sort(mapKeys, mapValues);

        Map <String, Long> stringLongMap = new LinkedHashMap<>();
        for (int k = 0; k < mapId.size(); k++) {
            stringLongMap.put(mapNames.get(k), mapId.get(k));
        }
        return stringLongMap;
    }

    /**
     * Сортируем мапу по убыванию (вытаскиваем отдельно ключи и значения и сортируем их)
     * @param stringList
     * @param longList
     * @return
     */
    private boolean sort(List<String> stringList, List<Long> longList){
        boolean t = true;
        while (t == true) {
            t = false;
            for (int k = 1; k < longList.size(); k++) {
                if (longList.get(k) > longList.get(k-1)) {
                    Collections.swap(longList, k, (k-1));
                    Collections.swap(stringList, k, (k-1));
                    t = true;
                }
            }
        }
        this.mapId = longList;
        this.mapNames = stringList;
        return t;
    }

    /**
     * Гистограмма
     */

    public void showHistogram() throws IOException {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        TabPane tabbedPane = new TabPane();

        for (int i = 0; i < listOfTweetsId.size(); i++) {
            Map<String,Long> stringLongMap = sortMap(i);
            List<String> mapKeys = new ArrayList<>(stringLongMap.keySet());
            List<Long> mapValues = new ArrayList<>(stringLongMap.values());

            SplitPane splitPane = new SplitPane();
            for (int j = 0; j < 10; j++) {

                AnchorPane anchorPane = new AnchorPane();
                Button button = new Button(mapKeys.get(j));

                int finalJ = j;
                button.setOnAction(event -> {
                    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                        try {
                            desktop.browse(new URI("https://twitter.com/hashtag/" +
                                    mapKeys.get(finalJ).replaceAll("#", "")));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                button.setPrefWidth(72);
                button.setMinWidth(72);
                button.setMaxWidth(72);

                double heightOfButton = Double.valueOf(mapValues.get(j))/Double.valueOf(summaryCountOfTweets.get(i));
                heightOfButton = 600*heightOfButton*3;

                button.setPrefHeight(heightOfButton);
                button.setMinHeight(heightOfButton);
                button.setMaxHeight(heightOfButton);

                button.setLayoutX(0);
                button.setLayoutY(anchorPane.getHeight()-button.getHeight());
                anchorPane.getChildren().add(button);
                splitPane.getItems().add(anchorPane);
            }
            Tab tab = new Tab();
            tab.setContent(splitPane);
            tabbedPane.getTabs().add(tab);
        }
        Scene scene = new Scene(tabbedPane);
        dialog.setScene(scene);
        dialog.setHeight(600);
        dialog.setWidth(800);
        dialog.setResizable(false);
        dialog.show();
    }

    /**
     * Обработчик нажатия клавиши сохранения
     */

    public void saveToExcel(){
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Книга Excel", "*.xls");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(new Stage());

        if(file != null){
            SaveFile(file);
        }
    }

    /**
     * Сохраняем в exl
     * @param file
     */

    private void SaveFile(File file){
        try {
            WritableWorkbook myExcel = Workbook.createWorkbook(file);
            WritableSheet mySheet = myExcel.createSheet("mySheet",0);
            for (int i = 0; i < listOfTweetsId.size(); i++) {

                int localNames = i*2;
                int localTweets = localNames+1;
                for (int j = 0; j < listOfTweetsId.get(i).size(); j++) {
                    jxl.write.Label labelName = new jxl.write.Label(localNames, j, String.valueOf(trendNames.get(i).get(j)));
                    mySheet.addCell(labelName);
                    jxl.write.Label label = new jxl.write.Label(localTweets, j, String.valueOf(listOfTweetsId.get(i).get(j).size()));
                    mySheet.addCell(label);
                }
            }
            myExcel.write();
            myExcel.close();
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Обработчик нажатия клавиши скрытия
     */

    public void hide(){

        if (isHidden) {
            isHidden = false;
            new Thread(() -> {
                int i = 0;
                while (i < 150){
                    i++;
                    setHidden(i);
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            isHidden = true;
            new Thread(() -> {
                int i = 150;
                while (i > 0){
                    i--;
                    setHidden(i);
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    }

    /**
     * Сетим размеры меню
     * @param i
     */

    private void setHidden(int i){
        boxCountry.setPrefWidth(i);
        buttonProcess.setPrefWidth(i);
        showFullListButton.setPrefWidth(i);
        histogramButton.setPrefWidth(i);
        exportToExcelButton.setPrefWidth(i);
        aboutDevButton.setPrefWidth(i);
        serializeButton.setPrefWidth(i);
        deserializeButton.setPrefWidth(i);
        vBox.setPrefWidth(i);

        boxCountry.setMinWidth(i);
        buttonProcess.setMinWidth(i);
        showFullListButton.setMinWidth(i);
        histogramButton.setMinWidth(i);
        exportToExcelButton.setMinWidth(i);
        aboutDevButton.setMinWidth(i);
        serializeButton.setMinWidth(i);
        deserializeButton.setMinWidth(i);
        vBox.setMinWidth(i);

        boxCountry.setMaxWidth(i);
        buttonProcess.setMaxWidth(i);
        showFullListButton.setMaxWidth(i);
        histogramButton.setMaxWidth(i);
        exportToExcelButton.setMaxWidth(i);
        aboutDevButton.setMaxWidth(i);
        serializeButton.setMaxWidth(i);
        deserializeButton.setMaxWidth(i);
        vBox.setMaxWidth(i);
    }

    /**
     * Сериализация массива id и массива trendNames
     */

    public void serialize(){
        File file = new File("data");
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(listOfTweetsId);
            objectOutputStream.writeObject(trendNames);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Десериализация массива id и массива trendNames
     */

    public void deserialize(){
        File file = new File("data");
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            listOfTweetsId = (List<List<List<Long>>>) objectInputStream.readObject();
            objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод-слушатель для кнопки SEARCH
     */
    public void startCalculate() {

        String color = null;
        summaryCountOfTweets.add(0L);
        if (boxCountry.getValue().equals("Ukraine")) {
            country = 23424976;
            color = ("-fx-border-color: white");
        }
        if (boxCountry.getValue().equals("USA")) {
            country = 23424977;
            color = ("-fx-border-color: yellow");
        }
        if (boxCountry.getValue().equals("Japan")) {
            country = 23424856;
            color = ("-fx-border-color: red");
        }
        if (boxCountry.getValue().equals("Germany")) {
            country = 23424829;
            color = ("-fx-border-color: green");
        }
        if (boxCountry.getValue().equals("World")) {
            country = 1;
            color = ("-fx-border-color: cyan");
        }

        Trends trends = null;
        try {
            trends = twitter.getPlaceTrends(country);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        Trend[] trend = trends.getTrends();

        List<Button> innerButtonList = new ArrayList<>();
        buttonList.add(innerButtonList);
        List<List<Long>> innerCountryList = new ArrayList<>();
        listOfTweetsId.add(innerCountryList);
        List innerTrendNames = new ArrayList();
        trendNames.add(innerTrendNames);

        int lastIndex = getIncrementLastButton();
        int localButtonPosition = 0;

        for (Trend t :
                trend) {
            buttonList.get(lastIndex).add(new Button(t.getName()));

            buttonList.get(lastIndex).get(localButtonPosition).setStyle(color);
            localButtonPosition++;
            List<Long> innerTrendList = new ArrayList<>();
            listOfTweetsId.get(lastIndex).add(innerTrendList);
            trendNames.get(lastIndex).add(t.getName());
        }


        splitMenuButtonsColorsOfCountry.add(new SplitMenuButton());
        splitMenuButtonsColorsOfCountry.get(lastIndex).setText(boxCountry.getValue().toString());
        splitMenuButtonsColorsOfCountry.get(lastIndex).setMinWidth(137);
        setItems(lastIndex);
        propertyBar.getChildren().add(splitMenuButtonsColorsOfCountry.get(lastIndex));

        InitColor initColor = new InitColor();
        Thread thread = new Thread(initColor);
        thread.start();

        Draw draw = new Draw();
        Thread graphicsThread = new Thread(draw);
        graphicsThread.start();

        System.out.println(trend);

        setBoolean();

    }

    /**
     * Класс, инициализирующий слушатели для items (смена цвета)
     */

    public class InitColor implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < splitMenuButtonsColorsOfCountry.size(); i++) {
                for (int j = 0; j < buttonsColor.get(i).size(); j++) {
                    int finalI = i;
                    int finalJ = j;
                    buttonsColor.get(i).get(j).setOnAction(event -> {
                        for (Button button :
                                buttonList.get(finalI)) {
                            button.setStyle("-fx-border-color: " + buttonsColor.get(finalI).get(finalJ).getText());
                        }
                    });
                }
            }
        }
    }

    /**
     * Данный класс занимается отправкой запросов и обработкой результатов
     */
    public class GetInfo implements Runnable {

        @Override
        public void run() {
            calculate();
        }

        private void calculate(){
            while (true) {
                while (!getBoolean()){
                    System.err.print("WAITING");
                }
                for (int k = 0; k < trendNames.size(); k++) {
                    for (int j = 0; j < trendNames.get(k).size(); j++) {
                        Query query = new Query(trendNames.get(k).get(j).toString());
                        query.setCount(100);
                        QueryResult result;
                        try {
                            result = twitter.search(query);
                            calculateSummaryOfTweets(result, j, k);
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
                    if (listOfTweetsId.size()>1){
                        try {
                            Thread.sleep(90000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(trendNames.get(k));

                    for (int j = 0; j < listOfTweetsId.get(k).size(); j++) {
                        System.out.print(trendNames.get(k).get(j) + ": ");
                        System.out.println(listOfTweetsId.get(k).get(j).size());
                    }
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

    /**
     *Класс, занимающийся отрисовкой и перемещением кнопок
     */
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
            int lastIndex = incrementLastButtonDraw();



            for (int j = 0; j < listOfTweetsId.get(lastIndex).size(); j++) {

                /**
                 * Создаем координаты для кнопок и сетим их на саму кнопку.
                 * Также определяем направление по Х и У.
                 */
                listX.get(lastIndex).add(random.nextInt(333)+200);
                listY.get(lastIndex).add(random.nextInt(333)+200);
                directionListX.get(lastIndex).add(random.nextBoolean());
                directionListY.get(lastIndex).add(random.nextBoolean());
                buttonList.get(lastIndex).get(j).setLayoutX(listX.get(lastIndex).get(j));
                buttonList.get(lastIndex).get(j).setLayoutY(listY.get(lastIndex).get(j));

                int finalJ = j;
                Platform.runLater((() -> absolutePane.getChildren().add(buttonList.get(lastIndex).get(finalJ))));       //сетим кнопку на pane
                buttonList.get(lastIndex).get(j).setOnAction(((ActionEvent event) -> {                                  //вешаем на кнопку ивент
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
                                        trendNames.get(lastIndex).get(finalJ).toString().replaceAll("#", "")));
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

                        if (listX.get(position).get(j) <= 0) {
                            directionListX.get(position).set(j, true);
                        }

                        if (listY.get(position).get(j) <= 0) {
                            directionListY.get(position).set(j, true);
                        }

                        if (listX.get(position).get(j) >= (width-tempWidth)) {
                            directionListX.get(position).set(j, false);
                        }

                        if (listY.get(position).get(j) >= (height-tempHeight)) {
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

    /**
     * Класс, подсчитывающий суммарное количесво твитов по странам (для статистики на диаграмме)
     */
    public class SummaryCount implements Runnable{
        @Override
        public void run() {
            summary();
        }

        private void summary(){
            while (bol==false){
                System.err.print("");
            }
            while (true){
                for (int positionOuter = 0; positionOuter < listOfTweetsId.size(); positionOuter++) {
                    Long sum = 0L;
                    for (int positionInner = 0; positionInner < listOfTweetsId.get(positionOuter).size(); positionInner++) {
                        sum += listOfTweetsId.get(positionOuter).get(positionInner).size();
                    }
                    summaryCountOfTweets.set(positionOuter, sum);
                }
            }
        }
    }
}
