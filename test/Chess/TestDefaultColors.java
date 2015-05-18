package Chess;

import model.ChessModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  Test valid color of figures.
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
}
