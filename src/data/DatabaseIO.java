package data;

import java.io.*;

/**
 * DatabaseIO object manages all object serialization. Reads SpellingDatabase object from .ser file
 * into an object. Writes the SpellingDatabase object into a hidden .ser file.
 * Also reads spelling list text file and appends the words to the SpellingDatabase object upon opening
 * the object.
 * Created by Yuliang Zhou on 8/09/2016.
 */
public class DatabaseIO {

    private File _hiddenFile;
    private File _wordListFile;

    public DatabaseIO(){
        _hiddenFile = new File(".spellingData.ser");
        _wordListFile = new File("NZCER-spelling-lists.txt");
    }

    /**
     * Upon creating the GUI frame open the saved data if it exists.
     * Otherwise create a new object.
     * Only called in initialisation of main GUI frame.
     */
    public SpellingDatabase openData() {
        SpellingDatabase data = null;
        if(_hiddenFile.exists()){
            try {
                FileInputStream streamIn = new FileInputStream(".spellingData.ser");
                ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
                data = (SpellingDatabase) objectinputstream.readObject();
                streamIn.close();
                objectinputstream.close();
                System.out.println("Open old object");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{//if serialized data does not exist then create a new object
            data = new SpellingDatabase();
            System.out.println("New object created");
        }
        updateWordList(data);
        return data;
    }

    /**
     * Saves the SpellingStatsModel object instance to a hidden .ser file.
     * Called when main GUI frame is closed.
     */
    public void writeData(SpellingDatabase data){
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
            oos.writeObject(data);
            fout.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reads each line of "wordlist" file and adds any new words to current word list object in SpellingStatsModel
     */
    public void updateWordList(SpellingDatabase database){
        try {
            FileReader fr = new FileReader(_wordListFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String levelKey = "";
            while((line = br.readLine())!=null){
                if(line.charAt(0) == '%' ){//change level key
                    levelKey = line.substring(1);
                }else{
                    database.addNewWord(levelKey, line);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
