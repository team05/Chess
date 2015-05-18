package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessView implements java.util.Observer {

    private JPanel gamePanel;
    private Button buttons[][];
    private Frame frame;
    private JLabel nextPlayer = new JLabel("     Next Player: Light");
    JButton newGameButton = new JButton("New Game");
    JButton backToMenu = new JButton("Back to menu");
    private Image lP, dP, lKn, dKn, lB, dB, lR, dR, lQ, dQ, lKi, dKi;
    private Color oldBackGroundColor;

    public ChessView() {

        buttons = new Button[8][8];

        frame = new Frame("Chess");

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

    private void initButtons() {
        gamePanel.removeAll();
        gamePanel.setLayout(new GridLayout(8, 8));
        loadIcons();
        int z = 1;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                //   JButton jButton = new Button(i, j);
                buttons[i][j] = new Button(i, j);
                //jButton.setPreferredSize(new Dimension(50, 50));
                buttons[i][j].setPreferredSize(new Dimension(50, 50));
                if (z % 2 == 0) {
                    buttons[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    buttons[i][j].setBackground(Color.WHITE);
                }

                z++;
                addIconToButton(i, j, buttons[i][j]);

                gamePanel.add(buttons[i][j]);
            }
            z++;
        }
    }

    public Button getButtonAt(int x, int y) {
        return buttons[x][y];
    }

    private void addIconToButton(int x, int y, JButton bu) {

        // Pawns   
        if (y == 1) {
            bu.setIcon(new ImageIcon(dP));
        }
        if (y == 6) {
            bu.setIcon(new ImageIcon(lP));
        }

             //Figures
        //Dark Figures
        if (y == 0) {
            if (x == 0 || x == 7) {
                bu.setIcon(new ImageIcon(dR));
            }
            if (x == 1 || x == 6) {
                bu.setIcon(new ImageIcon(dKn));
            }
            if (x == 2 || x == 5) {
                bu.setIcon(new ImageIcon(dB));
            }
            if (x == 3) {
                bu.setIcon(new ImageIcon(dQ));
            }
            if (x == 4) {
                bu.setIcon(new ImageIcon(dKi));
            }
        }

        //Light Figures
        if (y == 7) {
            if (x == 0 || x == 7) {
                bu.setIcon(new ImageIcon(lR));
            }
            if (x == 1 || x == 6) {
                bu.setIcon(new ImageIcon(lKn));
            }
            if (x == 2 || x == 5) {
                bu.setIcon(new ImageIcon(lB));
            }
            if (x == 3) {
                bu.setIcon(new ImageIcon(lQ));
            }
            if (x == 4) {
                bu.setIcon(new ImageIcon(lKi));
            }
        }

        /*   if (table.getFigure(i, j) instanceof Rook) {
         if (table.getFigure(i, j).isDark()) {
         jButton.setIcon(new ImageIcon(dR));
         } else {
         jButton.setIcon(new ImageIcon(lR));
         }
         jButton.setMargin(new Insets(0, 0, 0, 0));
         }

         if (table.getFigure(i, j) instanceof Pawn) {
         if (table.getFigure(i, j).isDark()) {
         jButton.setIcon(new ImageIcon(dP));
         } else {
         jButton.setIcon(new ImageIcon(lP));
         }
         jButton.setMargin(new Insets(0, 0, 0, 0));
         }

         if (table.getFigure(i, j) instanceof Knight) {
         if (table.getFigure(i, j).isDark()) {
         jButton.setIcon(new ImageIcon(dKn));
         } else {
         jButton.setIcon(new ImageIcon(lKn));
         }
         jButton.setMargin(new Insets(0, 0, 0, 0));
         }

         if (table.getFigure(i, j) instanceof Bishop) {
         if (table.getFigure(i, j).isDark()) {
         jButton.setIcon(new ImageIcon(dB));
         } else {
         jButton.setIcon(new ImageIcon(lB));
         }
         jButton.setMargin(new Insets(0, 0, 0, 0));
         }

         if (table.getFigure(i, j) instanceof Queen) {
         if (table.getFigure(i, j).isDark()) {
         jButton.setIcon(new ImageIcon(dQ));
         } else {
         jButton.setIcon(new ImageIcon(lQ));
         }
         jButton.setMargin(new Insets(0, 0, 0, 0));
         }

         if (table.getFigure(i, j) instanceof King) {
         if (table.getFigure(i, j).isDark()) {
         jButton.setIcon(new ImageIcon(dKi));
         } else {
         jButton.setIcon(new ImageIcon(lKi));
         }
         jButton.setMargin(new Insets(0, 0, 0, 0));
         } */
    }

    private void loadIcons() {
        try {
            lP = ImageIO.read(getClass().getResource("resources/lightPawn.jpg"));
            dP = ImageIO.read(getClass().getResource("resources/darkPawn.jpg"));
            lKn = ImageIO.read(getClass().getResource("resources/lightKnight.jpg"));
            dKn = ImageIO.read(getClass().getResource("resources/darkKnight.jpg"));
            lB = ImageIO.read(getClass().getResource("resources/lightBishop.jpg"));
            dB = ImageIO.read(getClass().getResource("resources/darkBishop.jpg"));
            lR = ImageIO.read(getClass().getResource("resources/lightRook.jpg"));
            dR = ImageIO.read(getClass().getResource("resources/darkRook.jpg"));
            lQ = ImageIO.read(getClass().getResource("resources/lightQueen.jpg"));
            dQ = ImageIO.read(getClass().getResource("resources/darkQueen.jpg"));
            lKi = ImageIO.read(getClass().getResource("resources/lightKing.jpg"));
            dKi = ImageIO.read(getClass().getResource("resources/darkKing.jpg"));
        } catch (IOException ex) {
            System.out.println("Nincs meg valamelyik bábu ikonja!");
        }
    }

    public void update(Observable obs, Object obj) {

    }

    public void goToMenu() {
        new MainFrame().setVisible(true);
        frame.setVisible(false);
        frame.dispose();
    }

    public void addController(ActionListener controller) {
        newGameButton.addActionListener(controller);
        backToMenu.addActionListener(controller);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j].addActionListener(controller);
            }
        }

    }

    public void setLabel(boolean dark) {
        if (dark) {
            nextPlayer.setText("     Next Player: Dark");
        } else {
            nextPlayer.setText("     Next Player: Light");
        }
    }

    public String isInDialog(int x, int y, boolean dark) {
        Object[] options = {"Queen",
            "Rook",
            "Knight",
            "Bishop"};
        int n = JOptionPane.showOptionDialog(null,
                "Which figure do you want?",
                "Choose figure",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
        switch (n) {
            case 0: {

                if (dark) {
                    buttons[x][y].setIcon(new ImageIcon(dQ));
                } else {
                    buttons[x][y].setIcon(new ImageIcon(lQ));
                }
                return "Queen";
            }
            case 1: {

                if (dark) {
                    buttons[x][y].setIcon(new ImageIcon(dR));
                } else {
                    buttons[x][y].setIcon(new ImageIcon(lR));
                }
                return "Rook";
            }
            case 2: {

                if (dark) {
                    buttons[x][y].setIcon(new ImageIcon(dKn));
                } else {
                    buttons[x][y].setIcon(new ImageIcon(lKn));
                }
                return "Knight";
            }
            case 3: {

                if (dark) {
                    buttons[x][y].setIcon(new ImageIcon(dB));
                } else {
                    buttons[x][y].setIcon(new ImageIcon(lB));
                }
                return "Bishop";
            }
            default:
                return "hiba";
        }
    }

    public void setRed(Button bu) {
        oldBackGroundColor = bu.getBackground();
        bu.setBackground(Color.red);
    }

    public void setBlue(Button bu) {

        bu.setBackground(Color.blue);
    }

    public Icon getIcon(int x, int y) {
        return buttons[x][y].getIcon();
    }

    public void setIcon(int x, int y, Icon icon) {
        buttons[x][y].setIcon(icon);
    }

    public void setUnselected(Button bu) {
        bu.setBackground(oldBackGroundColor);
    }

    public void resetBackGrounds() {
        int z = 1;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {

                if (z % 2 == 0) {
                    buttons[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    buttons[i][j].setBackground(Color.WHITE);
                }

                z++;

            }
            z++;
        }
    }

    public static class CloseListener extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

}
