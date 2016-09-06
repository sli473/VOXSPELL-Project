package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public final static String titleScreenID = "mainTitle";
    public final static String titleScreenFXML = "titleScreen.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        ScreenController mainContainer = new ScreenController();
        mainContainer.loadScreen(titleScreenID,titleScreenFXML);

        mainContainer.setScreen(titleScreenID);

        Group root = new Group();
        root.getChildren().add(mainContainer);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
