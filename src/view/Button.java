package view;

import javax.swing.JButton;

public class Button extends JButton {

    private final int posX;
    private final int posY;

    public Button(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

}
