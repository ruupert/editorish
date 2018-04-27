/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editorish;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Vector;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Raul Becker
 *
 * @version 1.0
 *
 * @since 17.3.2014
 *
 */
public class FileOpener extends SwingWorker<Integer, String> {

    private int localIndex;
    private JTextPane localFileViewer;
    private Vector<FileSummary> localTempList;
    private StyledDocument doc;
    private String localSearchWord;
    private SimpleAttributeSet[] attrs;

    /**
     * Alustaa tiedostojen lukijan joka asettaa parametreinä saadut arvot luokan
     * omiin muuttujiin.
     *
     * @param fileViewer Graafisen käyttöliittymän tiedostojen näyttämisen
     * tekstialue
     * @param tempList Avattavan tiedoston sisältävä kirjasto.. mitäköhän olen
     * ajatellut
     * @param index Avattavan tiedoston indeksinumero kirjastosta.
     * @param searchWord Hakusanojen merkkijono.
     */
    public FileOpener(final JTextPane fileViewer, final Vector<FileSummary> tempList, final int index, String searchWord) {
        localFileViewer = fileViewer;
        localTempList = tempList;
        localIndex = index;
        doc = localFileViewer.getStyledDocument();
        localSearchWord = searchWord;
        attrs = initializeAttributes(searchWord.split(" ").length);
    }
    /**
     * Tämä metodi hoitaa tiedoston sisällön näyttämisen hakusanat väritettynä käyttöliittymän JTextPaneen.
     * @return kokonaisluku suorituksen päättymisestä eli arvo 1
     * @throws Exception 
     */
    @Override
    protected Integer doInBackground() throws Exception {
        int currentBatchRow = 0;
        String rivi;
        String batchOfRows = "";
        localFileViewer.setText(null);
        boolean wordFound;
        int wordAttrSelector = 0;
        String[] words, rowWords;
        FileInputStream fstream = new FileInputStream(localTempList.get(localIndex).getFilename());
        BufferedReader reader = new BufferedReader(new InputStreamReader(fstream));

        if (localSearchWord.isEmpty()) {
            localSearchWord = "EISIISOLE MITÄÄN HAKUSANAA";
        }
        words = localSearchWord.split(" ");

        while ((rivi = reader.readLine()) != null) {
            if (currentBatchRow == 40) {
                Thread.sleep(60);
                currentBatchRow = 0;
            } else {
                rowWords = rivi.split(" ");

                for (int i = 0; i < rowWords.length; i++) {
                    wordFound = false;
                    for (int j = 0; j < words.length; j++) {
                        if (words[j].equalsIgnoreCase(rowWords[i])) {
                            wordAttrSelector = j + 1;
                            doc.insertString(doc.getLength(), rowWords[i] + " ", attrs[wordAttrSelector]);
                            wordFound = true;
                        } else {
                        }
                    }
                    if (wordFound == false) {
                        wordAttrSelector = 0;
                        doc.insertString(doc.getLength(), rowWords[i] + " ", attrs[wordAttrSelector]);
                    }

                }
                doc.insertString(doc.getLength(), "\n", attrs[wordAttrSelector]);
                wordAttrSelector = 0;
            }
            currentBatchRow++;
        }

        reader.close();
        return 1;
        
    }

    /**
     * Luo perustyylin lisäksi niin monta eri väristä kuin mitä luokan
     * konstruktorin parametrinä annettiin.
     *
     * @param length
     * @return
     */
    private SimpleAttributeSet[] initializeAttributes(int length) {

        length += 1;
        Random rand = new Random();
        float r, g, b;
        Color randomColor;
        SimpleAttributeSet[] attributes = new SimpleAttributeSet[length];


        // perustyyli
        attributes[0] = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attributes[0], "SansSerif");
        StyleConstants.setFontSize(attributes[0], 14);


        // seuraavassa luodaan tyylejä niin monta kuin parametrejä (hakusanoja) on.
        for (int i = 1; i < length; i++) {
            r = rand.nextFloat();
            g = rand.nextFloat();
            b = rand.nextFloat();
            randomColor = new Color(r, g, b);

            attributes[i] = new SimpleAttributeSet(attributes[0]);
            StyleConstants.setBackground(attributes[i], randomColor.brighter().brighter());
        }
        return attributes;
    }
}
