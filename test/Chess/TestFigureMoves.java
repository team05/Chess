package Chess;

import controller.ChessController;
import model.ChessFigure;
import model.ChessModel;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import view.ChessView;

/**
 *  Test piece movement.
 */
public class TestFigureMoves {

   
    public void testFigureMoves() {
        /*ChessModel myUnit = new ChessModel();
         myUnit.setDefaultFigures();
        
         ChessFigure cf1 = myUnit.getFigure(0, 0);
         myUnit.setCell(cf1, 0, 1);
         myUnit.setCell(null, 0, 0);
      
         assertEquals("test failed at myUnit.setCell ChessFigure", cf1, myUnit.getFigure(0, 1));
         assertEquals("test failed at myUnit.setCell null", null, myUnit.getFigure(0, 0));*/
       
    }
    
    @Test
    public void notWorkingVersion() {
         ChessModel myModel = new ChessModel();
        ChessView myView = new ChessView();

        myModel.setDefaultFigures();
        myModel.addObserver(myView);

        ChessController myController = new ChessController();
        myController.addModel(myModel);
        myController.addView(myView);
        myView.addController(myController);

      //  Button bu1 = new Button(0, 1);

        myController.selectFigure(0,1);
       // Button bu2 = new Button(0, 3);
        ChessFigure cf1 = myModel.getFigure(0, 1);
        myController.moveFigure(0,2);

        
      //  System.out.println(cf1);
        System.out.println("cf1 "+ cf1.getType() + " " + cf1.isDark());
        System.out.println("cf2 " + myModel.getFigure(0,2).getType() + " " + myModel.getFigure(0,2).isDark());
        boolean b = (cf1.equals(myModel.getFigure(0, 2)));

            //assertEquals(cf1, myModel.getFigure(3, 5));
        assertTrue(b);
    }
}
