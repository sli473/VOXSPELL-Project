package data;


import java.io.Serializable;

/**
 *
 * @author Yuliang Zhou yzho746 7/09/2016
 *
 */
public class Word implements Serializable{

    private static final long serialVersionUID = 1L;

    private String _word;
    private int _mastered;
    private int _faulted;
    private int _failed;

    public Word(String word){
        _word = word;
        setMastered(0);
        setFaulted(0);
        setFailed(0);
    }

    public int getMastered() {
        return _mastered;
    }

    public void setMastered(int _mastered) {
        this._mastered = _mastered;
    }

    public int getFaulted() {
        return _faulted;
    }

    public void setFaulted(int _faulted) {
        this._faulted = _faulted;
    }

    public int getFailed() {
        return _failed;
    }

    public void setFailed(int _failed) {
        this._failed = _failed;
    }

    /**
     * Resets the Mastered, Faulted, and Failed count for the word
     */
    public void clear(){
        _mastered = 0;
        _faulted = 0;
        _failed = 0;
    }

    /**
     * Returns true if the word has been attempted, false otherwise.
     * @return
     */
    public boolean attempted(){
        boolean attempted = false;
        if(_mastered == 0){
            if(_faulted == 0){
                if(_failed == 0){
                    attempted = true;
                }
            }
        }
        return attempted;
    }

    /**
     * returns the name of the word
     */
    @Override
    public String toString() {
        return _word;
    }



}

