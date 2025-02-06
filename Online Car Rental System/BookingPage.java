import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingPage extends JFrame implements ActionListener {

    private JComboBox<String> carDropdown;
    private JButton bookButton;

    public BookingPage() {
        setTitle("Car Booking");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the window to full screen

        // Create a custom panel to have a gradient background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.PINK, 0, getHeight(), Color.CYAN);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components

        JLabel selectCarLabel = new JLabel("Select Car:");
        selectCarLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increase label font size
        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        panel.add(selectCarLabel, gbc);

        carDropdown = new JComboBox<>(new String[]{"Maruti Suzuki Alto", "Hyundai i20", "Tata Nexon", "Honda City"});
        carDropdown.setFont(new Font("Arial", Font.PLAIN, 20)); // Increase dropdown font size
        gbc.gridx = 1; // Move to the next column
        panel.add(carDropdown, gbc);

        bookButton = new JButton("Book Now");
        bookButton.setFont(new Font("Arial", Font.BOLD, 24)); // Increase button font size
        bookButton.setPreferredSize(new Dimension(200, 50)); // Increase button size
        gbc.gridx = 0; // Reset column to 0
        gbc.gridy = 1; // Move to the next row
        gbc.gridwidth = 2; // Span two columns
        panel.add(bookButton, gbc);

        // Add the action listener for the book button
        bookButton.addActionListener(this);

        // Add the panel to the frame
        add(panel);

        setVisible(true); // Make the frame visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            String selectedCar = (String) carDropdown.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Car booked: " + selectedCar);

            // Navigate to the UserDetailsPage after booking a car
            new UserDetailsPage();  // Open UserDetailsPage
            this.dispose();  // Close the BookingPage
        }
    }

    public static void main(String[] args) {
        new BookingPage();
    }
}
