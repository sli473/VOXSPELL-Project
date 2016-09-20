package gui;

import data.SpellingDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 *
 * Author: Yuliang Zhou 7/09/2016
 */
public class SettingsScreenController implements ControlledScreen{

    private MasterController _myParentScreensController;
    ObservableList<String> _voiceTypeList;


    @FXML
    private ChoiceBox<String> _voiceSelect;
    @FXML
    private Button _okButton;


    public void cancelButtonPressed(){
        _myParentScreensController.setScreen(Main.Screen.TITLE);
    }

    public void okButtonPressed() throws IOException {
        //TODO: save changes and go to title screen
        if(getChoice(_voiceSelect).equals("Default")){
            String cmd = "sed -i \"1s/.*/(voice_kal_diphone)/\" ./src/resources/festival.scm";
            System.out.println(cmd);
            ProcessBuilder pb = new ProcessBuilder("bash","-c",cmd);
            Process process = pb.start();
        }
        else if(getChoice(_voiceSelect).equals("New Zealand")){
            String cmd = "sed -i \"1s/.*/(voice_akl_nz_jdt_diphone)/\" ./src/resources/festival.scm";
            ProcessBuilder pb = new ProcessBuilder("bash","-c",cmd);
            Process process = pb.start();
        }
        _myParentScreensController.setScreen(Main.Screen.TITLE);
    }



    public void clearStatsButtonPressed(){
        _myParentScreensController.requestClearStats();
    }

    /**
     * Sets the parent controller to the MasterController. Then gets a reference to the spelling database object
     * @param screenParent
     */
    @Override
    public void setScreenParent(MasterController screenParent) {
        _myParentScreensController = screenParent;
    }

    private String getChoice(ChoiceBox<String> _voiceSelect){
        return _voiceSelect.getValue();
    }

    @Override
    public void setup() {
        _voiceTypeList = FXCollections.observableArrayList("Default","New Zealand");
        _voiceSelect.setItems(_voiceTypeList);
        _voiceSelect.setValue(_myParentScreensController.getDatabase().get_voice());
    }


}
