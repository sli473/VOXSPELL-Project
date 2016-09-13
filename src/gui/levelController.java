package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

/**
 * Created by samule on 13/09/16.
 */
public class levelController implements ControlledScreen {

    ObservableList<String> _quizTypeList = FXCollections.observableArrayList("New Quiz","Revision Quiz");

    private ScreensController _myParentScreensController;

    @FXML
    private ChoiceBox _quizType;

    @FXML
    private void  initialize(){
        _quizType.setValue("New Quiz");
        _quizType.setItems(_quizTypeList);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        _myParentScreensController = screenParent;
    }
}
