/**
A figurakhoz szukseges absztrakt osztaly.
Attributumai az X,Y poziciok es a szine.
*/

package model;

public abstract class Figure {

    boolean dark;
    int posX;
    int posY;

    Figure(int posX, int posY, boolean dark) {
        this.posX = posX;
        this.posY = posY;
        this.dark = dark;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isDark() {
        return dark;
    }

   // public abstract boolean canMoveTo(int x, int y);
}
