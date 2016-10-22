package gui;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by samule on 10/10/16.
 */
public class CustomLevelController implements Initializable, ControlledScreen {

    private MasterController _myParentController;
    private CustomLevelController thisScreen = this;

    @Override
    public void setScreenParent(MasterController screenParent) {

    }

    @Override
    public void setup() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void uploadList(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose new wordlist");
        File file = fileChooser.showOpenDialog(new Stage());

        if(file != null){

        }
    }

}
