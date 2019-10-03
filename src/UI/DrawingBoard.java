package UI;

import domain.CheckersBoardSituation;
import piece.Blue;
import piece.CheckerPiece;
import piece.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class DrawingBoard extends JPanel implements MouseListener, KeyListener {

    private CheckersBoardSituation checkersBoardSituation = new CheckersBoardSituation(8, 8);
    private File gameOverRedWins = new File("src/Images/gameOverRedWins.png");
    private File gameoverBlueWins = new File("src/Images/BW.png");

    private File redChecker = new File("src/Images/redCheckerImage.png");
    private File blueChecker = new File("src/Images/BlueCheckerImage.png");
    private File redKing = new File("src/Images/redKing.png");
    private File blueKing = new File("src/Images/blueKing.png");


    private BufferedImage master;
    private BufferedImage gameOverRedWin;
    private BufferedImage gameOverBlueWin;

    private BufferedImage redCheckerImage;
    private BufferedImage blueCheckerImage;
    private BufferedImage redKingImage;
    private BufferedImage blueKingImage;

    {
        try {
//            master = ImageIO.read(file);
            gameOverRedWin = ImageIO.read(gameOverRedWins);
            gameOverBlueWin = ImageIO.read(gameoverBlueWins);

            redCheckerImage = ImageIO.read(redChecker);
            blueCheckerImage = ImageIO.read(blueChecker);
            redKingImage = ImageIO.read(redKing);
            blueKingImage = ImageIO.read(blueKing);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        super.setBackground(new Color(220, 220, 220, 250));


        List<Piece> checkerPieces = checkersBoardSituation.getPiecesLocations();

        // nested loops handle painting the draught pieces
        for (int i = 0; i < 716; i += 100) {
            for (int j = 0; j < 735; j += 100) {
                if ((i + j) % 200 == 0) {
                    g.setColor(new Color(0, 0, 0, 250));
                    g.fill3DRect(i, j, 100, 100, true);
                }
                for (Piece checkerPiece : checkerPieces) {
                    if (checkerPiece == null) {
                        continue;
                    }
                    int x = checkerPiece.getLocation().getX();
                    int y = checkerPiece.getLocation().getY();

                    if (checkerPiece instanceof Blue) {
                        g.setColor(new Color(31, 40, 255));
//                        g.fillOval(x * 100 + 25, y * 100 + 25, 50, 50);
                        g.drawImage(blueCheckerImage, x * 100, y * 100, 100, 100, null);
                        if (((Blue) checkerPiece).isKing()) {
                            g.drawImage(blueKingImage, x * 100, y * 100, 100, 100, null);
                        }
                    } else {
                        checkerPiece = (CheckerPiece) checkerPiece;
                        if (((CheckerPiece) checkerPiece).isKing()) {
                            g.drawImage(redKingImage, x * 100, y * 100, 100, 100, null);
                        } else {
                            g.drawImage(redCheckerImage, x * 100, y * 100, 100, 100, null);
                        }

                    }

                }
            }
        }
        if (checkersBoardSituation.checkIfGameOver() == 1) {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(this);
            frame.setSize(300, 300);


            JPanel panel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(gameOverBlueWin, 0, 0, 300, 300, null);

                }
            };
//            frame.add(panel);
            frame.getContentPane().add(panel);
            frame.setAlwaysOnTop(true);


            frame.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.dispose();
                    frame.setVisible(false);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            frame.add(new JLabel("Game Over"));
            frame.setVisible(true);

        } else if (checkersBoardSituation.checkIfGameOver() == 2) {
            g.drawImage(gameOverRedWin, 300, 300, 200, 200, null);
        }


        System.out.println();

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (checkersBoardSituation.checkIfGameOver() != 0) {
            return;
        }
        int mouseX = e.getX() / 100;
        int mouseY = e.getY() / 100;

        // clicking where there is a piece, selects it
        if (checkersBoardSituation.isOccupied(mouseX, mouseY)) {

            checkersBoardSituation.selectPiece(mouseX, mouseY);

            // clicking on empty space on the board, to move a piece if one is selected
        } else if (checkersBoardSituation.getSelectedCheckerPiece() != null) {
            checkersBoardSituation.movePiece((CheckerPiece) checkersBoardSituation.getSelectedCheckerPiece(), mouseX, mouseY);
            revalidate();
            repaint();

        }

        if (e.getButton() == MouseEvent.BUTTON2) {
            checkersBoardSituation.cheat();
            repaint();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            checkersBoardSituation.restart();
            System.out.println("restarting");
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}