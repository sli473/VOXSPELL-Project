package gui;

import data.SpellingDatabase;
import data.Word;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sun.security.pkcs11.wrapper.CK_DATE;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * Author: Yuliang Zhou 7/09/2016
 */
public class StatsScreenController implements ControlledScreen{

    private MasterController _myParentScreensController;

    private SpellingDatabase _database;

    private SpellingDatabase _customDatabase;

    private SpellingDatabase _regularDatabase;

    private ObservableList<String> _usedList;

    private ObservableList<String> _customOptions;

    private ObservableList<String> _regularOptions;

    private boolean _isCustom = false;

    @FXML
    private Label _accuracyForLevel;
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
        _isCustom=false;
        _database = _regularDatabase;
        _usedList = _regularOptions;
        Main.click();
    }

    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentScreensController = screenParent;
    }

    @Override
    public void setup() {
        //get database object
        _regularDatabase = _myParentScreensController.getDatabase();
        _customDatabase = _myParentScreensController.get_customDatabase();
        _isCustom = false;


        //setup combobox selection
        _regularOptions = FXCollections.observableArrayList();
        _regularOptions.clear();
        ArrayList<String> levels = _regularDatabase.getAllLevels();
        for(String s : levels) {
            _regularOptions.add(s);
        }

        _customOptions = FXCollections.observableArrayList();
        _customOptions.clear();
        ArrayList<String> clevels = _customDatabase.getAllLevels();
        for(String t : clevels) {
            _customOptions.add(t);
        }


        if(_isCustom){
            _database = _customDatabase;
            _usedList = _customOptions;
        }
        else{
            _database = _regularDatabase;
            _usedList = _regularOptions;
            System.out.println("set as regular");
        }

        //_levelSelection.getItems().clear();
        _levelSelection.setItems(_usedList);

        _levelSelection.setValue(_usedList.get(0));
      //  _levelSelection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
        //    @Override
          //  public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            //    _table.setItems(_database.getLevel((String)newValue));
              //  _accuracyForLevel.setText("Accuracy: " + _database.getAccuracyScore((String)newValue) +"%");
           // }
        //});

        //setup table
        screenOpened();
    }

    public void toggle(){
        if(_isCustom == true){
            _isCustom = false;
            _database = _regularDatabase;
            _usedList = _regularOptions;
            System.out.println("set as regular");

        }
        else{
            _isCustom = true;
            _database = _customDatabase;
            _usedList = _customOptions;
            System.out.println("set as custom");
        }
        screenOpened();
    }

    /**
     * This method is called immediately after the screen is set and updates the table view.
     */
    public void screenOpened(){
        //_isCustom = false;
        _customDatabase = _myParentScreensController.get_customDatabase();
        _levelSelection.setItems(_usedList);

        _levelSelection.setValue(_usedList.get(0));


        //setup table columns
        _wordColumn.setCellValueFactory(new PropertyValueFactory<>("_word"));
        _masteredColumn.setCellValueFactory(new PropertyValueFactory<>("_mastered"));
        _faultedColumn.setCellValueFactory(new PropertyValueFactory<>("_faulted"));
        _failedColumn.setCellValueFactory(new PropertyValueFactory<>("_failed"));


        _table.setItems(_database.getLevel(_levelSelection.getValue()));

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        Number n =_database.getAccuracyScore(_levelSelection.getValue());

        _accuracyForLevel.setText("Accuracy: " + df.format(n.doubleValue()) +"%");
        _accuracyForLevel.setText("Accuracy: " + df.format(n.doubleValue()) +"%");
    }

    public void generateTable(){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        Number n =_database.getAccuracyScore(_levelSelection.getValue());
        _table.setItems(_database.getLevel(_levelSelection.getValue()));
        _accuracyForLevel.setText("Accuracy: "+ df.format(n.doubleValue()) +"%");
    }
}
