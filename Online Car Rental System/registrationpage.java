
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationPage extends JFrame implements ActionListener {

    // Components of the Registration Form
    private JLabel nameLabel, emailLabel, passwordLabel, confirmPasswordLabel, phoneLabel;
    private JTextField nameField, emailField, phoneField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton submitButton, resetButton;

    // Constructor
    public RegistrationPage() {
        setTitle("Register - Online Car Rental System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame to full screen
        setResizable(true); // Allow the window to be resizable

        // Create a custom panel for the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // Create a gradient paint from blue to pink
                GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 0, 255, 102),
                        0, getHeight(), new Color(255, 192, 203));
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
        nameLabel = new JLabel("Full Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Increase font size
        nameField = new JTextField(15); // Adjust number of columns
        nameField.setFont(new Font("Arial", Font.PLAIN, 20)); // Increase font size
        nameField.setPreferredSize(new Dimension(300, 40)); // Set preferred size for text field

        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Increase font size
        emailField = new JTextField(15); // Adjust number of columns
        emailField.setFont(new Font("Arial", Font.PLAIN, 20)); // Increase font size
        emailField.setPreferredSize(new Dimension(300, 40)); // Set preferred size for text field

        phoneLabel = new JLabel("Phone");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Increase font size
        phoneField = new JTextField(15); // Adjust number of columns
        phoneField.setFont(new Font("Arial", Font.PLAIN, 20)); // Increase font size
        phoneField.setPreferredSize(new Dimension(300, 40)); // Set preferred size for text field

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Increase font size
        passwordField = new JPasswordField(15); // Adjust number of columns
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20)); // Increase font size
        passwordField.setPreferredSize(new Dimension(300, 40)); // Set preferred size for text field

        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Increase font size
        confirmPasswordField = new JPasswordField(15); // Adjust number of columns
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 20)); // Increase font size
        confirmPasswordField.setPreferredSize(new Dimension(300, 40)); // Set preferred size for text field

        // Adding components to the background panel
        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        backgroundPanel.add(nameLabel, gbc);

        gbc.gridx = 1; // Move to the next column
        backgroundPanel.add(nameField, gbc);

        gbc.gridx = 0; // Reset column
        gbc.gridy++; // Move to the next row
        backgroundPanel.add(emailLabel, gbc);

        gbc.gridx = 1; // Move to the next column
        backgroundPanel.add(emailField, gbc);

        gbc.gridx = 0; // Reset column
        gbc.gridy++; // Move to the next row
        backgroundPanel.add(phoneLabel, gbc);

        gbc.gridx = 1; // Move to the next column
        backgroundPanel.add(phoneField, gbc);

        gbc.gridx = 0; // Reset column
        gbc.gridy++; // Move to the next row
        backgroundPanel.add(passwordLabel, gbc);

        gbc.gridx = 1; // Move to the next column
        backgroundPanel.add(passwordField, gbc);

        gbc.gridx = 0; // Reset column
        gbc.gridy++; // Move to the next row
        backgroundPanel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1; // Move to the next column
        backgroundPanel.add(confirmPasswordField, gbc);

        // Buttons
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increase button font size
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increase button font size

        gbc.gridx = 0; // Reset column
        gbc.gridy++; // Move to the next row
        gbc.gridwidth = 2; // Span two columns for buttons
        backgroundPanel.add(submitButton, gbc);

        gbc.gridy++; // Move to the next row
        backgroundPanel.add(resetButton, gbc);

        // Register action listeners for the buttons
        submitButton.addActionListener(this);
        resetButton.addActionListener(this);

        add(backgroundPanel); // Add the custom panel to the frame

        setVisible(true); // Make the window visible
    }

    // Action Event Handler
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

            // Basic validation
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Save user to database
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rental_system", "root", "Latha@17"); // Adjust connection details
                    String query = "INSERT INTO users (name, email, phone, password) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, phone);
                    preparedStatement.setString(4, password);
                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        new LoginPage();  // After registration, open the login page
                        this.dispose();   // Close the registration page
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving user: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == resetButton) {
            // Reset form fields
            nameField.setText("");
            emailField.setText("");
            phoneField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
        }
    }

    public static void main(String[] args) {
        new RegistrationPage(); // Create and display the registration page
    }
}
