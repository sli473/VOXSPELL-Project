package gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;
import java.io.IOException;

/**
 * Author: Yuliang Zhou 6/09/2016
 */
public class PostQuizController implements ControlledScreen{

    private MasterController _myParentController;

    @FXML
    private Label _userResultsOne;
    @FXML
    private Label _userResultsTwo;
    @FXML
    private Button _playVideoButton;
    @FXML
    private Button _nextLevelButton;


    private String _level;
    private int _correct;
    private int _total;
    private double _accuracy;

    public void returnToTitleButtonPressed(ActionEvent event){
        _myParentController.setScreen(Main.Screen.TITLE);
    }

    public void playVideoButtonPressed(ActionEvent event)throws IOException{
            String path = System.getProperty("user.dir");
            path.replace("\\\\", "/");
            path +=  "/resources/rewardVideo.html";
            System.out.println(path);
            try {
                new ProcessBuilder("x-www-browser", path).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * This button is only enable if user scored 9 or more and the level is less than level 11.
     */
    public void nextLevelButtonPressed(){
        String[] level = _level.split(" ");
        int nextLevelNumber = Integer.parseInt(level[1]);
        nextLevelNumber++;
        String nextLevel = "Level " + nextLevelNumber;

        //change into quiz screen
        _myParentController.setScreen(Main.Screen.QUIZ);

        //get the QuizScreen Controller
        QuizScreenController nextScreen = (QuizScreenController)_myParentController.getScreenController(Main.Screen.QUIZ);
        nextScreen.setupTest(nextLevel,false);
    }

    public void reviewLevelButtonPressed(ActionEvent e){
        //change into the review quiz screen
        _myParentController.setScreen(Main.Screen.QUIZ);

        //get the QuizScreen Controller
        QuizScreenController nextScreen = (QuizScreenController)_myParentController.getScreenController(Main.Screen.QUIZ);
        nextScreen.setupTest(_level,true);
    }

    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentController = screenParent;
    }

    @Override
    public void setup() {
    }

    public void set_testResults(String level, double accuracy, int correct, int total){
        _level = level;
        _correct = correct;
        _accuracy = accuracy;
        _total = total;
    }

    public void showResults() {
        if (_total == 0) {
            _userResultsOne.setText("Congratulations. No mistakes to review.");
            _userResultsTwo.setText("Keep up the good work :)");
        }else {
            _userResultsOne.setText("Congratulations you scored: " + _correct + " of " + _total);
            _userResultsTwo.setText("Accuracy for  " + _level + ": " + _accuracy + "%");
        }
        if(_correct>8){
            _playVideoButton.setDisable(false);
        }else{
            _playVideoButton.setDisable(true);
        }
        if(_level.equals("Level 11") || _correct<9 ){
            _nextLevelButton.setDisable(true);
        }else{
            _nextLevelButton.setDisable(false);
        }

    }
}
