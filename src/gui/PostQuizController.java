package gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;

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


    private String _level;
    private int _correct;
    private int _total;
    private double _accuracy;

    public void returnToTitleButtonPressed(ActionEvent event){
        _myParentController.setScreen(Main.titleScreenID);
    }

    public void playVideoButtonPressed(ActionEvent e){
        //TODO: open video player
    }

    public void reviewLevelButtonPressed(ActionEvent e){
        _myParentController.setScreen(Main.quizScreenID);
        _myParentController.requestStartQuiz(_level,true);
    }

    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentController = screenParent;
        _myParentController.setPostQuizController(this);
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
    }
}
