/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editorish;

import java.util.HashMap;

/**
 *
 * @author Raul Becker
 * 
 * @version 1.0
 *  
 * @since 17.3.2014
 * 
 * @see FileWordSummary 
 */
public class FileSummary implements Comparable<FileSummary> {

    private double totalScore;
    private final HashMap<String, FileWordSummary> words;
    private int rowCount;
    private int wordCount;
    private String filename;
    private FileWordSummary wordSummary;
    private int iteratorNumber;

    public FileSummary() {
        words = new HashMap<>();
        rowCount = 0;
        wordCount = 0;
        filename = "";
        totalScore = 0.0;
        iteratorNumber = 0;
    }
    /**
     * Lisää uuden sanan mikäli sellaista ei vielä löydy. Jos sana
     * jo löytyy, niin sen sanan lukumäärää suoritetaan
     * lukumäärää lisäävä metodi.
     * @param word Lisättävä sana.
     */
    public void addWord(String word) {
        if (this.getWord(word) == 0) {
            wordSummary = new FileWordSummary();
            wordSummary.setWord(word);
            wordSummary.addOne();
            words.put(word, wordSummary);
        } else {
            words.get(word).addOne();
        }
    }
    /**
     * Palauttaa haetun sanan lukumäärän.
     * @param word haettava sana.
     * @return 
     */
    public int getWord(String word) {

        if (words.isEmpty()) {
            return 0;
        } else {
            if (words.get(word) == null) {
                return 0;
            } else {
                return words.get(word).getCount();
            }
        }
    }

    @Override
    public String toString() {
        return this.filename + " => rows: " + this.rowCount + " words:"
                + this.wordCount;
    }
    
    /**
     * Asettaa sanojen lukumäärän.
     * @param wordCount sanojen lukumääärä.
     */
    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }
    
    /**
     * Asettaa tiedoston rivimäärän.
     * @param lineCount Tiedoston rivimäärän.
     */
    public void setLineCount(int lineCount) {
        this.rowCount = lineCount;
    }
    
    /**
     * Asettaa tiedoston nimen.
     * @param name tiedoston absoluuttinen nimi: getAbsolutePath(); 
     */
    public void setFilename(String name) {
        this.filename = name;
    }

    /**
     * Palauttaa sanojen lukumäärän.
     * @return int
     */
    public int getWordCount() {
        return this.wordCount;
    }

    /**
     * Palauttaa rivien lukumäärän.
     * @return int
     */
    public int getLineCount() {
        return this.rowCount;
    }

    /**
     * Palauttaa tiedostonimen.
     * @return String
     */
    public String getFilename() {
        return this.filename;
    }

    @Override
    public int compareTo(FileSummary o) {
        try {
            if (o.getTotalScore() > this.getTotalScore()) {
                return 1;
            } else if (o.getTotalScore() < this.getTotalScore()) {
                return -1;
            } else {
                return 0;
            }

        } catch (NullPointerException e) {
            return 1;
        }

    }
    /**
     * 
     * @param word haettava sana tiedoston sanakirjastosta.
     * @return FileWordSummary
     */
    public FileWordSummary get(String word) {
        FileWordSummary palautus;
        return this.words.get(word);
    }

    /**
     * Palauttaa useamman haun tuottaman yhteenlasketut 
     * sanojen painotetut lukumäärät.
     * 
     * @return totalScore
     */
    public double getTotalScore() {
        return totalScore;
    }

    /**
     * Lisää tiedoston haettujen sanojen painotettuja sanamääriä. Hakutulokset
     * voi järjestää tämän arvon mukaan.     * 
     * 
     * @param totalScore Sanojen yhteislukumäärän yhteenlaskettu arvo
     */
    public void addToTotalScore(double totalScore) {
        if (this.getIteratorNumber() == 0) {
            this.totalScore = totalScore;
        } else if (this.totalScore != totalScore) {
            this.totalScore += totalScore;
        }
    }
    
    /**
     * Nollaa sen hetkisen sanahaun yhteenlasketun määrän.
     */
    public void resetTotalScore() {
        this.totalScore = 0.0;
    }

    /**
     * 
     * @return iteratorNumber 
     */
    public int getIteratorNumber() {
        return iteratorNumber;
    }

    /**
     * @param iteratorNumber asettaa painotetussa haussa sen kuinka monetta sanaa haetaan.
     */
    public void setIteratorNumber(int iteratorNumber) {
        this.iteratorNumber = iteratorNumber;
    }
}
