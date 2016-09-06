package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * Author: Yuliang Zhou 6/09/2016
 */
public class QuizScreenController implements ControlledScreen{

    private ScreenController _myParentScreenController;


    @Override
    public void setScreenParent(ScreenController screenParent) {
        _myParentScreenController = screenParent;
    }
}
