package gui;

import data.SpellingDatabase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 *
 * Author: Yuliang Zhou 7/09/2016
 */
public class SettingsScreenController implements ControlledScreen{

    private MasterController _myParentScreensController;

    @FXML
    private Button _cancelButton;
    @FXML
    private Button _okButton;

    public void cancelButtonPressed(){
        //TODO: confirm if user wants to discard changes
        _myParentScreensController.setScreen(Main.Screen.TITLE);
    }

    public void okButtonPressed(){
        //TODO: save changes and go to title screen
        _myParentScreensController.setScreen(Main.Screen.TITLE);
    }

    /**
     * Sets the parent controller to the MasterController. Then gets a reference to the spelling database object
     * @param screenParent
     */
    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentScreensController = screenParent;
    }

    @Override
    public void setup() {

    }


}
