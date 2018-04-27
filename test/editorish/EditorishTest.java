/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editorish;

import java.lang.reflect.Field;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
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
public class EditorishTest {

    public EditorishTest() {
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
     * Test of searchWords method, of class Editorish.
     */
    @Test
    public void testSearchWords() throws IllegalArgumentException, IllegalAccessException {
        System.out.println("searchWords");
        String[] words = new String[2];
        words[0] = "java";
        words[1] = "jar";

        Editorish instance = new Editorish();
        Class<?> editorishClass = instance.getClass();


        instance.execute();
        while (!instance.isDone()) {
        }

        int expResult = 229;
        int result = instance.searchWords(words).get(0).getLineCount();

        instance.cancel(true);


        assertEquals(expResult, result);
    }

    /**
     * Test of doInBackground method, of class Editorish.
     */
    @Test
    public void testDoInBackground() throws Exception {
        System.out.println("doInBackground");
        Editorish instance = new Editorish();
        instance.execute();
        Integer expResult = 1;
        Integer result = instance.doInBackground();
        System.out.println(result);
        while (!instance.isDone()) {
        }
        assertEquals(expResult, result);

    }

    /**
     * Test of getDirectory method, of class Editorish.
     */
    @Test
    public void testGetDirectory() {
        System.out.println("getDirectory");
        Editorish instance = new Editorish();
        instance.setDirectory(System.getProperty("user.dir"));
        String expResult = System.getProperty("user.dir");
        String result = instance.getDirectory();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDirectory method, of class Editorish.
     */
    @Test
    public void testSetDirectory() {
        System.out.println("setDirectory");
        String directory = System.getProperty("user.dir");
        Editorish instance = new Editorish();
        instance.setDirectory(directory);
        String expResult = System.getProperty("user.dir");
        String result = instance.getDirectory();
        assertEquals(expResult, result);


    }
}