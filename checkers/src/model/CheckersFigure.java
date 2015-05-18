package model;
/**
 * 
 * @author team05
 * \brief
 * A dama jatek figuraja.
 */
public abstract class CheckersFigure extends Figure {
    /**
     * 
     * @param posX
     * @param posY
     * @param dark
     * \brief
     * Konstruktor
     */
    public CheckersFigure(int posX, int posY, boolean dark) {
        super(posX, posY, dark);
    } 
    //hova mozoghat a bábuval
    public boolean canMoveTo(int x, int y) {
        System.out.println(" chessfigure can move to" + x + ", " + y);
        return false;
    }
}
