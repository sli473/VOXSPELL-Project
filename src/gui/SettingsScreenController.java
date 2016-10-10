package gui;

import data.SpellingDatabase;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
 * This is the controller for that settingsScreen.fxml. In this Screen it is possible to change the type of voice
 * used by festival. It also has the option of clearing stats.
 * Author: Yuliang Zhou 7/09/2016
 */
public class SettingsScreenController implements ControlledScreen{

    private MasterController _myParentScreensController;
    private ObservableList<String> _voiceTypeList;
    private ObservableList<String> _voiceSpeedList;
    private Festival _festival = new Festival();
    private static BooleanProperty _enableInput;
    private static String _voiceType;
    private static String _voicespeed;


    @FXML
    private Button _testButton;
    @FXML
    private ChoiceBox<String> _voiceSelect;
    @FXML
    private ChoiceBox<String> _voiceSpeed;
    @FXML
    private Button _okButton;


    public void cancelButtonPressed(){
        _myParentScreensController.setScreen(Main.Screen.TITLE);
    }

    /**
     * Once the okay button is pressed, this method
     * @throws IOException
     */
    public void okButtonPressed() throws IOException, InterruptedException {
        _festival.setVoiceType(getChoice(_voiceSelect));
        _festival.setVoiceSpeed(getChoice(_voiceSpeed));
        set_voicespeed(getChoice(_voiceSpeed));
        set_voiceType(getChoice(_voiceSelect));
        _myParentScreensController.getDatabase().set_voice(getChoice(_voiceSelect));
        _myParentScreensController.getDatabase().set_voiceSpeed(getChoice(_voiceSpeed));
        _festival.change_settings("Settings Changed");
        _festival.restart();

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
        _voiceSpeedList = FXCollections.observableArrayList("1.00","1.25","1.50","1.75","2.00");
        _voiceSpeed.setItems(_voiceSpeedList);
        _voiceSpeed.setValue(_myParentScreensController.getDatabase().get_voiceSpeed());
        _enableInput = new SimpleBooleanProperty(this,"_enableInput",true);
        //_submit.disableProperty().bind(_enableInput);
        _enableInput.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(is_enableInput()){
                    _testButton.setDisable(false);

                }else{
                    _testButton.setDisable(true);
                }
            }
        });
    }

    /**
     * when the test button is clicked it will test out the voice settings you've selected.
     */
    public void testFestival() throws IOException, InterruptedException {
        _festival.setVoiceType(getChoice(_voiceSelect));
        _festival.setVoiceSpeed(getChoice(_voiceSpeed));
        set_voicespeed(getChoice(_voiceSpeed));
        set_voiceType(getChoice(_voiceSelect));
        _myParentScreensController.getDatabase().set_voice(getChoice(_voiceSelect));
        _myParentScreensController.getDatabase().set_voiceSpeed(getChoice(_voiceSpeed));
        _festival.change_settings("Testing the current voice settings");
        _festival.restart();
    }

    public void toggleMute(){
        Main.toggleMusic();
    }

    public static boolean is_enableInput() {
        return _enableInput.get();
    }

    public static BooleanProperty _enableInputProperty() {
        return _enableInput;
    }

    public static void set_enableInput(boolean _enableInput) {
        SettingsScreenController._enableInput.set(_enableInput);
    }

    public static void set_voiceType(String input){
        _voiceType = input;
    }

    public static void set_voicespeed(String input){
        _voicespeed = input;
    }

    public static String getFestivalvoice(){
        return _voiceType;
    }

    public static String getFestivalSpeed(){
        return _voicespeed;
    }
}
