
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame implements ActionListener {

    private JButton availableCarsButton, bookingButton, paymentButton, serviceButton;

    public MainPage() {
        setTitle("Car Rental System - Main Page");

        // Set the frame to be undecorated and full-screen
        setUndecorated(true);  // Remove title bar and borders
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximize the window to full screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window on the screen

        // Create a custom panel for the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // Create a gradient paint from pink to blue
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 192, 203, 204),
                        0, getHeight(), new Color(0, 0, 255, 102));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Set layout and add the background panel
        backgroundPanel.setLayout(new GridBagLayout());  // Use GridBagLayout for precise control over placement
        add(backgroundPanel);

        // Create a panel for the buttons with a grid layout
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 20, 20));  // Adjust grid layout for 4 buttons with space between them
        buttonPanel.setOpaque(false);  // Make button panel transparent

        // Initialize buttons
        availableCarsButton = new JButton("Available Cars");
        bookingButton = new JButton("Booking");
        paymentButton = new JButton("Payment");
        serviceButton = new JButton("Service");

        // Set button styles for a more attractive look
        setButtonStyle(availableCarsButton);
        setButtonStyle(bookingButton);
        setButtonStyle(paymentButton);
        setButtonStyle(serviceButton);

        // Add buttons to panel
        buttonPanel.add(availableCarsButton);
        buttonPanel.add(bookingButton);
        buttonPanel.add(paymentButton);
        buttonPanel.add(serviceButton);

        // Add action listeners
        availableCarsButton.addActionListener(this);
        bookingButton.addActionListener(this);
        paymentButton.addActionListener(this);
        serviceButton.addActionListener(this);

        // Add the button panel to the center of the background
        backgroundPanel.add(buttonPanel, new GridBagConstraints());

        // Make the frame visible
        setVisible(true);
    }

    // Set a consistent style for all buttons
    private void setButtonStyle(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));  // Set a bold font for buttons
        button.setBackground(new Color(0, 102, 204));  // Set button background color
        button.setForeground(Color.WHITE);  // Set button text color
        button.setFocusPainted(false);  // Remove focus border
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  // Add padding inside the button
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == availableCarsButton) {
            // Open the AvailableCarsPage when "Available Cars" button is clicked
            new AvailableCarsPage();
        } else if (e.getSource() == bookingButton) {
            // Open the BookingPage when "Booking" button is clicked
            new BookingPage();
        } else if (e.getSource() == paymentButton) {
            // Open the PaymentPage when "Payment" button is clicked
            new PaymentPage();
        } else if (e.getSource() == serviceButton) {
            // Open the ServicePage when "Service" button is clicked
            new ServicePage();
        }
    }

    public static void main(String[] args) {
        new MainPage();
    }
}
