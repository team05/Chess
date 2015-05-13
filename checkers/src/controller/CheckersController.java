package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import model.CheckersModel;
import model.Pair;
import model.Queen2;
import model.Stone;
import view.Button;

import view.CheckersView;

public class CheckersController implements java.awt.event.ActionListener {

    CheckersModel model;
    CheckersView view;
    private boolean isSelected = false, nextIsDark = false;
    //bábuk mozgatását segíti
    private List<Pair> canMoveToList = new ArrayList<>();
    private int lastPosX, lastPosY;

    public CheckersController() {
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

        if ((e.getSource().getClass()).toString().equals("class javax.swing.JButton")) {
            //a "kattintott" gomb
            JButton jb = (JButton) e.getSource();
            //ha menü
            if (jb.getText().equals("Back to menu")) {
                view.goToMenu();
            } else if (jb.getText().equals("New Game")) {
                //itt majd új játék
            }
        } else {
            //ha még nincs mező kiválasztva -> kiválasztás
            if (!isSelected) {
                //ha még nem volt kiválasztva, gomb kiválasztás
                Button bu = (view.Button) e.getSource();
                selectFigure(bu);

            } //ha már ki van választva egy mező
            else {
                Button bu = (view.Button) e.getSource();
                //ha a kiválasztott gomb az utolsó x és y pozícióban van, vége a játéknaj
                if (bu.getPosX() == lastPosX && bu.getPosY() == lastPosY) {
                    //kijelölés megszüntetése
                    view.setUnselected(bu);
                    //háttér visszaállítása
                    view.resetBackGrounds();
                    //léphető pozíciók törlése
                    canMoveToList.clear();
                    isSelected = false;
                } else {
                    //ha nem az utolsó pozícióban van, akkor odamozgatjuk a bábut
                    moveFigure(bu);
                }
            }
        }
    }

    //kiválasztjuk a gombot, elmentjük az adatait
    private void selectFigure(Button bu) {
        if (model.getFigure(bu.getPosX(), bu.getPosY()) != null) {
            //mentjük az adatokat
            if (nextIsDark == model.getFigure(bu.getPosX(), bu.getPosY()).isDark()) {
                lastPosX = bu.getPosX();
                lastPosY = bu.getPosY();
                view.setRed(bu);

                canMoveToList = model.setCanMoveTo(lastPosX, lastPosY);
                //hova léphet a felhasználó
                for (Pair p : canMoveToList) {
                    view.setBlue(view.getButtonAt(p.getX(), p.getY()));
                }

                isSelected = true;
            }
        }
    }
    //mozdítja a figurát
    private void moveFigure(Button bu) {
        Pair it;
        int h = 0;
        int cap = canMoveToList.size();
        //ha még lehet lépni
        while (h < cap) {
            it = canMoveToList.get(h);
            if (it.getX() == bu.getPosX() && it.getY() == bu.getPosY()) {
                //frissítjük a hátteret
                view.resetBackGrounds();
                //beállítjuk a pozícióhoz az ikont
                view.setIcon(bu.getPosX(), bu.getPosY(), view.getIcon(lastPosX, lastPosY));
                view.setIcon(lastPosX, lastPosY, null);
                //beállítjuk a figurát
                model.getFigure(lastPosX, lastPosY).setPosX(bu.getPosX());
                model.getFigure(lastPosX, lastPosY).setPosY(bu.getPosY());
                //elfoglaljuk a pozíciót a gomb-bal
                capture(bu);
                //az elfoglalás után is beállítjuk a pozíciót
                model.setFigure(bu.getPosX(), bu.getPosY(), model.getFigure(lastPosX, lastPosY));
                model.setFigure(lastPosX, lastPosY, null);
                
                becomeQueen(bu);
                //megnézzük, hogy vége van-e már a játéknak
                if (model.isGameOver(true)) {
                    //ha igen, jelezzük
                    view.gameOver(true);
                } else if (model.isGameOver(false)) {
                    view.gameOver(false);
                }
                //lépés lehetőségeket töröljük, visszaállunk a lépés megkezdése állapotba
                canMoveToList.clear();
                cap = 0;
                isSelected = false;
                nextIsDark = !nextIsDark;
                view.setLabel(nextIsDark);
            }
            h++;
        }

    }
    //a gomb elfoglalja a helyét
    public void capture(Button bu) {
        if (lastPosX == bu.getPosX() + 2 && lastPosY == bu.getPosY() + 2) {
            view.setIcon(lastPosX - 1, lastPosY - 1, null);
            model.setFigure(lastPosX - 1, lastPosY - 1, null);
        }

        if (lastPosX == bu.getPosX() + 2 && lastPosY == bu.getPosY() - 2) {
            view.setIcon(lastPosX - 1, lastPosY + 1, null);
            model.setFigure(lastPosX - 1, lastPosY + 1, null);
        }

        if (lastPosX == bu.getPosX() - 2 && lastPosY == bu.getPosY() - 2) {
            view.setIcon(lastPosX + 1, lastPosY + 1, null);
            model.setFigure(lastPosX + 1, lastPosY + 1, null);
        }

        if (lastPosX == bu.getPosX() - 2 && lastPosY == bu.getPosY() + 2) {
            view.setIcon(lastPosX + 1, lastPosY - 1, null);
            model.setFigure(lastPosX + 1, lastPosY - 1, null);
        }
    }
    //,,királynővé" válik a gomb
    public void becomeQueen(Button bu) {
        if (model.getFigure(bu.getPosX(), bu.getPosY()) instanceof Stone) {
            if (model.getFigure(bu.getPosX(), bu.getPosY()).isDark()) {
                if (bu.getPosY() == 7) {
                    Queen2 q2 = new Queen2(bu.getPosX(), bu.getPosY(), true);
                    model.setFigure(bu.getPosX(), bu.getPosY(), q2);
                    view.setQueen(bu.getPosX(), bu.getPosY(), true);

                }
            } else {
                if (bu.getPosY() == 0) {
                    Queen2 q2 = new Queen2(bu.getPosX(), bu.getPosY(), false);
                    model.setFigure(bu.getPosX(), bu.getPosY(), q2);
                    view.setQueen(bu.getPosX(), bu.getPosY(), false);
                }
            }

        }
    }

    public void addModel(CheckersModel m) {
        this.model = m;
    }

    public void addView(CheckersView v) {
        this.view = v;
    }
}
