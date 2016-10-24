package data;

import gui.DialogBox;

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
    private File _customFile;
    private File _customListFile;

    public DatabaseIO(){
        _hiddenFile = new File(".spellingData.ser");
        _wordListFile = new File("NZCER-spelling-lists.txt");
        _customFile = new File(".customSpellingData.ser");
        _customListFile = new File(".customSpellingList.txt");
    }

    /**
     * Upon creating the GUI frame open the saved data if it exists.
     * Otherwise create a new object.
     * Only called in initialisation of main GUI frame.
     * @return SpellingDatabase
     */
    public SpellingDatabase openData(File spellingData, File spellingList, boolean custom) { // have an input argument of of which List it is
        SpellingDatabase data = null;
        if(spellingData.exists()){
            try {
                FileInputStream streamIn = new FileInputStream(spellingData);
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
        //if(custom && !spellingList.exists()){
        //    return data;
        //}

        updateWordList(data, spellingList, custom);
        return data;
    }


    /**
     * Saves the SpellingStatsModel object instance to a hidden .ser file.
     * Called when main GUI frame is closed.
     * @param data
     */
    public void writeData(SpellingDatabase data, File file){
        if(!file.exists()){ //create hidden .ser file if it does not exist
            try {
                file.createNewFile();
            } catch (IOException e) {
                //error creating hidden .ser file
            }
        }
        //write SpellingStatsModel object instance's data to the hidden file
        try {
            FileOutputStream fout = new FileOutputStream(file, false);
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
     * Reads each line of "wordlist" file and adds any new words to current word list object in SpellingStatsModel.
     * This method is called after reading the data.
     * @param database
     */
    public void updateWordList(SpellingDatabase database, File spellingList, boolean custom){
        try {
            FileReader fr = new FileReader(spellingList); //change it to wordList argument file
            BufferedReader br = new BufferedReader(fr);
            String line;
            String levelKey = "";
            while((line = br.readLine())!=null){
                if(line.charAt(0) == '%' ){//get level key
                    //if(custom){
                        levelKey = line.substring(1);
                    //}
                    //else {
                    //    levelKey = line.substring(1);
                    //}
                }else{
                    database.addNewWord(levelKey, line.trim());
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            DialogBox.errorDialogBox("Error","Could not find spelling list text file. Please refer to README and try again.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void set_customListFile(File file){
        _customListFile = file;
    }

    public File get_hiddenFile(){
        return _hiddenFile;
    }

    public File get_wordListFile(){
        return _wordListFile;
    }

    public File get_customFile(){
        return _customFile;
    }

    public File get_customListFile() { return _customListFile; }

}
