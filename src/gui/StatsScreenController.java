package gui;

import data.SpellingDatabase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 *
 * Author: Yuliang Zhou 7/09/2016
 */
public class StatsScreenController implements ControlledScreen{

    private MasterController _myParentScreensController;

    private SpellingDatabase _database;

    @FXML
    private Button _backButton;
    @FXML
    private ChoiceBox<String> _levelSelection;
    @FXML
    private TableView _table;
    @FXML
    private TableColumn _wordColumn;
    @FXML
    private TableColumn _masteredColumn;
    @FXML
    private TableColumn _faultedColumn;
    @FXML
    private TableColumn _failedColumn;


    public void backButtonPressed(){
        _myParentScreensController.setScreen(Main.Screen.TITLE);
    }

    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentScreensController = screenParent;
    }

    @Override
    public void setup() {
        _database = _myParentScreensController.getDatabase();
        ArrayList<String> levels = _database.getLevels();
        for( String levelNumber : levels ) {
            _levelSelection.getItems().add(levelNumber);
        }
        _levelSelection.setValue("Level 1");
    }


}
