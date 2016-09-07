package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private static Stage _window;

    public final static String titleScreenID = "mainTitle";
    public final static String titleScreenFXML = "titleScreen.fxml";
    public final static String quizScreenID = "quiz";
    public final static String quizScreenFXML = "quizScreen.fxml";
    public final static String optionScreenID = "optionScreen";
    public final static String optionScreenFXML = "optionScreen.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        _window = primaryStage;
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(titleScreenID,titleScreenFXML);
        mainContainer.loadScreen(quizScreenID,quizScreenFXML);
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

    public static void confirmCloseProgram(){
        Boolean closeOperation = ConfirmDialogBox.display("Please don't go","Are you sure you want to quit?");
        if(closeOperation){
            //TODO: save and close
            _window.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
