package gui;

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
    
    ObservableList<String> _quizTypeList = FXCollections.observableArrayList("New Quiz","Revision Quiz");

    private MasterController _myParentController;

    @FXML
    private ChoiceBox<String> _quizType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _quizType.setItems(_quizTypeList);
        _quizType.setValue("New Quiz");
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
        System.out.println("RevisionMode: "+ isRevision);
        System.out.println(level);
        //switches into the quiz menu screen
        _myParentController.setScreen(Main.quizScreenID);
        //commence quiz, and read out the first word
        _myParentController.requestStartQuiz(level,isRevision);
    }


    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentController = screenParent;
    }

}
