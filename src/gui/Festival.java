package gui;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * Created by samule on 19/09/16.
 */
public class Festival extends Service {

    private String _cmd;
    private ProcessBuilder _pb;
    private Process process;

    @Override
    protected void succeeded() {
        super.succeeded();
        System.out.println("SUCCESSFULLY enabled input");
        QuizScreenController.set_enableInput(true);
    }

    @Override
    protected void failed() {
        super.failed();
        System.out.println("ERROR reading word");
        QuizScreenController.set_enableInput(true);
    }

    @Override
    protected Task<Void> createTask() {

        return new Task<Void>(){

            @Override
            protected Void call() throws Exception {
                QuizScreenController.set_enableInput(false);
                System.out.println("DISABLED Input");
                process = _pb.start();
                process.waitFor();
                return null;
            }
        };

    }

    public static String get_phrase() {
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
