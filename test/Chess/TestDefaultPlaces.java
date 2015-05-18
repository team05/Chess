/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Chess;

import model.ChessFigure;
import model.ChessModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrik
 */
public class TestDefaultPlaces {
    
    @Test
    public void testDefaultFiguresPlace() {
        ChessModel myUnit = new ChessModel();
        myUnit.setDefaultFigures();
        
        
        //dark
        assertTrue("test failed at default dark figure's place",(myUnit.getFigure(0, 0)).getType() == ChessFigure.TypeOfFigure.ROOK); // instanceof Rook);
        assertTrue("test failed at default dark figure's place",(myUnit.getFigure(1, 0)).getType() == ChessFigure.TypeOfFigure.KNIGHT); // instanceof Knight);
        assertTrue("test failed at default dark figure's place",(myUnit.getFigure(2, 0)).getType() == ChessFigure.TypeOfFigure.BISHOP); // instanceof Bishop);
        assertTrue("test failed at default dark figure's place",(myUnit.getFigure(3, 0)).getType() == ChessFigure.TypeOfFigure.QUEEN); // instanceof Queen);
        assertTrue("test failed at default dark figure's place",(myUnit.getFigure(4, 0)).getType() == ChessFigure.TypeOfFigure.KING); // instanceof King);
        assertTrue("test failed at default dark figure's place",(myUnit.getFigure(5, 0)).getType() == ChessFigure.TypeOfFigure.BISHOP); // instanceof Bishop);
        assertTrue("test failed at default dark figure's place",(myUnit.getFigure(6, 0)).getType() == ChessFigure.TypeOfFigure.KNIGHT); // instanceof Knight);
        assertTrue("test failed at default dark figure's place",(myUnit.getFigure(7, 0)).getType() == ChessFigure.TypeOfFigure.ROOK); // instanceof Rook);
        for (int i = 0; i < 8; ++i){
            assertTrue("test failed at default dark figure's place",(myUnit.getFigure(i, 1)).getType() == ChessFigure.TypeOfFigure.PAWN); // instanceof Pawn);
        }
        
        //light
        assertTrue("test failed at default light figure's place",(myUnit.getFigure(0, 7)).getType() == ChessFigure.TypeOfFigure.ROOK); // instanceof Rook);
        assertTrue("test failed at default light figure's place",(myUnit.getFigure(1, 7)).getType() == ChessFigure.TypeOfFigure.KNIGHT); // instanceof Knight);
        assertTrue("test failed at default light figure's place",(myUnit.getFigure(2, 7)).getType() == ChessFigure.TypeOfFigure.BISHOP); // instanceof Bishop);
        assertTrue("test failed at default light figure's place",(myUnit.getFigure(3, 7)).getType() == ChessFigure.TypeOfFigure.QUEEN); // instanceof Queen);
        assertTrue("test failed at default light figure's place",(myUnit.getFigure(4, 7)).getType() == ChessFigure.TypeOfFigure.KING); // instanceof King);
        assertTrue("test failed at default light figure's place",(myUnit.getFigure(5, 7)).getType() == ChessFigure.TypeOfFigure.BISHOP); // instanceof Bishop);
        assertTrue("test failed at default light figure's place",(myUnit.getFigure(6, 7)).getType() == ChessFigure.TypeOfFigure.KNIGHT); // instanceof Knight);
        assertTrue("test failed at default light figure's place",(myUnit.getFigure(7, 7)).getType() == ChessFigure.TypeOfFigure.ROOK); // instanceof Rook);
        for (int i = 0; i < 8; ++i){
            assertTrue("test failed at default light figure's place",(myUnit.getFigure(i, 6)).getType() == ChessFigure.TypeOfFigure.PAWN); // instanceof Pawn);
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
