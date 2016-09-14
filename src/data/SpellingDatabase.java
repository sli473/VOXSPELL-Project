package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Yuliang Zhou on 12/09/2016.
 */
public class SpellingDatabase implements Serializable{

    private static final long serialVersionUID = 1L;

    private HashMap< String, ArrayList<Word> > _spellingWords;
    //TODO add another list for failed objects? or not?

    public int count;

    public SpellingDatabase(){
        _spellingWords = new HashMap<>();
        count = 0;
    }

    /**
     * Adds a new word given as a string to the database with a given level. If the
     * word is already in the list then it is not added. Words are saved in an
     * arraylist for each level which is contained in a hashmap with level being the key.
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
            ArrayList<Word> s = _spellingWords.get(levelKey);
            boolean newWord = true;
            for(Word w : s){
                if(w.toString().equals(word)){
                    newWord = false;
                }
            }
            if(newWord) {//if it is a new word then add it to the spelling list. Else skip.
                _spellingWords.get(levelKey).add(new Word(word));
            }
        }
    }

    /**
     * Debugging purposes only
     */
    public void printDatabase(){
        for (String key : _spellingWords.keySet()) {
            System.out.println(key);
            System.out.println(Arrays.toString(_spellingWords.get(key).toArray()));
        }
    }
}
