
import javax.swing.*;
import java.awt.*;

public class BackgroundUtils {

    // Method to create a JPanel with an image background
    public static JPanel createBackgroundPanel(String imagePath) {
        // Load the background image
        ImageIcon backgroundIcon = new ImageIcon(imagePath); // Use the provided imagePath
        JLabel backgroundLabel = new JLabel(backgroundIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set layout manager for the background label and add it to a JPanel
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                backgroundLabel.setSize(this.getSize());
            }
        };
        backgroundPanel.add(backgroundLabel);
        return backgroundPanel;
    }
}
