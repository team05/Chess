/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Chess;

import model.ChessModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hgfdsa
 */
public class TestDefaultColors {
    
    
    @Test
    public void testDefaultFiguresColor() {
        ChessModel myUnit = new ChessModel();
        myUnit.setDefaultFigures();
        
        
        // dark
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                assertTrue("test failed at default dark figure's color",(myUnit.getFigure(j,i).isDark()));
            }
        }
        // light
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertTrue("test failed at default light figure's color",!(myUnit.getFigure(j,i).isDark()));
            }
        }
    }
    
    public TestDefaultColors() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
