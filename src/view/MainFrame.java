package view;

import controller.ChessController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import model.ChessModel;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Chess");
        setLocation(40, 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        JButton b1 = new JButton("Chess");
        add(b1, BorderLayout.WEST);
        b1.addActionListener(chessActionListener);

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

            myView.addController(myController);

        }
    };

}
