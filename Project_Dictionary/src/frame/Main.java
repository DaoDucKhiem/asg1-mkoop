package frame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        String path = "background.fxml";
                Parent root = FXMLLoader.load(getClass().getResource(path));
                primaryStage.setTitle("Dictionary EngLish-VietNamese");
                primaryStage.setScene(new Scene(root, 745, 642));
                primaryStage.show();
    }
}