package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private MasterController _mainContainer;

    //TODO
    //public enum Screen{TITLE,QUIZ,SETTINGS,STATS};

    public static final String titleScreenID = "mainTitle";
    public static final String titleScreenFXML = "titleScreen.fxml";
    public static final String quizScreenID = "quiz";
    public static final String quizScreenFXML = "quizScreen.fxml";
    public static final String statsScreenID = "statsScreen";
    public static final String statsScreenFXML = "statsScreen.fxml";
    public static final String optionScreenID = "optionScreen";
    public static final String optionScreenFXML = "optionScreen.fxml";
    public static final String levelScreenID = "levelScreen";
    public static final String levelScreenFXML = "levelSelectScreen.fxml";
    public static final String videoPlayerID = "videoPlayer";
    public static final String videoPlayerFXML = "videoPlayer.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        _mainContainer = new MasterController();
        _mainContainer.loadScreen(titleScreenID,titleScreenFXML);
        _mainContainer.loadScreen(quizScreenID,quizScreenFXML);
        _mainContainer.loadScreen(statsScreenID,statsScreenFXML);
        _mainContainer.loadScreen(optionScreenID,optionScreenFXML);
        _mainContainer.loadScreen(levelScreenID,levelScreenFXML);
        _mainContainer.loadScreen(videoPlayerID,videoPlayerFXML);

        _mainContainer.setScreen(titleScreenID);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume(); //prevents window from automatically closing
                _mainContainer.confirmCloseProgram();
            }
        });

        Group root = new Group();
        root.getChildren().add(_mainContainer);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Application calls this method when program is closed. Does final wrapping up.
     * Saves object state
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        System.out.println("exiting...");
        _mainContainer.saveData();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
