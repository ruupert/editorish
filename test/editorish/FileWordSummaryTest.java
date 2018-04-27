/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editorish;

import java.lang.reflect.Field;
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
public class FileWordSummaryTest {

    public FileWordSummaryTest() {
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
     * Test of setWord method, of class FileWordSummary.
     */
    @Test
    public void testSetWord() throws IllegalArgumentException, IllegalAccessException {
        System.out.println("setWord");
        String word = "test";
        FileWordSummary instance = new FileWordSummary();
        instance.setWord(word);

        Class<?> privateClass = instance.getClass();

        Field fields[] = privateClass.getDeclaredFields();
        fields[0].setAccessible(true);

        String expResult = String.valueOf(fields[0].get(instance));


        assertEquals(expResult, word);


    }

    /**
     * Test of addOne method, of class FileWordSummary.
     */
    @Test
    public void testAddOne() {
        System.out.println("addOne");
        FileWordSummary instance = new FileWordSummary();
        instance.addOne();
        int expResult = 1;

        assertEquals(expResult, instance.getCount());

    }

    /**
     * Test of setCount method, of class FileWordSummary.
     */
    @Test
    public void testSetCount() {
        System.out.println("setCount");
        int count = 53;
        FileWordSummary instance = new FileWordSummary();
        instance.setCount(count);
        int expResult = 53;

        assertEquals(expResult, instance.getCount());
    }

    /**
     * Test of getCount method, of class FileWordSummary.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        FileWordSummary instance = new FileWordSummary();
        int expResult = 40;
        instance.setCount(40);
        int result = instance.getCount();
        
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class FileWordSummary.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        FileWordSummary instance = new FileWordSummary();
        String expResult = "test 45";
        instance.setWord("test");
        instance.setCount(45);
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of compareTo method, of class FileWordSummary.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        FileWordSummary otherWord = new FileWordSummary();
        otherWord.setWord("test1");
        otherWord.setCount(1);
        FileWordSummary instance = new FileWordSummary();
        instance.setWord("test2");
        instance.setCount(2);
        int expResult = 1;
        int result = instance.compareTo(otherWord);
        assertEquals(expResult, result);
    }
}