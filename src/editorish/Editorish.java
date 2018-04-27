
package editorish;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author Raul Becker
 * 
 * @version 1.0
 *  
 * @since 17.3.2014
 * 
 * @see FileSummary 
 */
public class Editorish extends SwingWorker<Integer, String> {

    private static String directory;
    private static FileReader fileReader;
    private static BufferedReader reader;
    private static Vector<FileSummary> dataStruct;
    private static int filesRead, fileCount, wordCount;
    private static String[] words;
    private static JProgressBar localProgressBar;
    private static TreeSet<String> fileList;
    private JLabel filesScannedLabelQty, fileCountLabel, wordsScannedLabelQty;

    private static File openFile(String filename) {
        // tässä sitten pitää try catchaa...         
        File f = new File(filename);
        return f;
    }

    private static void addToFileList(String filename) {
        fileList.add(filename);


    }

    private static void readFile(File inputFile) {
        try {
            fileReader = new FileReader(inputFile);
            reader = new BufferedReader(fileReader);
            int rowCount = 0, tempWordCount = 0, currentBatchRow = 0;
            String row;

            FileSummary tempSummary = new FileSummary();
            tempSummary.setFilename(inputFile.toString());
            tempSummary.setWordCount(tempWordCount);
            tempSummary.setLineCount(rowCount);

            dataStruct.add(tempSummary);

            while ((row = reader.readLine()) != null) {
                if (currentBatchRow == 2000) {
                    Thread.sleep(50);
                    currentBatchRow = 0;
                } else {

                    rowCount++;
                    words = row.toLowerCase().split(" ");
                    tempWordCount += words.length;

                    setWordCount(getWordCount() + words.length);

                    for (String word : words) {
                        dataStruct.lastElement().setLineCount(tempWordCount);
                        dataStruct.lastElement().setWordCount(tempWordCount);
                        dataStruct.lastElement().addWord(word);
                    }
                    currentBatchRow++;
                }
            }
            setFilesRead(getFilesRead() + 1);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Editorish.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Editorish.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static int getFileCount() {
        return fileCount;
    }

    private static void recurseList(String targetDirectoryName) {
        try {

            File targetDirectory = openFile(targetDirectoryName);
            for (File f : targetDirectory.listFiles()) {


                if (f.isDirectory()) {
                    recurseList(f.getAbsolutePath());

                } else {

                    readFile(f);
                    localProgressBar.setValue(filesRead);
                    localProgressBar.repaint();
                }
            }

        } catch (NullPointerException e) {
            // ei tehdä mitään.
        }
    }

    /**
     * @return the filesRead
     */
    private static int getFilesRead() {
        return filesRead;
    }

    /**
     * @param aFilesRead the filesRead to set
     */
    private static void setFilesRead(int aFilesRead) {
        filesRead = aFilesRead;
    }

    /**
     * @param aFileCount the fileCount to set
     */
    private static void setFileCount(int aFileCount) {
        fileCount = aFileCount;
    }

    /**
     * @return the wordCount
     */
    private static int getWordCount() {
        return wordCount;
    }

    /**
     * @param aWordCount the wordCount to set
     */
    private static void setWordCount(int aWordCount) {
        wordCount = aWordCount;
    }

    public Editorish() {
        this.fileCountLabel = new JLabel();
        this.filesScannedLabelQty = new JLabel();
        this.wordsScannedLabelQty = new JLabel();
        fileList = new TreeSet<>();
        localProgressBar = new JProgressBar();
        localProgressBar.setMinimum(0);
        dataStruct = new Vector<>();
        directory = String.valueOf(System.getProperty("user.dir"));

        filesRead = 0;
        fileCount = 0;
        wordCount = 0;
    }

    public Editorish(final JLabel filesScannedLabelQty, final JLabel fileCountLabel, final JLabel wordsScannedLabelQty, JProgressBar progressBar) {
        this.fileCountLabel = fileCountLabel;
        this.filesScannedLabelQty = filesScannedLabelQty;
        this.wordsScannedLabelQty = wordsScannedLabelQty;
        this.localProgressBar = progressBar;
        localProgressBar.setMinimum(0);

        dataStruct = new Vector<>();
        directory = String.valueOf(System.getProperty("user.dir"));

        filesRead = 0;
        fileCount = 0;
        wordCount = 0;
    }

    private int getFilesScanned() {
        return getFilesRead();
    }

        private void resetFilesScanned() {
        setFilesRead(0);
        setFileCount(0);
        setWordCount(0);
    }

    private Vector<FileSummary> findWord(String word, int power) {
        FileSummary tempSummary;
        double multiplier;
        int iterator = power;
        power += 1;
        if (power == 1) {
            multiplier = 1;
        } else {
            multiplier = 0.8;
        }

        double value;
        Vector<FileSummary> tempList = new Vector<>();


        for (int i = 0; i < dataStruct.size(); i++) {

            tempSummary = dataStruct.get(i);
            tempSummary.setIteratorNumber(iterator);
            try {
                if (tempSummary.get(word).getCount() > 0) {
                    System.out.println(tempSummary.getWord(word));
                    System.out.println(Math.pow(power, multiplier));
                    value = Double.valueOf(tempSummary.getWord(word)) * Math.pow(multiplier, power);



                    tempSummary.addToTotalScore(value);
                    tempList.add(tempSummary);

                    System.out.println(tempSummary.getWord(word) + (int) value);
                }


            } catch (NullPointerException e) {
            }




        }

        return tempList;

    }

    public Vector<FileSummary> searchWords(String[] words) {
        FileSummary tempSummary;

        Vector<FileSummary> resultList = new Vector<>();

        HashMap<String, FileSummary> tempMap = new HashMap<>();


        for (int i = 0; i < words.length; i++) {
            Vector<FileSummary> tempList = new Vector<>();

            tempList = findWord(words[i], i);

            for (int j = 0; j < tempList.size(); j++) {
                tempSummary = tempMap.get(tempList.get(j).getFilename());

                if (tempMap.containsKey(tempList.get(j).getFilename()) == true) {

                    System.out.println(tempList.get(j).getFilename());
                    System.out.println(tempList.get(j).getTotalScore());
                    try {
                        tempSummary.addToTotalScore(tempMap.get(j).getTotalScore());
                    } catch (NullPointerException e) {
                    }


                    tempMap.remove(tempList.get(j).getFilename());
                    tempMap.put(tempSummary.getFilename(), tempSummary);

                } else {
                    try {
                        tempSummary.addToTotalScore(tempMap.get(j).getTotalScore());
                    } catch (NullPointerException e) {
                    }

                    tempMap.put(tempList.get(j).getFilename(), tempList.get(j));
                }


            }

        }

        for (FileSummary value : tempMap.values()) {
            resultList.add(value);

        }
        Collections.sort(resultList);
        return resultList;
    }


    private void countFiles(String targetDirectoryName) {

        File targetDirectory = openFile(targetDirectoryName);
        File[] targetFileList = targetDirectory.listFiles();
        for (File f : targetFileList) {
            if (f.isDirectory()) {
                countFiles(f.getAbsolutePath());
            } else {
                setFileCount(getFileCount() + 1);

            }
        }


    }

    /**
     * Swing Worker that scans the given directory files and their contents.
     */
    @Override
    protected Integer doInBackground() throws Exception {
        countFiles(directory);

        localProgressBar.setMaximum(fileCount);

        fileCountLabel.setText(String.valueOf(this.getFileCount()));

        Thread t;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (getFileCount() > getFilesScanned()) {
                    try {
                        Thread.sleep(500);
                        filesScannedLabelQty.setText(String.valueOf(getFilesScanned()));
                        wordsScannedLabelQty.setText(String.valueOf("Sanoja yhteensä: " + getWordCount()));
                        

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Editorish.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        t.start();
        recurseList(directory);
        t.stop();  //  Jostain syystä tämä t.join(); nyt ei tuhoa tuota.

        
        return 1;

    }

    /**
     * @return the directory
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * @param directory the directory to set
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
