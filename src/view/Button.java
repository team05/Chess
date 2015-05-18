package view;

import javax.swing.JButton;

/**
 * Button used in ChessView
 */
public class Button extends JButton {

    private final int posX;
    private final int posY;

    public Button(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * returns X position
     */
    public int getPosX() {
        return posX;
    }

    /**
     * returns y position
     */
    public int getPosY() {
        return posY;
    }

}
