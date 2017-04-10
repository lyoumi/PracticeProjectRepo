import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by lyoumi on 24.03.2017.
 */
public class Main extends Application{


    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/sample.fxml"));
        Parent root = loader.load();
//        TwitterGetInfo controller = loader.getController();
        primaryStage.setTitle("TwiStat");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
//        controller.runResizeble(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
