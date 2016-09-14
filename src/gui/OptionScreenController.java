package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * Author: Yuliang Zhou 7/09/2016
 */
public class OptionScreenController implements ControlledScreen{

    private MasterController _myParentScreensController;

    @FXML
    private Button _cancelButton;
    @FXML
    private Button _okButton;

    public void cancelButtonPressed(){
        //TODO: confirm if user wants to discard changes
        _myParentScreensController.setScreen(Main.titleScreenID);
    }

    public void okButtonPressed(){
        //TODO: save changes and go to title screen
        _myParentScreensController.setScreen(Main.titleScreenID);
    }

    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentScreensController = screenParent;
    }

    @Override
    public void setup() {

    }
}
