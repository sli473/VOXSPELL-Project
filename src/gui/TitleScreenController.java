package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * Author: Yuliang Zhou 6/09/2016
 */
public class TitleScreenController implements ControlledScreen{

    private ScreensController _myParentScreensController;

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
        _myParentScreensController.setScreen(Main.quizScreenID);
    }

    public void displayStatsButtonPressed(){

    }

    /**
     * Requests main screen controller to switch to the options scene.
     * Uses a fade in and out transition.
     */
    public void settingsButtonPressed(){
        _myParentScreensController.setScreen(Main.optionScreenID);
    }

    public void quitButtonPressed(){
        //popup to confirm if user wants to quit
        //make sure exit button on top of window does not by pass the confirm dialog box
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        _myParentScreensController = screenParent;
    }
}
