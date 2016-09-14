package gui;

import data.SpellingLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    @FXML
    private TextField _textfield;

    public void repeatButtonPressed(ActionEvent event){
        _myParentScreensController.printdatabase();
    }

    public void abortQuizButtonPressed(ActionEvent event){
        _myParentScreensController.setScreen(Main.titleScreenID);
    }

    /**
     * enteredWord is called whenever Enter button is pressed or enter key is pressed
     */
    public void enteredWord(){
        //TODO set userAttempt in SpellingLogic
        System.out.println("You entered: "+ _textfield.getText());
        _textfield.setText("");
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
