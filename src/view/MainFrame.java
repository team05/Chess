package view;

import controller.CheckersController;
import controller.ChessController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import model.CheckersModel;
import model.ChessModel;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Tablajatekok");
        setLocation(40, 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        JButton b1 = new JButton("Chess");
        add(b1, BorderLayout.WEST);
        b1.addActionListener(chessActionListener);
        JButton b2 = new JButton("Checkers");
        add(b2, BorderLayout.EAST);
        b2.addActionListener(checkersActionListener);

        pack();
    }

    private ActionListener chessActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            dispose();

            ChessModel myModel = new ChessModel();
            ChessView myView = new ChessView();

            myModel.setDefaultFigures();
            myModel.addObserver(myView);

            ChessController myController = new ChessController();
            myController.addModel(myModel);
            myController.addView(myView);
            myController.initModel(9);

            myView.addController(myController);

        }
    };

    private ActionListener checkersActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            dispose();

            CheckersModel myModel = new CheckersModel();
            CheckersView myView = new CheckersView();

            myModel.setDefaultFigures();
            myModel.addObserver(myView);

            CheckersController myController = new CheckersController();
            myController.addModel(myModel);
            myController.addView(myView);
            myController.initModel(9);

            myView.addController(myController);

        }
    };
}
