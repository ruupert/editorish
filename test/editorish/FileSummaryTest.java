/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editorish;

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
public class FileSummaryTest {
    
    public FileSummaryTest() {
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
     * Test of addWord method, of class FileSummary.
     */
    @Test
    public void testAddWord() {
        System.out.println("addWord");
        String word = "test";
        FileSummary instance = new FileSummary();
        instance.addWord(word);
        int expResult = 1;
        int result = instance.getWord(word);
        assertEquals(result, expResult);
    }

    /**
     * Test of getWord method, of class FileSummary.
     */
    @Test
    public void testGetWord() {
        System.out.println("getWord");
        String word = "test";
        FileSummary instance = new FileSummary();
        instance.addWord(word);
        
        int expResult = 1;
        int result = instance.getWord(word);
        assertEquals(result, expResult);
    }

    /**
     * Test of toString method, of class FileSummary.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        FileSummary instance = new FileSummary();
        instance.setFilename("test");
        instance.setLineCount(2);
        instance.setWordCount(1);
        instance.addWord("testWord");
        String expResult = "test => rows: 2 words:1";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWordCount method, of class FileSummary.
     */
    @Test
    public void testSetWordCount() {
        System.out.println("setWordCount");
        int wordCount = 0;
        FileSummary instance = new FileSummary();
        instance.setWordCount(wordCount);
        int result = instance.getWordCount();
        assertEquals(wordCount, result);

    }

    /**
     * Test of setLineCount method, of class FileSummary.
     */
    @Test
    public void testSetLineCount() {
        System.out.println("setLineCount");
        int lineCount = 32;
        FileSummary instance = new FileSummary();
        instance.setLineCount(lineCount);
        
        assertEquals(instance.getLineCount(), lineCount);
        
    }

    /**
     * Test of setFilename method, of class FileSummary.
     */
    @Test
    public void testSetFilename() {
        System.out.println("setFilename");
        String name = "testname";
        FileSummary instance = new FileSummary();
        instance.setFilename(name);
        
        assertEquals(instance.getFilename(), name);
        
        
    }

    /**
     * Test of getWordCount method, of class FileSummary.
     */
    @Test
    public void testGetWordCount() {
        System.out.println("getWordCount");
        FileSummary instance = new FileSummary();
        int expResult = 5;
        instance.setWordCount(5);
        int result = instance.getWordCount();
        
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getLineCount method, of class FileSummary.
     */
    @Test
    public void testGetLineCount() {
        System.out.println("getLineCount");
        FileSummary instance = new FileSummary();
        int expResult = 7;
        instance.setLineCount(expResult);
        int result = instance.getLineCount();
        assertEquals(expResult, result);

    }

    /**
     * Test of getFilename method, of class FileSummary.
     */
    @Test
    public void testGetFilename() {
        System.out.println("getFilename");
        FileSummary instance = new FileSummary();
        String expResult = "testname";
        instance.setFilename(expResult);
        String result = instance.getFilename();
        
        assertEquals(expResult, result);

        
    }

    /**
     * Test of compareTo method, of class FileSummary.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        FileSummary o = new FileSummary();
        o.setFilename("test1");
        o.addWord("joo1");
        o.setLineCount(5);
        o.setWordCount(6);
        
        FileSummary instance = new FileSummary();
        o.setFilename("test2");
        o.addWord("joo2");
        o.setLineCount(7);
        o.setWordCount(8);
        
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class FileSummary.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        String word = "testisana";
        FileSummary instance = new FileSummary();
        instance.addWord(word);
        
        FileWordSummary comparison = new FileWordSummary();
        comparison.setWord(word);
        comparison.setCount(1);
        
        FileWordSummary expResult = comparison;
        FileWordSummary result = instance.get(word);
        assertEquals(expResult.getCount(), result.getCount());
        
    }

    /**
     * Test of getTotalScore method, of class FileSummary.
     */
    @Test
    public void testGetTotalScore() {
        System.out.println("getTotalScore");
        FileSummary instance = new FileSummary();
        double expResult = 5.3;
        instance.addToTotalScore(expResult);
        double result = instance.getTotalScore();
        assertEquals(expResult, result, 5.3);

    }

    /**
     * Test of addToTotalScore method, of class FileSummary.
     */
    @Test
    public void testAddToTotalScore() {
        System.out.println("addToTotalScore");
        
        double expResult = 18.42;
        FileSummary instance = new FileSummary();
        instance.addToTotalScore(7.5);
        instance.addToTotalScore(1.5);
        instance.addToTotalScore(8.905);
        instance.addToTotalScore(0.095);
        instance.addToTotalScore(0.42);
        
        assertEquals(expResult, instance.getTotalScore(), 18.42);
    }

    /**
     * Test of resetTotalScore method, of class FileSummary.
     */
    @Test
    public void testResetTotalScore() {
        System.out.println("resetTotalScore");
        FileSummary instance = new FileSummary();
        
        instance.addToTotalScore(7.5);
        instance.addToTotalScore(1.5);
        instance.addToTotalScore(8.905);
        instance.addToTotalScore(0.095);
        instance.addToTotalScore(0.42);

        instance.resetTotalScore();
        double expResult = 0.0;
        assertEquals(expResult, instance.getTotalScore(), 0.0);
    }

    /**
     * Test of getIteratorNumber method, of class FileSummary.
     */
    @Test
    public void testGetIteratorNumber() {
        System.out.println("getIteratorNumber");
        FileSummary instance = new FileSummary();
        int expResult = 7;
        instance.setIteratorNumber(expResult);
        int result = instance.getIteratorNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIteratorNumber method, of class FileSummary.
     */
    @Test
    public void testSetIteratorNumber() {
        System.out.println("setIteratorNumber");
        int iteratorNumber = 5;
        FileSummary instance = new FileSummary();
        instance.setIteratorNumber(iteratorNumber);
        assertEquals(iteratorNumber, instance.getIteratorNumber());
    }
}