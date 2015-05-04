package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckersView implements java.util.Observer {

    private JPanel gamePanel;
    private Button buttons[][];
    private Frame frame;
    private JLabel nextPlayer = new JLabel("     itt jelezni melyik játékos jön");
    private JButton newGameButton = new JButton("New Game");
    private JButton backToMenu = new JButton("Back to menu");

    CheckersView() {

        buttons = new Button[8][8];

        frame = new Frame("Checkers");

        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(1, 3));
        menu.add(newGameButton);
        menu.add(backToMenu);
        menu.add(nextPlayer);

        gamePanel = new JPanel();
        initButtons();
        gamePanel.setLayout(new GridLayout(8, 8));
        frame.add(menu, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);

        frame.addWindowListener(new CloseListener());
        frame.setSize(500, 500);
        frame.setLocation(100, 100);
        frame.setVisible(true);

    }
    
    //tábla felépítése
    private void initButtons() {
        gamePanel.removeAll();
        gamePanel.setLayout(new GridLayout(8, 8));
        
        int z = 1;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {

                buttons[i][j] = new Button(i, j);

                buttons[i][j].setPreferredSize(new Dimension(50, 50));
                if (z % 2 == 0) {
                    buttons[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    buttons[i][j].setBackground(Color.WHITE);
                }

                z++;
                gamePanel.add(buttons[i][j]);
            }
            z++;
        }
    }

    //eseménykezelők a gombokhoz, táblához
    public void addController(ActionListener controller) {

        newGameButton.addActionListener(controller);
        backToMenu.addActionListener(controller);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j].addActionListener(controller);
            }
        }

    }
    
    @Override
    public void update(Observable o, Object arg) {

    }
    //ablak bezárás figyelése
    public static class CloseListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

}
