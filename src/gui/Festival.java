package gui;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * Created by samule on 19/09/16.
 */
public class Festival extends Service {

    private static String _phrase;
    private static ProcessBuilder pb;
    Process process;
    @Override
    protected Task createTask() {

        return new Task<Void>(){

            @Override
            protected Void call() throws Exception {
                process = pb.start();
                process.waitFor();
                return null;
            }
        };

    }

    public static String get_phrase() {
        return _phrase;
    }

    public static void set_phrase(String _phrase) {
        _phrase = _phrase;
        String cmd = "sed -i '$d' ./src/resources/festival.scm ; echo \"(SayText \\\"" + _phrase + "\\\")\">>./src/resources/festival.scm ; festival -b ./src/resources/festival.scm";

        pb = new ProcessBuilder("bash","-c",cmd);
    }
}
