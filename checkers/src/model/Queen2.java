package model;
/**
 * 
 * @author team05
 * \brief
 * A kirajnot (damat) reprezentalo osztaly.
 */
public class Queen2 extends CheckersFigure {
    /**
     * 
     * @param posX
     * @param posY
     * @param dark 
     * \brief
     * Konstruktor.
     */
    public Queen2(int posX, int posY, boolean dark) {
        super(posX, posY, dark);
    }

  /**
   * 
   * @param x
   * @param y
   * @return boolean
   * \brief
   * Visszaadja, hogy a parameterul kapott koordinatakra lephet-e a babu.
   */
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
