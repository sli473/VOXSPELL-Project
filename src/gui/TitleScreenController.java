package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Screen;

/**
 *
 * Author: Yuliang Zhou 6/09/2016
 */
public class TitleScreenController implements ControlledScreen{

    private ScreenController _myParentScreenController;

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
     * Uses a fade in and out transition..
     */
    public void startButtonPressed(){
        _myParentScreenController.setScreen(Main.quizScreenID);
    }

    public void displayStatsButtonPressed(){

    }

    public void settingsButtonPressed(){

    }

    public void quitButtonPressed(){

    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        _myParentScreenController = screenParent;
    }
}
