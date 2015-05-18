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

/**
 * 
 * @author team05
 * \brief
 * A jatek megjelniteset vegzo osztaly.
 */
public class MainFrame extends JFrame {
    /**
     * \brief
     * Konstruktor.
     * Egy gombot jelenit meg, melynek hatasara kirajzolodik a palya.
     */
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

    /**
     * \brief
     * A jatek inditasat figyelo esemenykezelo.
     * Betolti az MVC felepiteshez szukseges elemeket.
     */
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
