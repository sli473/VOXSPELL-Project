package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * Author: Yuliang Zhou 6/09/2016
 */
public class TitleScreenController implements ControlledScreen{

    private MasterController _myParentScreensController;

    @FXML
    private Button _startButton;
    @FXML
    private Button _displayStatsButton;
    @FXML
    private Button _settingsButton;
    @FXML
    private Button _quitButton;

    /**
     * Requests main screen controller to switch to the quiz scene.
     * Uses a fade in and out transition.
     */
    public void startButtonPressed(){
        _myParentScreensController.setScreen(Main.Screen.LEVELSELECT);
    }

    /**
     * Requests main screen controller to switch to statistics scene which
     * contains a Table view of the stats of words at each level.
     * Uses a fade in and out transition
     */
    public void displayStatsButtonPressed(){
        _myParentScreensController.setScreen(Main.Screen.STATS);
    }

    /**
     * Requests main screen controller to switch to the options scene.
     * Uses a fade in and out transition.
     */
    public void settingsButtonPressed(){
        _myParentScreensController.setScreen(Main.Screen.SETTINGS);
    }

    public void quitButtonPressed(){
        _myParentScreensController.confirmCloseProgram();
    }

    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentScreensController = screenParent;
    }


}
