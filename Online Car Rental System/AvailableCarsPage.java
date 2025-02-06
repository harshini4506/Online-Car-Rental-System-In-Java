import javax.swing.*;
import java.awt.*;

public class AvailableCarsPage extends JFrame {

    public AvailableCarsPage() {
        setTitle("Available Cars");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full window screen

        // Create a custom panel to apply gradient background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Create a gradient with 60% pink and 40% blue
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 182, 193), 0, getHeight(), new Color(135, 206, 235));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setLayout(new GridLayout(4, 2));

        // Sample car details
        String[] carNames = {"Honda Civic", "Toyota Corolla", "Tesla Model 3", "Ford Mustang"};
        String[] carFeatures = {"Automatic, 25 MPG , petrol", "Manual, 30 MPG , diesel ", "Electric, 300 Miles Range , petrol ", "Manual, 20 MPG , diesel"};

        for (int i = 0; i < carNames.length; i++) {
            JLabel carNameLabel = new JLabel(carNames[i]);
            carNameLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Increase font size for better visibility
            JLabel carFeatureLabel = new JLabel(carFeatures[i]);
            carFeatureLabel.setFont(new Font("Arial", Font.PLAIN, 40)); // Increase font size for features
            panel.add(carNameLabel);
            panel.add(carFeatureLabel);
        }

        add(panel);
        setVisible(true); // Make the frame visible
    }

    public static void main(String[] args) {
        new AvailableCarsPage();
    }
}
