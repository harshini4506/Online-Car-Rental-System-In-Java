import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentPage extends JFrame implements ActionListener {

    private JTextField kmField;
    private JButton calculateButton, payOnlineButton, payCashButton;
    private JTabbedPane tabbedPane; // Tabbed pane for window tabs

    public PaymentPage() {
        setTitle("Payment");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Create a tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(204, 204, 255)); // Set the tabbed pane background to lavender

        // Payment Panel
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridBagLayout());
        paymentPanel.setBackground(new Color(178, 178, 255)); // Set the payment panel background to a lighter blue-lavender mix
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add space between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label for kilometers traveled
        JLabel kmLabel = new JLabel("Enter kilometers traveled:");
        kmLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increased font size
        kmLabel.setForeground(Color.BLACK); // Set label color to black
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        paymentPanel.add(kmLabel, gbc);

        // Text field to input kilometers
        kmField = new JTextField(20); // Increased the column count for larger size
        kmField.setFont(new Font("Arial", Font.PLAIN, 24)); // Increased text field font size
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        paymentPanel.add(kmField, gbc);

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 24)); // Increased button font size
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        paymentPanel.add(calculateButton, gbc);

        // Pay Online button
        payOnlineButton = new JButton("Pay Online");
        payOnlineButton.setFont(new Font("Arial", Font.BOLD, 24)); // Increased button font size
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        paymentPanel.add(payOnlineButton, gbc);

        // Pay with Cash button
        payCashButton = new JButton("Pay with Cash");
        payCashButton.setFont(new Font("Arial", Font.BOLD, 24)); // Increased button font size
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        paymentPanel.add(payCashButton, gbc);

        // Add the payment panel to the tabbed pane
        tabbedPane.addTab("Payment", paymentPanel);

        // Add action listeners for buttons
        calculateButton.addActionListener(this);
        payOnlineButton.addActionListener(this);
        payCashButton.addActionListener(this);

        // Add the tabbed pane to the frame
        add(tabbedPane);

        // Set the frame to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window
        setVisible(true);
    }

    // Action Event Handler
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            try {
                int km = Integer.parseInt(kmField.getText());
                int amount = km * 10; // Assuming 10 rupees per km
                showNotification("Total amount: " + amount + " INR"); // Show output as notification
            } catch (NumberFormatException ex) {
                showNotification("Please enter a valid number for kilometers."); // Show error as notification
            }
        } else if (e.getSource() == payOnlineButton) {
            showNotification("Proceed to online payment."); // Show message as notification
        } else if (e.getSource() == payCashButton) {
            showNotification("Pay cash on delivery."); // Show message as notification
        }
    }

    // Method to show notification as a dialog with larger text
    private void showNotification(String message) {
        // Create a JLabel with a larger font size for the notification message
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set font size for the notification
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text

        // Create a JPanel to hold the message
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(messageLabel, BorderLayout.CENTER);

        // Create the dialog
        JDialog dialog = new JDialog(this, "Notification", true);
        dialog.setContentPane(panel);
        dialog.setSize(500, 300); // Set the dialog size
        dialog.setLocationRelativeTo(this); // Center the dialog on the parent frame
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new PaymentPage();
    }
}
