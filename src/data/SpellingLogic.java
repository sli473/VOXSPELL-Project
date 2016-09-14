package data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * TODO: add spelling database a field to this class? Or just pass the object as paramter
 * Created by Yuliang on 14/09/2016.
 */
public class SpellingLogic {

    StringProperty userAttempt;

    public SpellingLogic(){
        userAttempt = new SimpleStringProperty(this,"userAttempt","");
        userAttempt.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkUserAttempt();
            }
        });
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

    private boolean checkUserAttempt(){

        return false;
    }



}
