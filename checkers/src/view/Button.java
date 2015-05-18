package view;

import javax.swing.JButton;

/**
 * 
 * @author team05
 * \brief
 * A jatektablat felepito gombokat reprezentalo osztaly.
 */
public class Button extends JButton {

    private final int posX;
    private final int posY;
    /**
     * 
     * @param posX
     * @param posY 
     * \brief
     * Konstruktor
     */
    public Button(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    /**
     * 
     * @return int - X koord
     * 
     */
    public int getPosX() {
        return posX;
    }
    /**
     * 
     * @return int - Y koord
     */
    public int getPosY() {
        return posY;
    }

}
