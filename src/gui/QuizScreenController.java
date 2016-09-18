package gui;

import data.SpellingDatabase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

/**
 * Main controller for both normal quiz mode and review quiz mode.
 * Author: Yuliang Zhou 6/09/2016
 */
public class QuizScreenController implements ControlledScreen{

    private MasterController _myParentController;

    @FXML
    private TextField _textfield;
    @FXML
    private ProgressBar _progressBar;
    @FXML
    private Label _progressLabel;
    @FXML
    private Label _tooltip;
    @FXML
    private Label _accuracy;

    public void repeatButtonPressed(ActionEvent event){
        read(_wordList[_position]);
    }

    public void abortQuizButtonPressed(ActionEvent event){
        boolean confirm = DialogBox.displayConfirmDialogBox("Quit current quiz","Are you sure you wish to " +
                "quit current quiz. Unsaved progress will be lost");
        if(confirm) {
            _myParentController.setScreen(Main.Screen.TITLE);
        }
    }

    /**
     * enteredWord is called whenever Enter button is pressed or enter key is pressed
     */
    public void enteredWord(ActionEvent event) {
        //TODO: what if user enters same word - tool tip ? or reset stringproperty
        //TODO: what if user enters I'm
        if(_textfield.getText().equals("")){
            _tooltip.setText("Please enter a word");
        }else if(_textfield.getText().matches(".*\\d+.*")){
            _tooltip.setText("Please do not enter numbers");
        }else if( _status.equals(Status.SECONDATTEMPT) && get_userAttempt().equals(_textfield.getText()) ) {
            _tooltip.setText("Please try a different spelling");
        }else{
            _userAttempt.set(_textfield.getText());
        }
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
    }

    @Override
    public void setup() {
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
    private boolean _isRevision;
    //4pts for mastered, 2pts for faulted, 0pts failed
    private int _score;
    private int _correctCount;

    /**
     * This method is called by the MasterController after the screen is set.
     * @param database
     * @param levelKey, revision mode
     */
    public void setupTest(SpellingDatabase database,String levelKey,boolean isRevision){
        _database = database;
        _currentLevel = levelKey;
        _isRevision = isRevision;
        _position = 0;
        _score = 0;
        _correctCount = 0;
        _status = Status.FIRSTATTEMPT;
        if(_isRevision) {
            _wordList = _database.getReviewQuiz(levelKey);
        }else{
            _wordList = _database.getNormalQuiz(levelKey);
        }
        _userAttempt = new SimpleStringProperty(this,"_userAttempt","");
        _userAttempt.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                _tooltip.setText("");
                checkUserAttempt();
            }
        });
        if( _wordList.length == 0){
            _myParentController.setScreen(Main.Screen.POSTQUIZ);
            _myParentController.setPostScreenTestResults(_currentLevel,0.0,_correctCount,_wordList.length);
            return;
        }
        //Commence test
        read("Please spell: " + _wordList[_position]);
        //set progress label and progress bar and accuracy
        _progressBar.setProgress(_position);
        _progressLabel.setText("Please spell word "+(_position+1)+" of "+_wordList.length);
        _accuracy.setText("Accuracy: "+0.0+"%");
        _tooltip.setText("");
    }


    /**
     * TODO: festival reading
     * TODO: in review mode. what if there are no failed words for that level
     * This method is called whenever the user attempt string property is changed. This occurs
     * when the user enters a word. Checks if the user's attempt is same as the correct spelling
     * of the word. Ignores case.
     * @return
     */
    private void checkUserAttempt(){
        boolean completed = false;
        if (_status == Status.FIRSTATTEMPT) {//================================================================MASTERED
            if (_wordList[_position].toLowerCase().equals(_userAttempt.getValue().toLowerCase())) {
                //FESTIVAL READ
                read("Correct!");

                //UPDATE DATABASE
                _database.incrementMastered(_currentLevel, _wordList[_position]);
                //if in revision mode, remove word from failed list
                if(_isRevision){
                    _database.removeFailedWord(_wordList[_position],_currentLevel);
                }

                //UPDATE SCORE 4pts MASTERED
                _score+=4;
                _correctCount++;

                //MOVE ONTO NEXT WORD
                _position++;
                if( _position == _wordList.length ){
                    completed = true;
                }else {//Move onto next word
                    read("Please spell: " + _wordList[_position]);
                    _progressLabel.setText("Please spell word "+(_position+1)+" of "+_wordList.length);
                }

            } else { // GO TO SECOND ATTEMPT
                read("Incorrect. Please try again: " + _wordList[_position]);
                _progressLabel.setText("Incorrect. Please spell word "+(_position+1)+" of "+_wordList.length);
                _status = Status.SECONDATTEMPT;
            }
        } else {//==============================================================================================FAULTED
            if (_wordList[_position].toLowerCase().equals(_userAttempt.getValue().toLowerCase())) {
                //FESTIVAL READ
                read("Correct!");

                //UPDATE DATABASE
                _database.incrementFaulted(_currentLevel, _wordList[_position]);
                //if in revision mode, remove word from failed list
                if(_isRevision){
                    _database.removeFailedWord(_wordList[_position],_currentLevel);
                }

                //UPDATE SCORE 2pts FAULTED
                _score+=2;
                _correctCount++;

                //MOVE ONTO NEXT WORD
                _position++;
                if( _position == _wordList.length ){
                    completed = true;
                }else {//Correct on second attempt. Move onto next word
                    read("Please spell: " + _wordList[_position]);
                    _progressLabel.setText("Please spell word "+(_position+1)+" of "+_wordList.length);
                }
            } else {//===========================================================================================FAILED
                //FESTIVAL READ
                read("Incorrect");

                //UPDATE DATABASE
                _database.incrementFailed(_currentLevel, _wordList[_position]);
                if(!_isRevision){ //if in normal mode, add word to failed list
                    _database.addFailedWord(_wordList[_position],_currentLevel);
                }

                //UPDATE SCORE 0pts FAILED

                //MOVE ONTO NEXT WORD
                _position++;
                if( _position == _wordList.length ){
                    completed = true;
                }else {
                    read("Please spell: " + _wordList[_position]);
                    _progressLabel.setText("Please spell word "+(_position+1)+" of "+_wordList.length);
                }
            }
            _status = Status.FIRSTATTEMPT;
        }
        //set progress bar
        _progressBar.setProgress((double)(_position)/_wordList.length);
        System.out.println("SCORE:"+_score);
        System.out.println("total:"+(_position)*4);
        double accuracy = ((double) _score / (_position * 4)) * 100;
        if(Double.isNaN(accuracy)){
            _accuracy.setText("Accuracy: " + 0.0 + "%");
        }else {
            _accuracy.setText("Accuracy: " + accuracy + "%");
        }
        if(completed){
            _database.addAccuracyScore(_score,_wordList.length,_currentLevel);
            _myParentController.setScreen(Main.Screen.POSTQUIZ);
            _myParentController.setPostScreenTestResults(_currentLevel, accuracy,_correctCount, _wordList.length);
        }
    }

    public String get_userAttempt() {
        return _userAttempt.get();
    }


    //stub method for festival reading
    public void read(String phrase){
        System.out.println("FESTIVAL: " + phrase);
    }


}
