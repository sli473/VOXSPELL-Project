package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public final static String titleScreenID = "mainTitle";
    public final static String titleScreenFXML = "titleScreen.fxml";
    public final static String quizScreenID = "quiz";
    public final static String quizScreenFXML = "quizScreen.fxml";
    public final static String optionScreenID = "optionScreen";
    public final static String optionScreenFXML = "optionScreen.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(titleScreenID,titleScreenFXML);
        mainContainer.loadScreen(quizScreenID,quizScreenFXML);
        mainContainer.loadScreen(optionScreenID,optionScreenFXML);

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
