import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServicePage extends JFrame implements ActionListener {

    private JButton emergencyButton, requestServiceButton;
    private JTabbedPane tabbedPane; // Tabbed pane for window tabs

    public ServicePage() {
        setTitle("Service Page");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen mode
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Create a tabbed pane
        tabbedPane = new JTabbedPane();

        // Create service panel
        JPanel servicePanel = new JPanel();
        servicePanel.setLayout(new BorderLayout()); // Set layout to BorderLayout

        // Create a background panel with blended red and green
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw blended yellow background (red 50% and green 50%)
                g.setColor(new Color(128, 128, 0)); // Blended yellow color
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Create buttons
        emergencyButton = new JButton("Emergency Help");
        requestServiceButton = new JButton("Request Service");

        // Set preferred sizes for the buttons to center them
        Dimension buttonSize = new Dimension(150, 40); // Set the width and height
        emergencyButton.setPreferredSize(buttonSize);
        requestServiceButton.setPreferredSize(buttonSize);

        // Set font sizes for buttons
        emergencyButton.setFont(new Font("Arial", Font.BOLD, 18)); // Font size 18 for better visibility
        requestServiceButton.setFont(new Font("Arial", Font.BOLD, 18));

        // Create a panel to center the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between buttons
        gbc.anchor = GridBagConstraints.CENTER; // Center the buttons

        // Add buttons to the button panel
        buttonPanel.add(emergencyButton, gbc);
        buttonPanel.add(requestServiceButton, gbc);

        // Add the button panel to the background panel
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER); // Center the button panel

        // Add the background panel to the service panel
        servicePanel.add(backgroundPanel, BorderLayout.CENTER);

        // Add the service panel to the tabbed pane
        tabbedPane.addTab("Services", servicePanel);

        // Add action listeners for buttons
        emergencyButton.addActionListener(this);
        requestServiceButton.addActionListener(this);

        // Add the tabbed pane to the frame
        add(tabbedPane);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == emergencyButton) {
            // Increase the font size in the pop-up notification
            JOptionPane.showMessageDialog(this, "Emergency service dispatched!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == requestServiceButton) {
            // Increase the font size in the pop-up notification
            JOptionPane.showMessageDialog(this, "Service requested, a technician will arrive shortly.", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ServicePage();
    }
}
