package model;
/**
 * 
 * @author team05
 * \brief
 * A jatek alapfigurajat reprezentalo osztaly.
 */
public class Stone extends CheckersFigure {
    /**
     * 
     * @param posX
     * @param posY
     * @param dark 
     * \brief
     * Konstruktor.
     * 
     */
    public Stone(int posX, int posY, boolean dark) {
        super(posX, posY, dark);
    }

    /**
     * 
     * @param x
     * @param y
     * @return boolean
     * \brief
     * Visszaadja, hogy a parameterul kapott koordinatakra tud-e lepni a babu.
     */
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
