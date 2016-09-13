package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * Author: Yuliang Zhou 6/09/2016
 */
public class QuizScreenController implements ControlledScreen{

    private ScreensController _myParentScreensController;

    @FXML
    private Button _abortButton;

    public void abortQuizButtonPressed(){
        _myParentScreensController.setScreen(Main.titleScreenID);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        _myParentScreensController = screenParent;
    }
}
