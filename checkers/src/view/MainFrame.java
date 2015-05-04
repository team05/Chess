package view;

import controller.CheckersController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import model.CheckersModel;


public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Checker");
        setLocation(40, 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        
        JButton checkersButton = new JButton("Checker");
        add(checkersButton, BorderLayout.EAST);
        checkersButton.addActionListener(checkersActionListener);

        pack();
    }

    
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
            
            myView.addController(myController);

        }
    };
}
