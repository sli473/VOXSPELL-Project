package gui;

import data.DatabaseIO;
import data.SpellingDatabase;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private DatabaseIO _dataIO;
    private SpellingDatabase _spellingWords;

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

    @Override
    public void start(Stage primaryStage) throws Exception{
        _dataIO = new DatabaseIO();
        _spellingWords = _dataIO.openData();
        ScreensController mainContainer = new ScreensController(_spellingWords);
        mainContainer.loadScreen(titleScreenID,titleScreenFXML);
        mainContainer.loadScreen(quizScreenID,quizScreenFXML);
        mainContainer.loadScreen(statsScreenID,statsScreenFXML);
        mainContainer.loadScreen(optionScreenID,optionScreenFXML);

        mainContainer.setScreen(titleScreenID);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume(); //prevents window from automatically closing
                mainContainer.confirmCloseProgram();
            }
        });

        Group root = new Group();
        root.getChildren().add(mainContainer);
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
        System.out.println("exited?");
        _dataIO.writeData(_spellingWords);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
