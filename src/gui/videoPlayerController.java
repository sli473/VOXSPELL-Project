package gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by samule on 15/09/16.
 */
public class videoPlayerController implements Initializable, ControlledScreen{

    private MasterController _myParentScreensController;

    @FXML
    private MediaView _mediaView;
    private MediaPlayer _mediaPlayer;
    private Media _media;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("media/play.mp4");
        _media = new Media(file.toURI().toString());
        //Media media = new Media(
        System.out.println(_media);
        _mediaPlayer = new MediaPlayer(_media);
        System.out.println("could compile Media player");
        _mediaView.setMediaPlayer(_mediaPlayer);
        _mediaPlayer.setAutoPlay(true);
    }

    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentScreensController = screenParent;
    }

    @Override
    public void setup() {

    }
}
