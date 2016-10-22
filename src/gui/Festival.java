package gui;

import data.SpellingDatabase;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The Festival class that takes the scm file and reads out a string provided by the QuizScreenController.
 * It should read out the words that need to be read for the spelling quiz. Uses Task to invoke methods for
 * saying the words through festival on the background thread to prevent the GUI from freezing.
 * Created by samule on 19/09/16.
 */
public class Festival extends Service<Void> {

    private String _cmd;
    private ProcessBuilder _pb;
    private Process process;
    private String _voicetype = "voice_kal_diphone";
    private String _voicespeed = "1.00";
    private File _file;


    /**
     *  Enables the submit and enter button on the QuizScreenController after the Task successfully finishes.
     */
    @Override
    protected void succeeded() {
        super.succeeded();
        //enables the enteredWord method and _submit method.
        QuizScreenController.set_enableInput(true);
        SettingsScreenController.set_enableInput(true);
    }

    /**
     *  Enables the submit and enter button on the QuizScreenController if the task fails
     */
    @Override
    protected void failed() {
        super.failed();
        //enables the enteredWord method and _submit method.
        QuizScreenController.set_enableInput(true);
        SettingsScreenController.set_enableInput(true);
    }

    /**
     * Reads out the phrase sent by the QuizScreenController and disables the submit and enter buttons, so the
     * festival voices won't overlap. You can only submit or enter a word once the festival voice finishes.
     * @return
     */
    @Override
    protected Task<Void> createTask() {

        return new Task<Void>(){

            @Override
            protected Void call() throws Exception {
                //disables the enteredWord method and _submit method.
                QuizScreenController.set_enableInput(false);
                SettingsScreenController.set_enableInput(false);
                //starts the process
                process = _pb.start();
                //waits for the process to finish before calling succeeded or failed methodss
                process.waitFor();
                return null;
            }
        };



    }

    public void setVoiceType(String voiceType){
        if (voiceType.equals("Default")) {
            _voicetype = "voice_kal_diphone";
        }
        else{
            _voicetype = "voice_akl_nz_jdt_diphone";
        }
    }

    public void setVoiceSpeed(String voiceSpeed){
        _voicespeed = voiceSpeed;
    }

    public String getVoiceSpeed() {return _voicespeed;}

    public String getVoiceType(){
        if(_voicetype.equals("voice_kal_diphone")){
            return "Default";
        }
        else{
            return "New Zealand";
        }
    }


    /**
     * edits the final line of the Festival.scm, inserts the phrase that needs to be read out by festival into SayText.
     * The ProcessBuilder then reads the Festival.scm file executing all the commands inside.
     * @param phrase
     */
    public void set_phrase(String phrase) {
        BufferedWriter out = null;
        _file = new File("./src/resources/festival.scm");
        try{
            _file.delete();
        }
        catch (Exception e){
        }

        try{
            out = new BufferedWriter(new FileWriter("./src/resources/festival.scm",true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            out.write("("+_voicetype+")");
            out.newLine();
            out.write("(Parameter.set 'Duration_Stretch "+_voicespeed+")" );
            out.newLine();
            out.write("(SayText " + "\"" +phrase+ "\"" + ")");
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        _cmd = "festival -b ./src/resources/festival.scm";
        _pb = new ProcessBuilder("/bin/bash","-c",_cmd);
    }

    //public void set_phrase(String phrase){
    //    _cmd = "sed -i \"3s/.*/(SayText \\\""+phrase+"\\\")/\" ./src/resources/festival.scm ; festival -b ./src/resources/festival.scm";
    //    _pb = new ProcessBuilder("/bin/bash","-c",_cmd);
    //}
}
