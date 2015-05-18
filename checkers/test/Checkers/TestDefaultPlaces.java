/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Checkers;

import model.CheckersModel;
import model.Stone;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author team05
 * \brief
 * Az alapjatekosokat es azok felrakasat tesztelo osztaly.
 */
public class TestDefaultPlaces {
    
    @Test
    public void testDefaultFiguresPlace() {
        CheckersModel myUnit = new CheckersModel();
        myUnit.setDefaultFigures();
        
        
        //dark
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if (((i+j) % 2) == 0) {
                    assertTrue("test failed at default dark figure's place", (myUnit.getFigure(j, i) instanceof Stone));
                }
            }
        }
        
        // light
        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (((i+j) % 2) == 0) {
                    assertTrue("test failed at default light figure's place", (myUnit.getFigure(j, i) instanceof Stone));
                }
            }
        }
    }
    
    public TestDefaultPlaces() {
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
