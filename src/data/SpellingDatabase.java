package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yuliang Zhou on 12/09/2016.
 */
public class SpellingDatabase implements Serializable{

    private static final long serialVersionUID = 1L;

    private HashMap< String, ArrayList<Word> > _spellingWords;

    public SpellingDatabase(){
        _spellingWords = new HashMap<>();
    }

    public void addNewWord(String levelKey, String line) {


    }
}
