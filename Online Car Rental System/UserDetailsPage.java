
import javax.swing.*;
import java.awt.*;

public class UserDetailsPage extends JFrame {

    public UserDetailsPage() {
        setTitle("User Details");
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Set the window to full-screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Custom background panel with gradient colors
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // Create a gradient paint from lavender to purple
                GradientPaint gradient = new GradientPaint(0, 0, new Color(230, 230, 250), // Lavender
                        0, getHeight(), new Color(128, 0, 128)); // Purple
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Set layout
        backgroundPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components
        gbc.anchor = GridBagConstraints.CENTER; // Center the components

        // Form Components
        Font labelFont = new Font("Arial", Font.BOLD, 30); // Font for labels
        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setFont(labelFont);
        JTextField nameField = new JTextField(15);
        nameField.setFont(new Font("Arial", Font.PLAIN, 20));
        nameField.setPreferredSize(new Dimension(300, 40));

        JLabel licenseLabel = new JLabel("License No");
        licenseLabel.setFont(labelFont);
        JTextField licenseField = new JTextField(15);
        licenseField.setFont(new Font("Arial", Font.PLAIN, 20));
        licenseField.setPreferredSize(new Dimension(300, 40));

        JLabel aadhaarLabel = new JLabel("Aadhaar No");
        aadhaarLabel.setFont(labelFont);
        JTextField aadhaarField = new JTextField(15);
        aadhaarField.setFont(new Font("Arial", Font.PLAIN, 20));
        aadhaarField.setPreferredSize(new Dimension(300, 40));

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(labelFont);
        JTextField addressField = new JTextField(15);
        addressField.setFont(new Font("Arial", Font.PLAIN, 20));
        addressField.setPreferredSize(new Dimension(300, 40));

        // Adding components to the background panel
        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        backgroundPanel.add(nameLabel, gbc);

        gbc.gridx = 1; // Move to the next column
        backgroundPanel.add(nameField, gbc);

        gbc.gridx = 0; // Reset column
        gbc.gridy++; // Move to the next row
        backgroundPanel.add(licenseLabel, gbc);

        gbc.gridx = 1; // Move to the next column
        backgroundPanel.add(licenseField, gbc);

        gbc.gridx = 0; // Reset column
        gbc.gridy++; // Move to the next row
        backgroundPanel.add(aadhaarLabel, gbc);

        gbc.gridx = 1; // Move to the next column
        backgroundPanel.add(aadhaarField, gbc);

        gbc.gridx = 0; // Reset column
        gbc.gridy++; // Move to the next row
        backgroundPanel.add(addressLabel, gbc);

        gbc.gridx = 1; // Move to the next column
        backgroundPanel.add(addressField, gbc);

        // Submit button to process the user input
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increase font size for button
        gbc.gridx = 0; // Reset column
        gbc.gridy++; // Move to the next row
        gbc.gridwidth = 2; // Span two columns for the button
        backgroundPanel.add(submitButton, gbc);

        // Action listener for the submit button
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String license = licenseField.getText();
            String aadhaar = aadhaarField.getText();
            String address = addressField.getText();  // Get address input

            // Displaying the entered details in a dialog box
            JOptionPane.showMessageDialog(this, "Name: " + name +
                    "\nLicense No: " + license +
                    "\nAadhaar No: " + aadhaar +
                    "\nAddress: " + address);  // Include address in the dialog
        });

        add(backgroundPanel); // Add the custom panel to the frame
        setVisible(true); // Make the window visible
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserDetailsPage::new);
    }
}
