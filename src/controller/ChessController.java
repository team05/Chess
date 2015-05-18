package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import model.ChessFigure;
import model.ChessModel;
import model.Pair;

import view.Button;
import view.ChessView;

/** Controller class */
public class ChessController implements java.awt.event.ActionListener {

    ChessModel model;
    ChessView view;
    private boolean isSelected = false, nextIsDark = false;
    private List<Pair> canMoveToList = new ArrayList<>();
    private int lastPosX, lastPosY;

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
                selectFigure(bu.getPosX(), bu.getPosY());

            } else {
                Button bu = (view.Button) e.getSource();

                if (bu.getPosX() == lastPosX && bu.getPosY() == lastPosY) {
                    view.setUnselected(bu);
                    view.resetBackGrounds();
                    canMoveToList.clear();
                    isSelected = false;
                } else {
                    moveFigure(bu.getPosX(), bu.getPosY());
                }
            }
        }
    }

    /**
     * Selects piece on (x, y)
     * @param x Row (rank) to be selected
     * @param y Column (file) to be selected
     */
    public void selectFigure(int x, int y) {
        
        if (model.getFigure(x,y) != null) {
            if (nextIsDark == model.getFigure(x, y).isDark()) {

                lastPosX = x;
                lastPosY = y;
                
                Button bu = view.getButtonAt(x, y);
                view.setRed(bu);

                canMoveToList = model.setCanMoveTo(lastPosX, lastPosY);
                for (Pair p : canMoveToList) {
                    view.setBlue(view.getButtonAt(p.getX(), p.getY()));
                }
                isSelected = true;
            }
        }
    }

    /**
     * Move piece to (x, y)
     * @param x Rank to move
     * @param y File to move
     */
    public void moveFigure(int x, int y) {

        Pair it;
        int h = 0;
        int cap = canMoveToList.size();

        while (h < cap) {
            it = canMoveToList.get(h);
            if (it.getX() == x && it.getY() == y) {
                

                view.resetBackGrounds();
                view.setIcon(x, y, view.getIcon(lastPosX, lastPosY));
                view.setIcon(lastPosX, lastPosY, null);

                
              //  ChessFigure cf1 = model.getFigure(lastPosX, lastPosY);
                //cf1.setPosX(x);
               // cf1.setPosY(y);

                model.setFigure(x, y, model.getFigure(lastPosX, lastPosY));
                model.setFigure(lastPosX, lastPosY, null);

                Button bu = view.getButtonAt(x, y);
                isIn(bu);
                

                model.setInChess();
                if (model.getIsInChess()) {
                    System.out.println("Chess!" );
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

    
    public void isIn(Button bu) {
        int x = bu.getPosX(); int y = bu.getPosY();
        if (model.getFigure(x, y).getType() == ChessFigure.TypeOfFigure.PAWN)  { // instanceof Pawn) {
            if (model.getFigure(x, y).isDark()) {
                if (y == 7) {
                    String choosedFigure;
                    choosedFigure = view.isInDialog(x, y, true);
                    model.isIn(x, y, choosedFigure);
                }
            } else {
                if (y == 0) {
                    String choosedFigure;
                    choosedFigure = view.isInDialog(x, y, false);
                    model.isIn(x, y, choosedFigure);
                }
            }

        }

    }

    public void addModel(ChessModel m) {
        this.model = m;
    }

    public void addView(ChessView v) {
        this.view = v;
    }
}
