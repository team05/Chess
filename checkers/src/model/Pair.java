package model;
/**
 * 
 * @author team05
 * \brief
 * Egy int (x,y) parost reprezentalo osztaly
 */
public class Pair {

    private int x;
    private int y;
    /**
     * 
     * @param x
     * @param y 
     * Konstruktor
     */
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * 
     * @return int
     * \brief
     * Visszaadja az X koordinatat.
     */
    public int getX() {
        return x;
    }
     /**
     * 
     * @return int
     * \brief
     * Visszaadja az Y koordinatat.
     */
    public int getY() {
        return y;
    }
}
