package model;

public class CheckersModel extends java.util.Observable {
    
    //a tábla modellezése
    private CheckersFigure[][] cells = new CheckersFigure[8][8];
 
    //tábla modellje
    public void setDefaultFigures() {
        int z = 0;
        
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 8; i++) {
                if (z % 2 == 0) {
                    cells[i][j] = new Stone(i, j, true);
                }
                z++;
            }
            z++;
        }

        z = 1;
        for (int j = 5; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if (z % 2 == 0) {
                    cells[i][j] = new Stone(i, j, false);
                }
                z++;
            }
            z++;
        }
    }
    
}
