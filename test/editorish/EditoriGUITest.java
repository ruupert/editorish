/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editorish;

import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionEvent;
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
public class EditoriGUITest {
    
    public EditoriGUITest() {
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
     * Test of main method, of class EditoriGUI.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        EditoriGUI.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actionPerformed method, of class EditoriGUI.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent e = null;
        EditoriGUI instance = null;
        instance.actionPerformed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueChanged method, of class EditoriGUI.
     */
    @Test
    public void testValueChanged() {
        System.out.println("valueChanged");
        ListSelectionEvent e = null;
        EditoriGUI instance = null;
        instance.valueChanged(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}