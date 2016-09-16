package data;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Yuliang Zhou on 12/09/2016.
 */
public class SpellingDatabase implements Serializable{

    private static final long serialVersionUID = 1L;

    private HashMap< String, ArrayList<Word> > _spellingWords;
    private HashMap< String, ArrayList<Word> > _failedWords;

    public SpellingDatabase(){
        _spellingWords = new HashMap<>();
        _failedWords = new HashMap<>();
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
     * Adds a word from existing spelling word list in the database in a given level to
     * the failed word list. If word is already in failed list then it wont add it again.
     * @param word, level
     */
    public void addFailedWord(String word, String level) {
        ArrayList<Word> levelWords = _spellingWords.get(level);
        for(int i=0;i<levelWords.size();i++){
            if (levelWords.get(i).toString().equals(word)){ //find index of the failed word in spelling list
                if(_failedWords.containsKey(level)){
                    if( _failedWords.get(level).contains(levelWords.get(i)) ){ //if word is already in failed list
                        return;
                    }
                    _failedWords.get(level).add(levelWords.get(i));
                }else{
                    ArrayList<Word> failedLevelWords = new ArrayList<>();
                    failedLevelWords.add(levelWords.get(i));
                    _failedWords.put(level,failedLevelWords);
                }
                break;
            }
        }
    }

    /**
     * Removes a word from the failed list.
     * @param word, level
     */
    public void removeFailedWord(String word,String level) {
        ArrayList<Word> levelFailed = _failedWords.get(level);
        for(int i=0;i<levelFailed.size();i++){
            if(levelFailed.get(i).toString().equals(word)){ // find index of failed word
                levelFailed.remove(i);
                break;
            }
        }
    }

    /**
     * Increments the Word object's mastered count field in the database.
     * @param levelkey
     * @param word
     */
    public void incrementMastered(String levelkey, String word){
        ArrayList<Word> levelwords = _spellingWords.get(levelkey);
        for(Word w : levelwords){
            if(w.toString().toLowerCase().equals(word)){
                w.setMastered(w.getMastered()+1);
            }
        }
    }

    /**
     * Increments the Word object's faulted count field in the database.
     * @param levelkey
     * @param word
     */
    public void incrementFaulted(String levelkey, String word){
        ArrayList<Word> levelwords = _spellingWords.get(levelkey);
        for(Word w : levelwords){
            if(w.toString().toLowerCase().equals(word)){
                w.setFaulted(w.getMastered()+1);
            }
        }
    }

    /**
     * Increments the Word object's failed count field in the database.
     * @param levelkey
     * @param word
     */
    public void incrementFailed(String levelkey, String word){
        ArrayList<Word> levelwords = _spellingWords.get(levelkey);
        for(Word w : levelwords){
            if(w.toString().toLowerCase().equals(word)){
                w.setFailed(w.getMastered()+1);
            }
        }
    }

    /**
     * getNormalQuiz returns 10 random words from a given level as a String array.
     * @param levelKey
     * @return String[] words from level x
     */
    public String[] getNormalQuiz(String levelKey){
        ArrayList<Word> levelWords = _spellingWords.get(levelKey);
        Collections.shuffle(levelWords);
        String[] testList = new String[10];
        for(int i=0;i<10;i++){
            testList[i] = levelWords.get(i).toString();
        }
        return testList;
    }

    /**
     * getReview Quiz returns 10 or less words from a given level as a String array.
     * @param levelKey
     * @return String[] words from level x
     */
    public String[] getReviewQuiz(String levelKey) {
        ArrayList<Word> levelWords = _failedWords.get(levelKey);
        if ( levelWords==null ){
            return new String[0];
        }
        Collections.shuffle(levelWords);
        String[] testList;
        if (levelWords.size()<10){
            testList = new String[levelWords.size()];
            for(int i=0;i<levelWords.size();i++){
                testList[i] = levelWords.get(i).toString();
            }
        }else{
            testList = new String[10];
            for(int i=0;i<10;i++){
                testList[i] = levelWords.get(i).toString();
            }
        }
        return testList;
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
