package model;

public abstract class CheckersFigure extends Figure {

    public CheckersFigure(int posX, int posY, boolean dark) {
        super(posX, posY, dark);
    } 
    //hova mozoghat a bábuval
    public boolean canMoveTo(int x, int y) {
        System.out.println(" chessfigure can move to" + x + ", " + y);
        return false;
    }
}
