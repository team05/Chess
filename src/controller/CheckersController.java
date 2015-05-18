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
    private List<Pair> canMoveToList = new ArrayList<>();
    private int lastPosX, lastPosY;

    public CheckersController() {

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if ((e.getSource().getClass()).toString().equals("class javax.swing.JButton")) {
            JButton jb = (JButton) e.getSource();
            if (jb.getText().equals("Back to menu")) {
                view.goToMenu();
            } else if (jb.getText().equals("New Game")) {

            }
        } else {
            if (!isSelected) {
                Button bu = (view.Button) e.getSource();
                selectFigure(bu);

            } else {
                Button bu = (view.Button) e.getSource();

                if (bu.getPosX() == lastPosX && bu.getPosY() == lastPosY) {
                    view.setUnselected(bu);
                    view.resetBackGrounds();
                    canMoveToList.clear();
                    isSelected = false;
                } else {
                    moveFigure(bu);
                }
            }
        }
    }

    private void selectFigure(Button bu) {
        if (model.getFigure(bu.getPosX(), bu.getPosY()) != null) {
            if (nextIsDark == model.getFigure(bu.getPosX(), bu.getPosY()).isDark()) {
                lastPosX = bu.getPosX();
                lastPosY = bu.getPosY();
                view.setRed(bu);

                canMoveToList = model.setCanMoveTo(lastPosX, lastPosY);
                for (Pair p : canMoveToList) {
                    view.setBlue(view.getButtonAt(p.getX(), p.getY()));
                }

                isSelected = true;
            }
        }
    }

    private void moveFigure(Button bu) {
        Pair it;
        int h = 0;
        int cap = canMoveToList.size();

        while (h < cap) {
            it = canMoveToList.get(h);
            if (it.getX() == bu.getPosX() && it.getY() == bu.getPosY()) {

                view.resetBackGrounds();
                view.setIcon(bu.getPosX(), bu.getPosY(), view.getIcon(lastPosX, lastPosY));
                view.setIcon(lastPosX, lastPosY, null);

                model.getFigure(lastPosX, lastPosY).setPosX(bu.getPosX());
                model.getFigure(lastPosX, lastPosY).setPosY(bu.getPosY());

                capture(bu);

                model.setFigure(bu.getPosX(), bu.getPosY(), model.getFigure(lastPosX, lastPosY));
                model.setFigure(lastPosX, lastPosY, null);

                becomeQueen(bu);

                if (model.isGameOver(true)) {
                    view.gameOver(true);
                } else if (model.isGameOver(false)) {
                    view.gameOver(false);
                }

                canMoveToList.clear();
                cap = 0;
                isSelected = false;
                nextIsDark = !nextIsDark;
                view.setLabel(nextIsDark);
            }
            h++;
        }

    }

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

    public void addModel(CheckersModel m) {
        this.model = m;
    }

    public void addView(CheckersView v) {
        this.view = v;
    }

    public void initModel(int x) {
    }

}
