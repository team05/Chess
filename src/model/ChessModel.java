/**
A sakktablat reprezentalo osztaly.
A cells matrix tartalmazza, hogy hol milyen figura van.
lKingPosX, lKingPosY dKingPosX, dKingPosY valtozok a kiralyok poziciojat taroljak.
isInChess valtozo jelzi, hogy sakkban van-e.
A setFigure fuggvenyt hivja a controller, hogy lepjen a babuval.
A createLight és createDark fuggvenyek hozzak letre a jatek kezdoallapotat.
Az isIn fuggveny a gyalogok beeresekor fut le, miutan a jatekos valasztott, hogy milyen figurat szeretne.
A setCanMoveTo megvizsgalja, hogy a parameterkent kapott pozicion levo babu hova lephet, ehhez a tobbi canMoveTo fuggvenyt hasznalja attol fuggoen, hogy milyen babu van ott.
A setInChess megvizsgalja, hogy sakkban van-e valamelyik jatekos es beallitja az isInChess valtozo erteket.
*/

package model;

import java.util.ArrayList;
import java.util.List;
import model.ChessFigure.TypeOfFigure;

public class ChessModel extends java.util.Observable {

    private int lKingPosX, lKingPosY, dKingPosX, dKingPosY;
    private ChessFigure[][] cells = new ChessFigure[8][8];
    private boolean isInChess;

    public void setDefaultFigures() {
        createLight();
        createDark();
    }

    public void setFigure(int x, int y, ChessFigure f) {
        cells[x][y] = f;
      if (f != null) {
        if (f.getType() == TypeOfFigure.KING) { //f instanceof King) {
            if (f.isDark()) {
                setDarkKingPos(x, y);
            } else {
                setLightKingPos(x, y);
            }
        }
      }
    }

    public void createLight() {
        cells[0][7] = new ChessFigure(0,7,false, "ROOK"); // new Rook(0, 7, false);
        cells[1][7] = new ChessFigure(1,7,false, "KNIGHT"); //new Knight(1, 7, false);
        cells[2][7] = new ChessFigure(2,7,false, "BISHOP"); //new Bishop(2, 7, false);
        cells[3][7] = new ChessFigure(3,7,false, "QUEEN"); //new Queen(3, 7, false);
        cells[4][7] = new ChessFigure(0,7,false, "KING"); //new King(4, 7, false);
        lKingPosX = 4;
        lKingPosY = 7;
        cells[5][7] = new ChessFigure(5,7,false, "BISHOP"); //new Bishop(5, 7, false);
        cells[6][7] = new ChessFigure(6,7,false, "KNIGHT"); //new Knight(6, 7, false);
        cells[7][7] = new ChessFigure(7,7,false, "ROOK"); //new Rook(7, 7, false);
        for (int i = 0; i < 8; ++i) {
            cells[i][6] = new ChessFigure(i,6,false, "PAWN"); //new Pawn(i, 6, false);
        }
    }

    public void createDark() {
        cells[0][0] = new ChessFigure(0,0,true, "ROOK"); //new Rook(0, 0, true);
        cells[1][0] = new ChessFigure(1,0,true, "KNIGHT"); //new Knight(1, 0, true);
        cells[2][0] = new ChessFigure(2,0,true, "BISHOP"); //new Bishop(2, 0, true);
        cells[3][0] = new ChessFigure(3,0,true, "QUEEN"); //new Queen(3, 0, true);
        cells[4][0] = new ChessFigure(4,0,true, "KING"); //new King(4, 0, true);
        dKingPosX = 4;
        dKingPosY = 0;
        cells[5][0] = new ChessFigure(5,0,true, "BISHOP"); //new Bishop(5, 0, true);
        cells[6][0] = new ChessFigure(6,0,true, "KNIGHT"); //new Knight(6, 0, true);
        cells[7][0] = new ChessFigure(7,0,true, "ROOK"); //new Rook(7, 0, true);
        for (int i = 0; i < 8; ++i) {
            cells[i][1] = new ChessFigure(i,1,true, "PAWN"); //new Pawn(i, 1, true);
        }
    }

    public void isIn(int x, int y, String choosedFigure) {
        switch (choosedFigure) {
            case ("Queen"): {
                if (y == 0) {
                    cells[x][y].setType(TypeOfFigure.QUEEN); //new ChessFigure(x, y, false, "QUEEN"); //new Queen(x, y, false);
                } else {
                    cells[x][y].setType(TypeOfFigure.QUEEN); // = new Queen(x, y, true);
                }
                break;
            }

            case ("Rook"): {
                if (y == 0) {
                    cells[x][y].setType(TypeOfFigure.ROOK); // = new Rook(x, y, false);
                } else {
                    cells[x][y].setType(TypeOfFigure.ROOK); // = new Rook(x, y, true);
                }
                break;
            }

            case ("Bishop"): {
                if (y == 0) {
                    cells[x][y].setType(TypeOfFigure.BISHOP); // = new Bishop(x, y, false);
                } else {
                    cells[x][y].setType(TypeOfFigure.BISHOP); // = new Bishop(x, y, true);
                }
                break;
            }

            case ("Knight"): {
                if (y == 0) {
                    cells[x][y].setType(TypeOfFigure.KNIGHT); // = new Knight(x, y, false);
                } else {
                    cells[x][y].setType(TypeOfFigure.KNIGHT); // = new Knight(x, y, true);
                }
                break;
            }
        }
    }

    public List<Pair> setCanMoveTo(int x, int y) {
        List<Pair> canMoveToList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
              //  System.out.println("x " + x + " " + y + " type: " + cells[x][y].getType());

                if (cells[x][y].getType() == TypeOfFigure.PAWN) { // instanceof Pawn) {
                    if (pawnCanMoveTo(x, y, i, j)) {
                        if (cells[i][j] != null) {
                            if ((cells[i][j].isDark() && (!(cells[x][y].isDark()))) || (!(cells[i][j].isDark()) && (cells[x][y].isDark()))) {

                                Pair p = new Pair(i, j);
                                canMoveToList.add(p);
                            }
                        } else {
                            Pair p = new Pair(i, j);
                            canMoveToList.add(p);
                        }
                    }
                }

                if (cells[x][y].getType() == TypeOfFigure.KNIGHT) { // instanceof Knight) {
                    if (knightCanMoveTo(x, y, i, j)) {
                        if (cells[i][j] != null) {
                            if ((cells[i][j].isDark() && (!(cells[x][y].isDark()))) || (!(cells[i][j].isDark()) && (cells[x][y].isDark()))) {
                                Pair p = new Pair(i, j);
                                canMoveToList.add(p);
                            }
                        } else {
                            Pair p = new Pair(i, j);
                            canMoveToList.add(p);
                        }
                    }
                }

                if (cells[x][y].getType() == TypeOfFigure.BISHOP) { // instanceof Bishop) {
                    if (bishopCanMoveTo(x, y, i, j)) {
                        if (cells[i][j] != null) {
                            if ((cells[i][j].isDark() && (!(cells[x][y].isDark()))) || (!(cells[i][j].isDark()) && (cells[x][y].isDark()))) {
                                Pair p = new Pair(i, j);
                                canMoveToList.add(p);
                            }
                        } else {
                            Pair p = new Pair(i, j);
                            canMoveToList.add(p);
                        }
                    }
                }
                
                if (cells[x][y].getType() == TypeOfFigure.ROOK) { // instanceof Rook) {
                    if (rookCanMoveTo(x, y, i, j)) {
                        if (cells[i][j] != null) {
                            if ((cells[i][j].isDark() && (!(cells[x][y].isDark()))) || (!(cells[i][j].isDark()) && (cells[x][y].isDark()))) {
                                Pair p = new Pair(i, j);
                                canMoveToList.add(p);
                            }
                        } else {
                            Pair p = new Pair(i, j);
                            canMoveToList.add(p);
                        }
                    }
                }

                if (cells[x][y].getType() == TypeOfFigure.QUEEN) { // instanceof Queen) {
                    if (bishopCanMoveTo(x, y, i, j) || rookCanMoveTo(x, y, i, j)) {
                        if (cells[i][j] != null) {
                            if ((cells[i][j].isDark() && (!(cells[x][y].isDark()))) || (!(cells[i][j].isDark()) && (cells[x][y].isDark()))) {
                                Pair p = new Pair(i, j);
                                canMoveToList.add(p);
                            }
                        } else {
                            Pair p = new Pair(i, j);
                            canMoveToList.add(p);
                        }
                    }
                }

                if (cells[x][y].getType() == TypeOfFigure.KING) { // instanceof King) {
                    if (kingCanMoveTo(x, y, i, j)) {
                        if (cells[i][j] != null) {
                            if ((cells[i][j].isDark() && (!(cells[x][y].isDark()))) || (!(cells[i][j].isDark()) && (cells[x][y].isDark()))) {
                                Pair p = new Pair(i, j);
                                canMoveToList.add(p);
                            }
                        } else {
                            Pair p = new Pair(i, j);
                            canMoveToList.add(p);
                        }
                    }
                }

            }
        }

        return canMoveToList;
    }

    public ChessFigure getFigure(int x, int y) {
        return cells[x][y];
    }

    public void setDarkKingPos(int x, int y) {
        dKingPosX = x;
        dKingPosY = y;
    }

    public void setLightKingPos(int x, int y) {
        lKingPosX = x;
        lKingPosY = y;
    }

    public boolean knightCanMoveTo(int fromX, int fromY, int toX, int toY) {

        if (toX == fromX - 1 && toY == fromY - 2) {
            return true;
        }
        if (toX == fromX - 1 && toY == fromY + 2) {
            return true;
        }
        if (toX == fromX + 1 && toY == fromY - 2) {
            return true;
        }
        if (toX == fromX + 1 && toY == fromY + 2) {
            return true;
        }

        if (toX == fromX - 2 && toY == fromY - 1) {
            return true;
        }
        if (toX == fromX - 2 && toY == fromY + 1) {
            return true;
        }
        if (toX == fromX + 2 && toY == fromY - 1) {
            return true;
        }
        if (toX == fromX + 2 && toY == fromY + 1) {
            return true;
        }

        return false;
    }

    public boolean pawnCanMoveTo(int fromX, int fromY, int toX, int toY) {
        ChessFigure f = getFigure(fromX, fromY);
        if (f.isDark()) {
            if (darkPawnCanMoveTo(fromX, fromY, toX, toY)) {
                return true;
            }
        }

        if (!(f.isDark())) {
            if (lightPawnCanMoveTo(fromX, fromY, toX, toY)) {
                return true;
            }
        }

        return false;
    }

    public boolean lightPawnCanMoveTo(int fromX, int fromY, int toX, int toY) {
        if (toX == fromX + 1 && toY == fromY - 1) {
            if ((getFigure(toX, toY) != null) && ((getFigure(toX, toY).isDark()))) {

                return true;
            }
        }
        if (toX == fromX - 1 && toY == fromY - 1) {
            if ((getFigure(toX, toY) != null) && ((getFigure(toX, toY).isDark()))) {
                return true;
            }
        }
        if (toX == fromX && toY == fromY - 1) {
            if ((getFigure(toX, toY)) == null) {
                return true;
            }
        }
        if (toX == fromX && toY == fromY - 2 && fromY == 6) {
            if ((getFigure(toX, toY)) == null && getFigure(toX, toY + 1) == null) {

                return true;
            }
        }

        return false;
    }

    public boolean darkPawnCanMoveTo(int fromX, int fromY, int toX, int toY) {
        if (toX == fromX + 1 && toY == fromY + 1) {
            if ((getFigure(toX, toY) != null) && (!(getFigure(toX, toY).isDark()))) {
                return true;
            }
        }
        if (toX == fromX - 1 && toY == fromY + 1) {
            if ((getFigure(toX, toY) != null) && (!(getFigure(toX, toY).isDark()))) {
                return true;
            }
        }
        if (toX == fromX && toY == fromY + 1) {
            if ((getFigure(toX, toY)) == null) {
                return true;
            }
        }
        if (toX == fromX && toY == fromY + 2 && fromY == 1) {
            if ((getFigure(toX, toY)) == null && getFigure(toX, toY - 1) == null) {
                return true;
            }
        }

        return false;
    }

    public boolean canMoveToDownLeft(int fromX, int fromY, int toX, int toY) {
        if (fromX > toX && fromY < toY) {
            int j = toY - 1;
            for (int i = toX + 1; i < fromX; i++) {
                if (j > 0) {
                    if (getFigure(i, j) != null) {
                        return false;
                    }
                    j--;
                }
            }
        }
        return true;
    }

    public boolean canMoveToUpLeft(int fromX, int fromY, int toX, int toY) {
        if (fromX > toX && fromY > toY) {
            int j = toY + 1;
            for (int i = toX + 1; i < fromX; i++) {
                if (j < 7) {
                    if (getFigure(i, j) != null) {
                        return false;
                    }
                    j++;
                }
            }
        }
        return true;
    }

    public boolean canMoveToUpRight(int fromX, int fromY, int toX, int toY) {
        if (fromX < toX && fromY > toY) {
            int j = toY + 1;
            for (int i = toX - 1; i > fromX; i--) {
                if (j < 7) {
                    if (getFigure(i, j) != null) {
                        return false;
                    }
                    j++;
                }
            }
        }
        return true;
    }

    public boolean canMoveToDownRight(int fromX, int fromY, int toX, int toY) {
        if (fromX < toX && fromY < toY) {
            int j = toY - 1;
            for (int i = toX - 1; i > fromX; i--) {
                if (j > 0) {
                    if (getFigure(i, j) != null) {
                        return false;
                    }
                    j--;
                }
            }
        }
        return true;
    }

    public boolean bishopCanMoveTo(int fromX, int fromY, int toX, int toY) {
        //balra fel
        if (!canMoveToUpLeft(fromX, fromY, toX, toY)) {
            return false;
        }
        //balra le
        if (!canMoveToDownLeft(fromX, fromY, toX, toY)) {
            return false;
        }

        //jobbra fel
        if (!canMoveToUpRight(fromX, fromY, toX, toY)) {
            return false;
        }

        //jobbra le
        if (!canMoveToDownRight(fromX, fromY, toX, toY)) {
            return false;
        }

        
        //
        for (int i = 0; i <= 7; i++) {
            if (fromX + i == toX && fromY + i == toY) {
                return true;
            }
            if (fromX + i == toX && fromY - i == toY) {
                return true;
            }
            if (fromX - i == toX && fromY + i == toY) {
                return true;
            }
            if (fromX - i == toX && fromY - i == toY) {
                return true;
            }
        }
        
        //
        return false; //getFigure(fromX, fromY).canMoveTo(toX, toY);
    }

    public boolean rookCanMoveTo(int fromX, int fromY, int toX, int toY) {
        if (!verticallyCanMoveTo(fromX, fromY, toX, toY)) {
            return false;
        }

        if (!horizontallyCanMoveTo(fromX, fromY, toX, toY)) {
            return false;
        }

        if ((fromX - toX == 0) && (fromY != toY)) {
            return true;
        }
        if ((fromX != toX) && (fromY - toY == 0)) {
            return true;
        }
        
        return false;//getFigure(fromX, fromY).canMoveTo(toX, toY);
    }

    public boolean verticallyCanMoveTo(int fromX, int fromY, int toX, int toY) {
        if (fromX == toX) {
            if (fromY > toY) {
                for (int i = toY + 1; i < fromY; i++) {
                    if (getFigure(fromX, i) != null) {
                        return false;
                    }
                }
            }
            if (toY > fromY) {
                for (int i = toY - 1; i > fromY; i--) {
                    if (getFigure(fromX, i) != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean horizontallyCanMoveTo(int fromX, int fromY, int toX, int toY) {
        if (fromY == toY) {
            if (fromX > toX) {
                for (int i = toX + 1; i < fromX; i++) {
                    if (getFigure(i, fromY) != null) {
                        return false;
                    }
                }
            }
            if (toX > fromX) {
                for (int i = toX - 1; i > fromX; i--) {
                    if (getFigure(i, fromY) != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean kingCanMoveTo(int fromX, int fromY, int toX, int toY) {
        return (Math.abs(fromX - toX) < 2) && (Math.abs(fromY - toY) < 2);
    }

    public void setInChess() {

        isInChess = false;
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                if (getFigure(i, j) != null) {
                    ChessFigure cf = getFigure(i, j);

                    if (cf.isDark() && cf.getType() == TypeOfFigure.ROOK) { // instanceof Rook) {

                        if (rookCanMoveTo(i, j, lKingPosX, lKingPosY)) {
                            isInChess = true;
                        }
                    }

                    if (cf.isDark() && cf.getType() == TypeOfFigure.BISHOP) { // instanceof Bishop) {
                        if (bishopCanMoveTo(i, j, lKingPosX, lKingPosY)) {
                            isInChess = true;
                        }
                    }

                    if (cf.isDark() && cf.getType() == TypeOfFigure.QUEEN) { // instanceof Queen) {
                        if (bishopCanMoveTo(i, j, lKingPosX, lKingPosY) || rookCanMoveTo(i, j, lKingPosX, lKingPosY)) {
                            isInChess = true;
                        }
                    }

                    if (cf.isDark() && cf.getType() == TypeOfFigure.KNIGHT) { // instanceof Knight) {
                        if (knightCanMoveTo(i, j, lKingPosX, lKingPosY)) {
                            isInChess = true;
                        }
                    }

                    if (cf.isDark() && cf.getType() == TypeOfFigure.PAWN) { // instanceof Pawn) {
                        if (pawnCanMoveTo(i, j, lKingPosX, lKingPosY)) {
                            isInChess = true;
                        }
                    }

                    //dark:
                    if (!(cf.isDark()) && cf.getType() == TypeOfFigure.ROOK) { // instanceof Rook) {

                        if (rookCanMoveTo(i, j, dKingPosX, dKingPosY)) {
                            isInChess = true;
                        }
                    }

                    if (!(cf.isDark()) && cf.getType() == TypeOfFigure.BISHOP) { // instanceof Bishop) {
                        if (bishopCanMoveTo(i, j, dKingPosX, dKingPosY)) {
                            isInChess = true;
                        }
                    }

                    if (!(cf.isDark()) && cf.getType() == TypeOfFigure.QUEEN) { // instanceof Queen) {
                        if (bishopCanMoveTo(i, j, dKingPosX, dKingPosY) || rookCanMoveTo(i, j, dKingPosX, dKingPosY)) {
                            isInChess = true;
                        }
                    }

                    if (!(cf.isDark()) && cf.getType() == TypeOfFigure.KNIGHT) { // instanceof Knight) {
                        if (knightCanMoveTo(i, j, dKingPosX, dKingPosY)) {
                            isInChess = true;
                        }
                    }

                    if (!(cf.isDark()) && cf.getType() == TypeOfFigure.PAWN) { // instanceof Pawn) {
                        if (pawnCanMoveTo(i, j, dKingPosX, dKingPosY)) {
                            isInChess = true;
                        }
                    }

                }
            }
        }

    }

    public boolean getIsInChess() {
        return isInChess;
    }

    public void setCell(ChessFigure cf, int x, int y) {
        cells[x][y] = cf;
    }

}
