package UI;

import javax.swing.*;
import java.awt.*;


public class CheckersBoard implements Runnable {

    private final int rows = 8;
    private final int cols = 8;


    private JFrame frame;
    private         DrawingBoard drawingBoard = new DrawingBoard();


    public void Setup() {
        frame = new JFrame("Checkers game");

        frame.setPreferredSize(new Dimension(816, 835));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        frame.setContentPane(drawingBoard);

        createComponents(frame.getContentPane());


//        frame.setLocationRelativeTo(null);
        frame.pack();

        frame.setVisible(true);

    }

    private void createComponents(Container container) {

        DrawingBoard drawingBoard = new DrawingBoard();
        drawingBoard.addMouseListener(drawingBoard);
        drawingBoard.addKeyListener(drawingBoard);
        frame.addKeyListener(drawingBoard);
        frame.addMouseListener(drawingBoard);

         container.add(drawingBoard);

//        container.add(new SplashScreen(0));


    }


    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void run() {
        Setup();
    }
}
