package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by samule on 13/09/16.
 */
public class levelController implements ControlledScreen {
    
    ObservableList<String> _quizTypeList = FXCollections.observableArrayList("New Quiz","Revision Quiz");

    private MasterController _myParentScreensController;

    @FXML
    private ChoiceBox<String> _quizType;

    @FXML
    private void  initialize(){

        _quizType.setItems(_quizTypeList);
        _quizType.setValue("New Quiz");
    }

    private String getChoice(ChoiceBox<String> _quizType){
        String quizType = _quizType.getValue();
        return quizType;
    }

    public void playVideo() throws IOException {
        String path = System.getProperty("user.dir");
        path.replace("\\\\", "/");
        path +=  "/rewardVideo.html";
        System.out.println(path);
        try {
            new ProcessBuilder("x-www-browser", path).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void enterNewQuiz(ActionEvent event){
        //extracting the text on the button
        String level = ((Button) event.getSource()).getText();
        //checking which option the user chose for the quiz type
        if( getChoice(_quizType).equals("Revision Quiz")){
            QuizScreenController.set_isRevision(true);
        }
        else if( getChoice(_quizType).equals("New Quiz")){
            QuizScreenController.set_isRevision(false);
        }
        System.out.println(QuizScreenController.get_isRevision());
        System.out.println(level);
        _myParentScreensController.setScreen(Main.quizScreenID);
    }
    //nyes
    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentScreensController = screenParent;
    }

    @Override
    public void setup() {

    }
}
