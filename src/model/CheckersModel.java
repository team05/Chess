package model;

import java.util.ArrayList;
import java.util.List;

public class CheckersModel extends java.util.Observable {

    private int lKingPosX, lKingPosY, dKingPosX, dKingPosY;
    private CheckersFigure[][] cells = new CheckersFigure[8][8];
    private boolean isInChess, canCapture;

  

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

    public void setFigure(int x, int y, CheckersFigure f) {
        cells[x][y] = f;
    }

    public boolean isGameOver(boolean dark) {
        boolean empty = true;
        if (dark) {
            for (int x = 0; x < 7; x++) {
                for (int y = 0; y < 7; y++) {
                    if (cells[x][y] != null && cells[x][y].isDark()) {
                        if (!(setCanMoveTo(x, y).isEmpty())) {
                            empty = false;
                        }
                    }
                }
            }
        } else {
            for (int x = 0; x < 7; x++) {
                for (int y = 0; y < 7; y++) {
                    if (cells[x][y] != null && !(cells[x][y].isDark())) {
                        if (!(setCanMoveTo(x, y).isEmpty())) {
                            empty = false;
                        }
                    }
                }
            }
        }
        return empty;
    }

    public List<Pair> setCanMoveTo(int x, int y) {
        List<Pair> canMoveToList = new ArrayList<>();
        canCapture = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (cells[x][y] instanceof Stone) {
                    if (stoneCanCapture(x, y, i, j)) {
                        Pair p = new Pair(i, j);
                        canMoveToList.add(p);
                        canCapture = true;
                    } else if (stoneCanMove(x, y, i, j) && !canCapture) {
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

                if (cells[x][y] instanceof Queen2) {
                    if (queenCanCapture(x, y, i, j)) {
                        Pair p = new Pair(i, j);
                        canMoveToList.add(p);
                        canCapture = true;
                    }
                    if ((queenCanMove(x, y, i, j)) && !canCapture) {
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

    public CheckersFigure getFigure(int x, int y) {
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

    public boolean stoneCanMove(int fromX, int fromY, int toX, int toY) {
        if (!(getFigure(fromX, fromY).isDark())) {
            //balra fel
            if (toX + 1 == fromX && toY + 1 == fromY) {
                if (getFigure(toX, toY) != null) {

                    return false;
                }
            }

            //jobbra fel
            if (toX == fromX + 1 && toY + 1 == fromY) {
                if (getFigure(toX, toY) != null) {

                    return false;
                }
            }

        } else {
            //balra le
            if (toX + 1 == fromX && toY == fromY + 1) {
                if (getFigure(toX, toY) != null) {

                    return false;
                }
            }

            //jobbra le
            if (toX == fromX + 1 && toY == fromY + 1) {
                if (getFigure(toX, toY) != null) {

                    return false;
                }
            }

            if (fromX == toX && fromY == toY) {
                return false;
            }

        }
        return getFigure(fromX, fromY).canMoveTo(toX, toY);
    }

    public boolean queenCanMove(int fromX, int fromY, int toX, int toY) {

        //balra fel
        if (toX + 1 == fromX && toY + 1 == fromY) {
            if (getFigure(toX, toY) != null) {

                return false;
            }
        }

        //jobbra fel
        if (toX == fromX + 1 && toY + 1 == fromY) {
            if (getFigure(toX, toY) != null) {

                return false;
            }
        }

        //balra le
        if (toX + 1 == fromX && toY == fromY + 1) {
            if (getFigure(toX, toY) != null) {

                return false;
            }
        }

        //jobbra le
        if (toX == fromX + 1 && toY == fromY + 1) {
            if (getFigure(toX, toY) != null) {

                return false;
            }
        }

        if (fromX == toX && fromY == toY) {
            return false;
        }

        return getFigure(fromX, fromY).canMoveTo(toX, toY);
    }

    public boolean stoneCanCapture(int fromX, int fromY, int toX, int toY) {
        if (!(getFigure(fromX, fromY).isDark())) {
            //balra fel
            if (toX == fromX - 2 && toY == fromY - 2) {
                if (getFigure(toX, toY) == null) {
                    if (getFigure(toX + 1, toY + 1) != null) {
                        if (getFigure(toX + 1, toY + 1).isDark()) {
                            return true;
                        }
                    }
                }
            }

            //jobbra fel
            if (toX == fromX + 2 && toY == fromY - 2) {
                if (getFigure(toX, toY) == null) {
                    if (getFigure(toX - 1, toY + 1) != null) {
                        if (getFigure(toX - 1, toY + 1).isDark()) {
                            return true;
                        }
                    }
                }
            }

        } else {
            //balra le
            if (toX == fromX - 2 && toY == fromY + 2) {
                if (getFigure(toX, toY) == null) {
                    if (getFigure(toX + 1, toY - 1) != null) {
                        if (!(getFigure(toX + 1, toY - 1).isDark())) {
                            return true;
                        }
                    }
                }
            }

            //jobbra le
            if (toX == fromX + 2 && toY == fromY + 2) {
                if (getFigure(toX, toY) == null) {
                    if (getFigure(toX - 1, toY - 1) != null) {
                        if (!(getFigure(toX - 1, toY - 1).isDark())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean queenCanCapture(int fromX, int fromY, int toX, int toY) {
        if (!(getFigure(fromX, fromY).isDark())) {
            //balra fel
            if (toX == fromX - 2 && toY == fromY - 2) {
                if (getFigure(toX, toY) == null) {
                    if (getFigure(toX + 1, toY + 1) != null) {
                        if (getFigure(toX + 1, toY + 1).isDark()) {
                            return true;
                        }
                    }
                }
            }

            //jobbra fel
            if (toX == fromX + 2 && toY == fromY - 2) {
                if (getFigure(toX, toY) == null) {
                    if (getFigure(toX - 1, toY + 1) != null) {
                        if (getFigure(toX - 1, toY + 1).isDark()) {
                            return true;
                        }
                    }
                }
            }

            //balra le
            if (toX == fromX - 2 && toY == fromY + 2) {
                if (getFigure(toX, toY) == null) {
                    if (getFigure(toX + 1, toY - 1) != null) {
                        if ((getFigure(toX + 1, toY - 1).isDark())) {
                            return true;
                        }
                    }
                }
            }

            //jobbra le
            if (toX == fromX + 2 && toY == fromY + 2) {
                if (getFigure(toX, toY) == null) {
                    if (getFigure(toX - 1, toY - 1) != null) {
                        if ((getFigure(toX - 1, toY - 1).isDark())) {
                            return true;
                        }
                    }
                }
            }

        } else {

            //balra fel
            if (toX == fromX - 2 && toY == fromY - 2) {
                if (getFigure(toX, toY) == null) {
                    if (getFigure(toX + 1, toY + 1) != null) {
                        if (!(getFigure(toX + 1, toY + 1).isDark())) {
                            return true;
                        }
                    }
                }
            }

            //jobbra fel
            if (toX == fromX + 2 && toY == fromY - 2) {
                if (getFigure(toX, toY) == null) {
                    if (getFigure(toX - 1, toY + 1) != null) {
                        if (!(getFigure(toX - 1, toY + 1).isDark())) {
                            return true;
                        }
                    }
                }
            }

            //balra le
            if (toX == fromX - 2 && toY == fromY + 2) {
                if (getFigure(toX, toY) == null) {
                    if (getFigure(toX + 1, toY - 1) != null) {
                        if (!(getFigure(toX + 1, toY - 1).isDark())) {
                            return true;
                        }
                    }
                }
            }

            //jobbra le
            if (toX == fromX + 2 && toY == fromY + 2) {
                if (getFigure(toX, toY) == null) {
                    if (getFigure(toX - 1, toY - 1) != null) {
                        if (!(getFigure(toX - 1, toY - 1).isDark())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean getIsInChess() {
        return isInChess;
    }

    public boolean getCanCapture() {
        return canCapture;
    }

    public void setCell(CheckersFigure cf, int x, int y) {
        cells[x][y] = cf;
    }

}
