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

    /**
     * Adds a new word given as a string to the database with a given level. Words
     * are saved in an arraylist for each level which is contained in a hashmap with
     * level being the key.
     * e.g. "Level 1" key contains the word "the"
     * @param levelKey
     * @param word
     */
    public void addNewWord(String levelKey, String word) {
        if(!_spellingWords.containsKey(levelKey)){
            ArrayList<Word> wordsInLevel = new ArrayList<>();
            wordsInLevel.add(new Word(word));
            _spellingWords.put(levelKey,wordsInLevel);
        }else{
            _spellingWords.get(levelKey).add(new Word(word));
        }
        
    }
}
