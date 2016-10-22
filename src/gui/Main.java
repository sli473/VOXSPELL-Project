package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;

public class Main extends Application {

    private MasterController _mainContainer;

    //Set enums for each screen that has been loaded.
    public enum Screen{TITLE,QUIZ,LEVELSELECT,POSTQUIZ,SETTINGS,STATS,VIDEO,CUSTOM};

    public static final String titleScreenFXML = "titleScreen.fxml";
    public static final String quizScreenFXML = "quizScreen.fxml";
    public static final String statsScreenFXML = "statsScreen.fxml";
    public static final String settingsScreenFXML = "settingsScreen.fxml";
    public static final String levelScreenFXML = "levelSelectScreen.fxml";
    public static final String postQuizScreenFXML = "postQuizScreen.fxml";
    public static final String videoPlayerFXML = "videoPlayer.fxml";
    public static final String customLevelFXML = "customLevel.fxml";
    public static MediaPlayer _mediaPlayer;
    public static MediaPlayer _click;
    public static MediaPlayer _victory;
    public static MediaPlayer _failiure;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //loading in all the screens in the main container.
        _mainContainer = new MasterController();
        _mainContainer.loadScreen(Screen.TITLE,titleScreenFXML);
        _mainContainer.loadScreen(Screen.QUIZ,quizScreenFXML);
        _mainContainer.loadScreen(Screen.STATS,statsScreenFXML);
        _mainContainer.loadScreen(Screen.SETTINGS,settingsScreenFXML);
        _mainContainer.loadScreen(Screen.LEVELSELECT,levelScreenFXML);
        _mainContainer.loadScreen(Screen.POSTQUIZ,postQuizScreenFXML);
        _mainContainer.loadScreen(Screen.VIDEO,videoPlayerFXML);
        _mainContainer.loadScreen(Screen.CUSTOM,customLevelFXML);

        //set the screen on launch to the TITLE screen.
        _mainContainer.setScreen(Screen.TITLE);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume(); //prevents window from automatically closing
                _mainContainer.confirmCloseProgram();
            }
        });

        // Creates a node that contains an ObservableList of children in order.
        Group root = new Group();
        root.getChildren().add(_mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setTitle("VOXSPELL Spelling App");
        //Setting the first stage as the titleScreen.
        primaryStage.setScene(scene);
        
        primaryStage.setMaxHeight(425);
        primaryStage.setMaxWidth(650);
        primaryStage.setMinHeight(425);
        primaryStage.setMinWidth(650);

        primaryStage.show();

        File file = new File("./src/resources/play.mp3");
        Media musicFile = new Media(file.toURI().toString());
        _mediaPlayer = new MediaPlayer(musicFile);
        _mediaPlayer.setAutoPlay(true);
        _mediaPlayer.setVolume(0.05);
        _mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                _mediaPlayer.seek(Duration.ZERO);
            }
        });
        _mediaPlayer.onRepeatProperty();


       File clickSoundFile = new File("./src/resources/menuclick.wav");
       Media clickSound = new Media(clickSoundFile.toURI().toString());
        _click = new MediaPlayer(clickSound);
        _click.setVolume(1);

        File SuccessFile = new File("./src/resources/success.wav");
        Media success = new Media(SuccessFile.toURI().toString());
        _victory = new MediaPlayer(success);
        _victory.setVolume(0.2);

        File FailFile = new File("./src/resources/fail.wav");
        Media fail = new Media(FailFile.toURI().toString());
        _failiure = new MediaPlayer(fail);
        _failiure.setVolume(1.5);
    }

    /**
     * Application calls this method when program is closed. Does final wrapping up.
     * Saves object state
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        System.out.println("Exiting...");
        _mainContainer.saveData();
    }

    public static void toggleMusic(){
        if(_mediaPlayer.isMute())
            _mediaPlayer.setMute(false);
        else{
            _mediaPlayer.setMute(true);
        }
    }

    public static void click(){
        _click.seek(_click.getStartTime());
        _click.play();
    }

    public static void victory(){
        _victory.seek(_victory.getStartTime());
        _victory.play();
    }

    public static void failiure(){
        _failiure.seek(_failiure.getStartTime());
        _failiure.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
