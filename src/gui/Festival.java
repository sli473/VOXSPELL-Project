package gui;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * Created by samule on 19/09/16.
 */
public class Festival extends Service<Void> {

    private static String _cmd;
    private static ProcessBuilder pb;
    private Process process;

    @Override
    protected Task<Void> createTask() {

        return new Task<Void>(){

            @Override
            protected Void call() throws Exception {
                process = pb.start();
                enablebutton(true);
                process.waitFor();
                enablebutton(false);
                return null;
            }
        };

    }
    public void enablebutton(boolean value){
        if (value == true){
            QuizScreenController.enableEntering();
        }
        else if(value == false){
            QuizScreenController.disableEntering();
            System.out.println("work");
        }
    }


    public static void set_phrase(String phrase) {
        _cmd = "sed -i '$d' ./src/resources/festival.scm ; echo \"(SayText \\\"" + phrase + "\\\")\">>./src/resources/festival.scm ; festival -b ./src/resources/festival.scm";
        pb = new ProcessBuilder("/bin/bash","-c",_cmd);
    }
}
