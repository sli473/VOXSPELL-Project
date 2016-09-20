package gui;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * Created by samule on 19/09/16.
 */
public class Festival extends Service<Void> {

    private String _phrase;
    private ProcessBuilder pb;
    private Process process;
    @Override
    protected Task<Void> createTask() {

        return new Task<Void>(){

            @Override
            protected Void call() throws Exception {
                process = pb.start();
                process.waitFor();
                return null;
            }
        };

    }

    public String get_phrase() {
        return _phrase;
    }

    public void set_phrase(String phrase) {
        _phrase = phrase;
        String path = "./resources/festival.scm";
        System.out.println(path);
        String cmd = "sed -i '$d' "+path+" ; echo \"(SayText \\\"" + _phrase + "\\\")\">>"+path+" ; festival -b "+path+"";

        pb = new ProcessBuilder("bash","-c",cmd);
        pb.inheritIO();
    }
}
