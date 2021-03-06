package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author team05 \brief A tabla modellje. Logikailag itt zajlik a jatek.
 */
public class CheckersModel extends java.util.Observable {

    //a tábla modellezése
    private CheckersFigure[][] cells = new CheckersFigure[8][8];
    private boolean canCapture;

    /**
     * \brief A standard jatekosok betoltese.
     */
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

    /**
     *
     * @param x
     * @param y
     * @return CheckersFigure \brief Adott poziciorol visszaadja a babu
     * objektumot.
     */
    public CheckersFigure getFigure(int x, int y) {
        return cells[x][y];
    }

    /**
     *
     * @param x
     * @param y
     * @return List - azon poziciok listaja, ahova lephetunk \brief Visszaadja
     * azon poziciok listajat, ahova lephetunk.
     */
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

    /**
     *
     * @param x
     * @param y
     * @param f \brief A tablat reprezentalo matrix adott poziciojat beallitja a
     * parameterul kapott figuranak.
     */
    public void setFigure(int x, int y, CheckersFigure f) {
        cells[x][y] = f;
    }

    /**
     *
     * @param dark
     * @return boolean \brief A jatek veget figyelo fuggveny.
     */
    public boolean isGameOver(boolean dark) {
        boolean empty = true;
        //ha egyik pozícióba sem tud lépni, akkor vége a játéknak
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

    /**
     *
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @return boolean \brief Honnan hova lephetunk figyeleset vegzo fuggeny
     * (alapbabuval).
     */
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

    /**
     *
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @return boolean \brief Honnan hova lephetunk figyeleset vegzo fuggeny
     * (kiralynovel).
     */
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

    /**
     *
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @return boolean \brief Azt figyeli, hogy le tudunk e venni az ellenfel
     * jatekosai kozul. (alapbabuval)
     */
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

    /**
     *
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @return bool \brief Azt figyeli, hogy le tudunk e venni az ellenfel
     * jatekosai kozul. (kiralynovel)
     */
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

}
