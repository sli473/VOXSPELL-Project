package gui;

import data.SpellingLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * TODO
 * Main controller for both normal quiz mode and review quiz mode.
 * Author: Yuliang Zhou 6/09/2016
 */
public class QuizScreenController implements Initializable, ControlledScreen{

    private MasterController _myParentController;

    private SpellingLogic _myLogic;

    @FXML
    private Button _abortButton;
    @FXML
    private Button _repeatButton;
    @FXML
    private TextField _textfield;

    public void repeatButtonPressed(ActionEvent event){
        _myParentController.printdatabase();
    }

    public void abortQuizButtonPressed(ActionEvent event){
        _myParentController.setScreen(Main.titleScreenID);
    }

    /**
     * enteredWord is called whenever Enter button is pressed or enter key is pressed
     */
    public void enteredWord(){
        //TODO: if nothing entered add a tooltip "Please enter the spelling of the word"
        _myLogic.setUserAttempt(_textfield.getText());
        _textfield.setText("");
    }

    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentController = screenParent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO: move into another method which should be called when this screen is opened
        _myLogic = new SpellingLogic(_myParentController.getDatabase(),_myParentController.getCurrentLevel());
    }
}
