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

public class CheckersView implements java.util.Observer {

    private JPanel gamePanel;
    private Button buttons[][];
    private Frame frame;
    private JLabel nextPlayer = new JLabel("     Next Player: Red");
    private JButton newGameButton = new JButton("New Game");
    private JButton backToMenu = new JButton("Back to menu");
    private Image rStone, bStone, rQueen, bQueen;
    private Color oldBackGroundColor;

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

    private void initButtons() {
        gamePanel.removeAll();
        gamePanel.setLayout(new GridLayout(8, 8));
        loadIcons();
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
                addIconToButton(i, j, buttons[i][j]);

                gamePanel.add(buttons[i][j]);
            }
            z++;
        }
    }

    public void setQueen(int x, int y, boolean dark) {
        if (dark) {
            buttons[x][y].setIcon(new ImageIcon(bQueen));
        } else {
            buttons[x][y].setIcon(new ImageIcon(rQueen));
        }
    }

    public Button getButtonAt(int x, int y) {
        return buttons[x][y];
    }

    public void goToMenu() {
        new MainFrame().setVisible(true);
        frame.setVisible(false);
        frame.dispose();
    }

    private void addIconToButton(int x, int y, JButton bu) {
        // kek
        if (y == 0 || y == 2) {
            if (x % 2 == 0) {
                bu.setIcon(new ImageIcon(bStone));
            }
        }

        if (y == 1) {
            if (x % 2 == 1) {
                bu.setIcon(new ImageIcon(bStone));
            }
        }

        // piros
        if (y == 5 || y == 7) {
            if (x % 2 == 1) {
                bu.setIcon(new ImageIcon(rStone));
            }
        }

        if (y == 6) {
            if (x % 2 == 0) {
                bu.setIcon(new ImageIcon(rStone));
            }
        }

    }

    public void setLabel(boolean dark) {
        if (dark) {
            nextPlayer.setText("     Next Player: Blue");
        } else {
            nextPlayer.setText("     Next Player: Red");
        }
    }

    private void loadIcons() {
        try {
            rStone = ImageIO.read(getClass().getResource("resources/redStone.jpg"));
            bStone = ImageIO.read(getClass().getResource("resources/blueStone.jpg"));
            rQueen = ImageIO.read(getClass().getResource("resources/redQueen.jpg"));
            bQueen = ImageIO.read(getClass().getResource("resources/blueQueen.jpg"));

        } catch (IOException ex) {
            System.out.println("Nincs meg valamelyik bábu ikonja!");
        }
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

    public void gameOver(boolean dark) {
        if (dark) {
            JOptionPane.showMessageDialog(null,
                    "Game Over! Red player won!",
                    "Game Over",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Game Over! Blue player won!",
                    "Game Over",
                    JOptionPane.PLAIN_MESSAGE);
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

    @Override
    public void update(Observable o, Object arg) {

    }

    public static class CloseListener extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

}
