package data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * TODO
 * Created by Yuliang on 14/09/2016.
 */
public class SpellingLogic {

    private enum Status{FIRSTATTEMPT,SECONDATTEMPT};

    private SpellingDatabase _database;

    private StringProperty userAttempt;

    private String _currentLevel;
    private String[] _wordList;
    private int _position;
    private Status _status;

    public SpellingLogic(SpellingDatabase database,String levelKey){
        _database = database;
        _currentLevel = levelKey;
        _status = Status.FIRSTATTEMPT;
        _position = 0;
        _wordList = _database.getNormalQuiz(levelKey);
        userAttempt = new SimpleStringProperty(this,"userAttempt","");
        userAttempt.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkUserAttempt();
            }
        });
    }

    /**
     * TODO: festival reading
     * @return
     */
    private boolean checkUserAttempt(){
        boolean correct;
        if(_status == Status.FIRSTATTEMPT){
            if( _wordList[_position].toLowerCase().equals(userAttempt.getValue().toLowerCase()) ){
                _database.incrementMastered(_currentLevel,_wordList[_position]);
                //TODO: "Correct"
                System.out.println("Correct!");
                _position++;
            }else{
                //TODO: "Incorrect. Please try again"
                System.out.println("Incorrect. Please try again.");
                _status = Status.SECONDATTEMPT;
            }
        }else{//second attempted. Faulted.
            _status = Status.FIRSTATTEMPT;
            if( _wordList[_position].toLowerCase().equals(userAttempt.getValue().toLowerCase()) ){
                _database.incrementFaulted(_currentLevel,_wordList[_position]);
                _position++;
                //TODO: "Correct"
                System.out.println("Correct!");
            }else{
                _database.incrementFailed(_currentLevel,_wordList[_position]);
                _position++;
                //TODO: "Incorrect"
                System.out.println("Incorrect.");
            }
        }
        System.out.println("You entered: " + userAttempt);
        return false;
    }


    public String getUserAttempt() {
        return userAttempt.get();
    }

    public void setUserAttempt(String userAttempt) {
        this.userAttempt.set(userAttempt);
    }

    public StringProperty userAttemptProperty() {
        return userAttempt;
    }


}
