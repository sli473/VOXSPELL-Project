package gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Samule Li on 13/09/16.
 */
public class LevelController implements Initializable,ControlledScreen {
    
    ObservableList<String> _quizTypeList;

    private MasterController _myParentController;

    @FXML
    private ChoiceBox<String> _quizType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _quizTypeList = FXCollections.observableArrayList("New Quiz","Revision Quiz");
        _quizType.setItems(_quizTypeList);
        _quizType.setValue("New Quiz");
    }

    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentController = screenParent;
    }

    @Override
    public void setup() {

    }

    private String getChoice(ChoiceBox<String> _quizType){
        return _quizType.getValue();
    }

    /**
     * This is method called whenever a level tile button is clicked. Switches the scene to
     * quizScreen and calls the setupTest method in the MasterController.
     * @param event
     */
    public void enterNewQuiz(ActionEvent event){
        //switch into the quiz menu screen
        _myParentController.setScreen(Main.Screen.QUIZ);

        //extracting the text on the button
        String level = "Level "+((Button) event.getSource()).getText();
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
            nextScreen.setupTest(level, true);
        }else{
            nextScreen.setupTest(level, false);
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
                    Thread.sleep(3000); //3 seconds, obviously replace with your chosen time
                }catch(InterruptedException ex) {
                }
                Platform.runLater(new Runnable() {
                    public void run() {
                        myButton.setDisable(false);
                    }
                });
            }
        }.start();
    }

    public void backButtonPressed(){
        _myParentController.setScreen(Main.Screen.TITLE);
    }



}
