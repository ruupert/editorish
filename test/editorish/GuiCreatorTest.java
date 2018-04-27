/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editorish;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
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
public class GuiCreatorTest {
    
    public GuiCreatorTest() {
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
     * Test of getMenuItem method, of class GuiCreator.
     */
    @Test
    public void testGetMenuItem() {
        System.out.println("getMenuItem");
        String text = "Testi";
        String actionCommand = "TestiAction";
        String expResult;
        
        JMenuItem result = GuiCreator.getMenuItem(text, actionCommand);
        
        expResult = "TestiAction";
        assertEquals(expResult, result.getActionCommand());
        
        expResult = "Testi";
        assertEquals(expResult, result.getText());
        
        
    }

    /**
     * Test of getProgressBar method, of class GuiCreator.
     */
    @Test
    public void testGetProgressBar() {
        System.out.println("getProgressBar");
        int value = 0;
        boolean stringPainted = true;
        int sizeX = 40;
        int sizeY = 20;
        int top = 3;
        int left = 3;
        int bottom = 3;
        int right = 3;
        int expResult;
        
        JProgressBar result = GuiCreator.getProgressBar(value, stringPainted, sizeX, sizeY, top, left, bottom, right);        
        
        expResult = 0;
        
        assertEquals(expResult, result.getValue());
        expResult = 40;
        assertEquals(expResult, (int) result.getSize().getWidth());
        expResult = 20;
        assertEquals(expResult, (int) result.getSize().getHeight());
        
        
    }
}