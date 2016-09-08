package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private static Stage _window;
    //make non static?

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
        _window = primaryStage;
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(titleScreenID,titleScreenFXML);
        mainContainer.loadScreen(quizScreenID,quizScreenFXML);
        mainContainer.loadScreen(statsScreenID,statsScreenFXML);
        mainContainer.loadScreen(optionScreenID,optionScreenFXML);

        mainContainer.setScreen(titleScreenID);

        _window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume(); //prevents window from automatically closing
                confirmCloseProgram();
            }
        });

        Group root = new Group();
        root.getChildren().add(mainContainer);
        _window.setTitle("Hello World");
        _window.setScene(new Scene(root));
        _window.show();
    }

    /**
     * Show dialog box to confirm if user wants to close program.
     */
    public static void confirmCloseProgram(){
        Boolean closeOperation = DialogBox.displayConfirmDialogBox("Please don't go","Are you sure you want to quit?");
        if(closeOperation){
            //TODO: save and close
            //TODO: what if user closes while in quiz mode
            _window.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
