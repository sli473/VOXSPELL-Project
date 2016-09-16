package gui;

import data.SpellingDatabase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

/**
 * TODO move code from SpellingLogic into here?
 * TODO ^- how to start test once screen switches?
 * Main controller for both normal quiz mode and review quiz mode.
 * Author: Yuliang Zhou 6/09/2016
 */
public class QuizScreenController implements ControlledScreen{

    private MasterController _myParentController;

    @FXML
    private Button _abortButton;
    @FXML
    private Button _repeatButton;
    @FXML
    private TextField _textfield;
    @FXML
    private ProgressBar _progress;

    public void repeatButtonPressed(ActionEvent event){
        read(_wordList[_position]);
    }

    public void abortQuizButtonPressed(ActionEvent event){
        //TODO: wrap up quiz and abandon?
        _myParentController.setScreen(Main.titleScreenID);
    }

    /**
     * enteredWord is called whenever Enter button is pressed or enter key is pressed
     */
    public void enteredWord(ActionEvent event) {
        //TODO: if nothing entered add a tooltip "Please enter the spelling of the word"
        //TODO: what if user enters same word - tool tip ? or reset stringproperty
        _userAttempt.set(_textfield.getText());
        _textfield.setText("");
    }

    /**
     * This method sets the the MasterController as the parent controller for the quiz controller
     * then sets the quiz controller as a field in the master controller. Thus can call methods in
     * the quiz controller object.
     * @param screenParent
     */
    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentController = screenParent;
        _myParentController.setQuizController(this);
    }


    //==========================================SPELLING_LOGIC=====================================================//

    private enum Status{FIRSTATTEMPT,SECONDATTEMPT};

    private SpellingDatabase _database;

    private StringProperty _userAttempt;

    private String _currentLevel;
    private String[] _wordList;
    private int _position;
    private Status _status;

    /**
     * This method is called by the MasterController after the screen is set.
     * @param database
     * @param levelKey
     */
    public void setupTest(SpellingDatabase database,String levelKey,boolean isRevision){
        _database = database;
        _currentLevel = levelKey;
        _wordList = _database.getNormalQuiz(levelKey);
        _position = 0;
        _status = Status.FIRSTATTEMPT;
        _userAttempt = new SimpleStringProperty(this,"_userAttempt","");
        _userAttempt.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkUserAttempt();
            }
        });
        //Commence test
        read("Please spell: " + _wordList[_position]);
    }


    /**
     * TODO: festival reading
     * @return
     */
    private void checkUserAttempt(){
        if (_status == Status.FIRSTATTEMPT) {
            if (_wordList[_position].toLowerCase().equals(_userAttempt.getValue().toLowerCase())) {
                _database.incrementMastered(_currentLevel, _wordList[_position]);
                read("Correct!");
                _position++;
                if( _position>_wordList.length-1 ){
                    //Completed quiz. Change screen to post quiz screen.
                    _myParentController.setScreen(Main.postQuizScreenID);
                    //TODO: pass test results to postquiz controller screen
                    return;
                }
                read("Please spell: " + _wordList[_position]);
            } else {
                read("Incorrect. Please try again: " + _wordList[_position]);
                _status = Status.SECONDATTEMPT;
            }
        } else {//second attempted. Faulted.
            _status = Status.FIRSTATTEMPT;
            if (_wordList[_position].toLowerCase().equals(_userAttempt.getValue().toLowerCase())) {
                _database.incrementFaulted(_currentLevel, _wordList[_position]);
                read("Correct!");
                _position++;
                if( _position>_wordList.length-1 ){
                    //Completed quiz. Change screen to post quiz screen.
                    _myParentController.setScreen(Main.postQuizScreenID);
                    //TODO: pass test results to postquiz controller screen
                    return;
                }
                read("Please spell: " + _wordList[_position]);
            } else {
                _database.incrementFailed(_currentLevel, _wordList[_position]);
                read("Incorrect");
                _position++;
                if( _position>_wordList.length-1 ){
                    //Completed quiz. Change screen to post quiz screen.
                    _myParentController.setScreen(Main.postQuizScreenID);
                    //TODO: pass test results to postquiz controller screen
                    return;
                }
                read("Please spell: " + _wordList[_position]);
            }
        }
        System.out.println("You entered: " + _userAttempt);
    }

    public void getResults(){

    }

    //stub method for festival reading
    public void read(String phrase){
        System.out.println("FESTIVAL: " + phrase);
    }


}
