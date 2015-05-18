/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Checkers;

import model.CheckersModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestDefaultColors {
    
    
    @Test
    public void testDefaultFiguresColor() {
        CheckersModel myUnit = new CheckersModel();
        myUnit.setDefaultFigures();
        
        
       //dark
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if (((i+j) % 2) == 0) {
                    //paramkent megadott utasitas igaz-e
                    assertTrue("test failed at default dark figure's color", (myUnit.getFigure(j, i).isDark()));
                }
            }
        }
        
        // light
        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (((i+j) % 2) == 0) {
                    //paramkent megadott utasitas hamis-e
                    assertFalse("test failed at default light figure's color", (myUnit.getFigure(j, i).isDark()));
                }
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

}
