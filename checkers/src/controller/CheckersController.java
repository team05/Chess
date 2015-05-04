package controller;

import javax.swing.JButton;
import model.CheckersModel;

import view.CheckersView;

public class CheckersController implements java.awt.event.ActionListener {

    CheckersModel model;
    CheckersView view;
    private boolean isSelected = false;
    
    public CheckersController() {

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        
        if ((e.getSource().getClass()).toString().equals("class javax.swing.JButton")) {
            //a "kattintott" gomb
            JButton jb = (JButton) e.getSource();
            //ha menü
            if (jb.getText().equals("Back to menu")) {
                //itt majd vissza a menübe
            } else if (jb.getText().equals("New Game")) {
                //itt majd új játék
            }
        } else {
            //ha még nincs mező kiválasztva -> kiválasztás
            if (!isSelected) {
               //...

            } //ha már ki van választva egy mező
            else {
                //...
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
