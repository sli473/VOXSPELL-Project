package gui;

import data.SpellingDatabase;
import data.Word;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

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
    private TableView<Word> _table;
    @FXML
    private TableColumn<Word, String> _wordColumn;
    @FXML
    private TableColumn<Word, Integer> _masteredColumn;
    @FXML
    private TableColumn<Word, Integer> _faultedColumn;
    @FXML
    private TableColumn<Word, Integer> _failedColumn;

    public void backButtonPressed(){
        _myParentScreensController.setScreen(Main.Screen.TITLE);
    }

    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentScreensController = screenParent;
    }

    @Override
    public void setup() {
        _myParentScreensController.setStatsController(this);

        //get database object
        _database = _myParentScreensController.getDatabase();

        //setup combobox selection
        ArrayList<String> levels = _database.getAllLevels();
        for( String levelNumber : levels ) {
            _levelSelection.getItems().add(levelNumber);
        }
        _levelSelection.setValue(levels.get(0));
        _levelSelection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                _table.setItems(_database.getLevel((String)newValue));
            }
        });

        //setup table columns
        _wordColumn.setCellValueFactory(new PropertyValueFactory<>("_word"));
        _masteredColumn.setCellValueFactory(new PropertyValueFactory<>("_mastered"));
        _faultedColumn.setCellValueFactory(new PropertyValueFactory<>("_faulted"));
        _failedColumn.setCellValueFactory(new PropertyValueFactory<>("_failed"));

        //setup table
        screenOpened();
    }

    /**
     * This method is called immediately after the screen is set and updates the table view.
     */
    public void screenOpened(){
        _table.setItems(_database.getLevel(_levelSelection.getValue()));
    }

}
