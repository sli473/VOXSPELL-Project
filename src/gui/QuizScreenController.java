package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * Author: Yuliang Zhou 6/09/2016
 */
public class QuizScreenController implements ControlledScreen{

    private ScreensController _myParentScreensController;
    private static boolean _isRevision = false;

    @FXML
    private Button _abortButton;

    public static boolean get_isRevision() {
        return _isRevision;
    }

    public void abortQuizButtonPressed(){
        _myParentScreensController.setScreen(Main.titleScreenID);
    }

    public static void set_isRevision(boolean value){
        _isRevision = value;
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        _myParentScreensController = screenParent;
    }

}
