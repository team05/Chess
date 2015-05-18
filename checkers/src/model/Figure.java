package model;

/**
 *
 * @author team05 \brief A babut reprezentalo osztaly.
 */
public abstract class Figure {

    boolean dark;
    int posX;
    int posY;

    /**
     *
     * @param posX
     * @param posY
     * @param dark Konstruktor.
     */
    Figure(int posX, int posY, boolean dark) {
        this.posX = posX;
        this.posY = posY;
        this.dark = dark;
    }

    /**
     *
     * @return int \brief Visszaadja az X koordinatat.
     */
    public int getPosX() {
        return posX;
    }

    /**
     *
     * @return int \brief Visszaadja az Y koordinatat.
     */
    public int getPosY() {
        return posY;
    }

    /**
     *
     * @return int \brief Beallitja az X koordinatat.
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     *
     * @return int \brief Beallitja az Y koordinatat.
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     *
     * @return int \brief Visszaadja az isDark logikai valtozo erteket.
     */
    public boolean isDark() {
        return dark;
    }

}
