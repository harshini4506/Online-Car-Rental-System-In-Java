import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends JFrame implements ActionListener {

    private JLabel welcomeLabel, emailLabel, passwordLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private Image backgroundImage;

    public LoginPage() {
        // Set frame properties
        setTitle("Login Page - Online Car Rental System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame to full screen
        setResizable(true); // Allow the window to be resizable

        // Load the background image
        try {
            backgroundImage = Toolkit.getDefaultToolkit().createImage("C:/Users/Dell/OneDrive/Desktop/java programmes AOODP/carimage.jpg");
        } catch (Exception e) {
            System.err.println("Background image not found: " + e.getMessage());
        }

        // Create a custom panel for the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components
        gbc.anchor = GridBagConstraints.CENTER; // Center the components

        add(backgroundPanel);

        // Welcome Label
        welcomeLabel = new JLabel("Welcome to Online Car Rental System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        welcomeLabel.setForeground(Color.BLUE); // Set welcome text color
        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        gbc.gridwidth = 2; // Span two columns
        backgroundPanel.add(welcomeLabel, gbc);

        // Create a transparent panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for the input panel
        inputPanel.setOpaque(false); // Make the input panel transparent

        gbc.gridwidth = 1; // Reset grid width
        gbc.gridx = 0; // Reset column
        gbc.gridy = 1; // Move to the next row (adjusted for upward positioning)

        // Email Label
        emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.black); // Set label color to white
        emailLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set font size
        inputPanel.add(emailLabel, gbc);

        // Increase text field size
        emailField = new JTextField(30); // Set to 30 columns for larger size
        gbc.gridx = 1; // Move to the next column
        inputPanel.add(emailField, gbc);

        // Focus Listener for Email Field
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                emailLabel.setForeground(Color.BLACK); // Change label color when focused
            }

            @Override
            public void focusLost(FocusEvent e) {
                emailLabel.setForeground(Color.black); // Change label color back when not focused
            }
        });

        // Password Label
        gbc.gridx = 0; // Reset column
        gbc.gridy = 2; // Move to the next row (adjusted for upward positioning)
        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLACK); // Set label color to black
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set font size
        inputPanel.add(passwordLabel, gbc);

        // Increase text field size
        passwordField = new JPasswordField(30); // Set to 30 columns for larger size
        gbc.gridx = 1; // Move to the next column
        inputPanel.add(passwordField, gbc);

        // Focus Listener for Password Field
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordLabel.setForeground(Color.black); // Change label color when focused
            }

            @Override
            public void focusLost(FocusEvent e) {
                passwordLabel.setForeground(Color.black); // Change label color back when not focused
            }
        });

        // Add input panel to the background panel
        gbc.gridx = 0; // Reset column
        gbc.gridy = 3; // Move to the next row (adjusted for upward positioning)
        gbc.gridwidth = 2; // Span two columns
        backgroundPanel.add(inputPanel, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.setLayout(new FlowLayout()); // Align buttons in a row

        loginButton = new JButton("Login");
        registerButton = new JButton("Not a member? Register here");

        // Set button colors and styles
        loginButton.setBackground(new Color(0, 120, 215)); // Custom background color
        loginButton.setForeground(Color.WHITE); // Button text color
        loginButton.setPreferredSize(new Dimension(200, 50)); // Increase size of login button
        loginButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increase font size of login button text

        registerButton.setBackground(new Color(255, 0, 0)); // Custom background color
        registerButton.setForeground(Color.WHITE); // Button text color
        registerButton.setPreferredSize(new Dimension(250, 50)); // Increase size of register button
        registerButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increase font size of register button text

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        gbc.gridwidth = 2; // Reset grid width
        gbc.gridy = 4; // Move to the next row for buttons (adjusted for upward positioning)
        backgroundPanel.add(buttonPanel, gbc);

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String email = emailField.getText();
            String password = String.valueOf(passwordField.getPassword());

            // Check credentials from the database
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rental_system", "root", "Latha@17"); // Adjust connection details
                String query = "SELECT * FROM users WHERE email = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new MainPage();  // After login, open the main page
                    this.dispose();   // Close the login page
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error checking credentials: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == registerButton) {
            new RegistrationPage();  // Open registration page
            this.dispose();  // Close login page
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
