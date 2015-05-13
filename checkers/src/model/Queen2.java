package model;

public class Queen2 extends CheckersFigure {

    public Queen2(int posX, int posY, boolean dark) {
        super(posX, posY, dark);
    }

  //hova léphet
    public boolean canMoveTo(int x, int y) {
        if (posX + 1 == x && posY + 1 == y) {
            return true;
        }
        if (posX - 1 == x && posY + 1 == y) {
            return true;
        }
        if (posX + 1 == x && posY - 1 == y) {
            return true;
        }
        if (posX - 1 == x && posY - 1 == y) {
            return true;
        }
        return false;
    }

}
