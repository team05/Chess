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
 * Test for movement validation.
 */
public class TestCanMoveTo {
    ChessModel myUnit = new ChessModel();
    
    @Test
    public void testCanMoveTo() {
    
        
 
         ChessFigure lightQueen1 = new ChessFigure(3, 4, false, "QUEEN");
         myUnit.setCell(lightQueen1, 3, 4);

      
         // Queen
         testQueenCanMoveTo(0,1);
         testQueenCanMoveTo(1,2);
         testQueenCanMoveTo(2,3);
         testQueenCanMoveTo(4,3);
         testQueenCanMoveTo(5,2);
         testQueenCanMoveTo(6,1);
         testQueenCanMoveTo(2,5);
         testQueenCanMoveTo(1,6);
         testQueenCanMoveTo(0,7);
         testQueenCanMoveTo(4,5);
         testQueenCanMoveTo(5,6);
         testQueenCanMoveTo(6,7);
         
         for (int i = 0; i < 8; i++) {
             testQueenCanMoveTo(3,i);
         }
         
         for (int j = 0; j < 8; j++) {
             testQueenCanMoveTo(j,4);
         }
         
         myUnit.setCell(null, 3, 4);
         
         ChessFigure lightPawn1 = new ChessFigure(0, 6, false, "PAWN");
         myUnit.setCell(lightPawn1, 0, 6);
         
         assertTrue("test failed at pawnCanMoveTo", (myUnit.pawnCanMoveTo(0, 6, 0, 5)));
         assertTrue("test failed at pawnCanMoveTo", (myUnit.pawnCanMoveTo(0, 6, 0, 4)));
         
         myUnit.setCell(null, 0, 6);
         myUnit.setCell(lightPawn1, 0, 5);
         assertTrue("test failed at pawnCanMoveTo", (myUnit.pawnCanMoveTo(0, 5, 0, 4)));
         
         for (int i = 0; i < 8; i++) {
             for (int j = 0; j < 8; j++) {
                 if (!(i == 0 && j == 4)) {
                     assertFalse("test failed at pawnCanMoveTo", (myUnit.pawnCanMoveTo(0, 5, i, j)));
                 }
             }
         }
         
         myUnit.setCell(null, 0, 5);
         
          
       
    }
    
    public void testQueenCanMoveTo(int x, int y) {
       assertTrue("test failed at queenCanMoveTo", (myUnit.bishopCanMoveTo(3, 4, x, y) || myUnit.rookCanMoveTo(3, 4, x, y)));
    }    
}
