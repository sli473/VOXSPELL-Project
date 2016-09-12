package data;

import java.io.*;

/**
 * Created by Yuliang Zhou on 8/09/2016.
 */
public class SpellingDatabase {

    private SpellingStatsModel _data;

    private File _hiddenFile;
    private File _wordListFile;

    public SpellingDatabase(){
        _hiddenFile = new File(".spellingData.ser");
        _wordListFile = new File("NZCER-spelling-lists.txt");
    }

    /**
     * Upon creating the GUI frame open the saved data if it exists.
     * Otherwise create a new object.
     * Only called in initialisation of main GUI frame.
     */
    public void openData() {
        if(_hiddenFile.exists()){
            try {
                FileInputStream streamIn = new FileInputStream(".spellingData.ser");
                ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
                _data = (SpellingStatsModel) objectinputstream.readObject();
                streamIn.close();
                objectinputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{//if serialized data does not exist then create a new object
            _data = new SpellingStatsModel();
            System.out.println("New hidden object created");
        }
        //TODO: do we need to constantly update wordlist?
        updateWordList();
    }

    /**
     * Saves the SpellingStatsModel object instance to a hidden .ser file.
     * Called when main GUI frame is closed.
     */
    public void writeData(){
        if(!_hiddenFile.exists()){ //create hidden .ser file if it does not exist
            try {
                _hiddenFile.createNewFile();
            } catch (IOException e) {
                //error creating hidden .ser file
            }
        }
        //write SpellingStatsModel object instance's data to the hidden file
        try {
            FileOutputStream fout = new FileOutputStream(".spellingData.ser", false);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(_data);
            fout.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * //TODO: need to be able to handle different levels
     * Reads each line of "wordlist" file and adds any new words to current word list object in SpellingStatsModel
     */
    public void updateWordList(){
        try {
            FileReader fr = new FileReader(_wordListFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!=null){
                //_data.addNewWord(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
