package model;

public class Stone extends CheckersFigure {

    public Stone(int posX, int posY, boolean dark) {
        super(posX, posY, dark);
    }

    
    public boolean canMoveTo(int x, int y) {

        if (dark) {
            if (posX + 1 == x && posY + 1 == y) {
                return true;
            }
            if (posX - 1 == x && posY + 1 == y) {
                return true;
            }
        } else {
            if (posX + 1 == x && posY - 1 == y) {
                return true;
            }
            if (posX - 1 == x && posY - 1 == y) {
                return true;
            }
        }
        return false;
    }

}
