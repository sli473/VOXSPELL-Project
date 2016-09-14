package gui;

import data.SpellingLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main controller for both normal quiz mode and review quiz mode.
 * Author: Yuliang Zhou 6/09/2016
 */
public class QuizScreenController implements Initializable, ControlledScreen{

    private ScreensController _myParentScreensController;

    private SpellingLogic _myLogic;

    @FXML
    private Button _abortButton;
    @FXML
    private Button _repeatButton;

    public void repeatButtonPressed(){
        _myParentScreensController.printdatabase();
    }

    public void abortQuizButtonPressed(){
        _myParentScreensController.setScreen(Main.titleScreenID);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        _myParentScreensController = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _myLogic = new SpellingLogic();
    }
}
