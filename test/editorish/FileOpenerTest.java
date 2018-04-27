/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editorish;

import java.util.Vector;
import javax.swing.JTextPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ruupert
 */
public class FileOpenerTest {
    
    public FileOpenerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of doInBackground method, of class FileOpener.
     */
    @Test
    public void testDoInBackground() throws Exception {
        
        editorish.Editorish editorishObject = new editorish.Editorish();
        editorishObject.doInBackground();
        String[] words = new String[1];
        words[0] = "java";
        editorishObject.searchWords(words).get(0);
        
        final JTextPane fileViewer = new JTextPane();
 
        final Vector<FileSummary> tempList = editorishObject.searchWords(words);
        final int index = 0;
        String searchWord = "java";
        
        
        System.out.println("doInBackground");
        
        editorish.FileOpener instance = new editorish.FileOpener(fileViewer, tempList, index, searchWord);
        Integer expResult = 1;
        Integer result = instance.doInBackground();
        assertEquals(expResult, result);
    }
}