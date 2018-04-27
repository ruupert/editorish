/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editorish;

/**
 *
 * @author Raul Becker
 * 
 * @version 1.0
 *  
 * @since 17.3.2014
 * 
 */
public class FileWordSummary implements Comparable<FileWordSummary> {

    private String word;
    private int count;

    public FileWordSummary() {
        count = 0;
    }
    
    /**
     * Sanan yhteenvetoon asetettava sana.
     * 
     * @param word 
     */
    public void setWord(String word) {
        this.word = word;
    }
    
    /**
     * Lisää yhden sanojen lukumäärään.
     */
    public void addOne() {
        this.setCount(this.count + 1);
    }

    /**
     * Asettaa sanojen lukumäärän.
     * 
     * @param count sanojen lukumäärä
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Palauttaa sanojen lukumäärän.
     * 
     * @return sanojen lukumäärä
     */
    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return this.word + " " + this.count;
    }

    @Override
    public int compareTo(FileWordSummary otherWord) {
        try {
            if (this.getCount() > otherWord.getCount()) {
                return 1;
            } else if (this.getCount() < otherWord.getCount()) {
                return -1;
            } else {
                return 0;
            }

        } catch (NullPointerException e) {

            return 1;
        }



    }
}
