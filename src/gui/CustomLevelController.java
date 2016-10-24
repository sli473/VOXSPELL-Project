package gui;

import data.DatabaseIO;
import data.SpellingDatabase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * Created by samule on 10/10/16.
 */
public class CustomLevelController implements Initializable, ControlledScreen {

    private MasterController _myParentController;
    private ObservableList<String> _customList;
    private ObservableList<String> _quizTypeList;

    @FXML
    private ChoiceBox<String> _quizType;

    @FXML
    private ChoiceBox<String> _customQuizList;

    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentController = screenParent;
    }

    @Override
    public void setup() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _quizTypeList = FXCollections.observableArrayList("New Quiz","Revision Quiz");
        _quizType.setItems(_quizTypeList);
        _quizType.setValue("New Quiz");
        _customList = FXCollections.observableArrayList("No custom List implemented yet");
        _customQuizList.setItems(_customList);
        _customQuizList.setValue("No custom List implemented yet");
    }

    public void addCustomList(){
        try {
            //DatabaseIO data = _myParentController.get_dataIO();
            SpellingDatabase database = _myParentController.get_customDatabase();
            Set<String> dataKeySet = database.get_spellingWords().keySet();
            _customList.clear();
            for (String s : dataKeySet) {
                _customList.add(s);
                System.out.println(s);
            }
            Collections.sort(_customList, new ComparatorOfNumericString());
            _customQuizList.setItems(_customList);
            _customQuizList.setValue(_customList.get(0));
        }
        catch (Exception e){
            // do nothing
        }
    }
    public void enterNewQuiz(ActionEvent event){
        //switch into the quiz menu screen
        _myParentController.setScreen(Main.Screen.QUIZ);


        //extracting the text on the button
        String level = getChoice(_customQuizList);
        boolean isRevision = false;
        //checking which option the user chose for the quiz type
        if( getChoice(_quizType).equals("Revision Quiz")){
            isRevision = true;
        }
        else if( getChoice(_quizType).equals("New Quiz")){
            isRevision = false;
        }

        //get the QuizScreen Controller and setup the test
        QuizScreenController nextScreen = (QuizScreenController) _myParentController.getScreenController(Main.Screen.QUIZ);
        if(isRevision) {
            nextScreen.setupTest(level, true, true);
        }else{
            nextScreen.setupTest(level, false, true);
        }

        //prevents user from accidentally double clicking the button.
        Button myButton = ((Button) event.getSource());
        new Thread() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        myButton.setDisable(true);
                    }
                });
                try {
                    Thread.sleep(3000); //3 seconds
                }catch(InterruptedException ex) {
                }
                Platform.runLater(new Runnable() {
                    public void run() {
                        myButton.setDisable(false);
                    }
                });
            }
        }.start();
        Main.click();
    }

    public void backButtonPressed(){
        _myParentController.setScreen(Main.Screen.TITLE);
        Main.click();
    }

    private String getChoice(ChoiceBox<String> Type){
        return Type.getValue();
    }

}
class ComparatorOfNumericString implements Comparator<String> {

    public int compare(String string1, String string2) {
        String[] stringa = string1.split("\\s+");
        String[] stringb = string2.split("\\s+");

        return Integer.parseInt(stringa[1])-Integer.parseInt(stringb[1]);
    }
}
