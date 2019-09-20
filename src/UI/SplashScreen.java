package UI;

import javax.swing.*;
import java.awt.*;

class SplashScreen extends JFrame {
    private int duration;

    public SplashScreen(int d) {
        duration = d;

        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.white);
        int width = 450;
        int height = 115;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        content.add(new JLabel("asdf"), BorderLayout.CENTER);
        Color oraRed = new Color(156, 20, 20, 255);
        content.setBorder(BorderFactory.createLineBorder(oraRed, 10));

        setVisible(true);
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
        }
        setVisible(false);
    }
}