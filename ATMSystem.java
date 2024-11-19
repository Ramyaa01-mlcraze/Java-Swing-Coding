import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ATM class to simulate the ATM system
public class ATMSystem {

    // GUI Components
    private JFrame frame;
    private JTextField usernameField, pinField, amountField;
    private JTextArea displayArea;
    private JButton loginButton, checkBalanceButton, depositButton, withdrawButton, logoutButton;
    private JPanel loginPanel, menuPanel;

    // ATM Data: Stored for simplicity (in-memory only)
    private String storedUsername = "user";
    private String storedPin = "1234";
    private double balance = 1000.0;  // Initial balance

    // Constructor to set up the GUI
    public ATMSystem() {
        // Set up the main frame
        frame = new JFrame("ATM System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use CardLayout for the main content panel
        CardLayout cardLayout = new CardLayout();
        JPanel contentPane = new JPanel(cardLayout); // Apply CardLayout here
        frame.setContentPane(contentPane);

        // Create login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout());
        
        // Username field and label
        loginPanel.add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        loginPanel.add(usernameField);

        // PIN field and label (password style)
        loginPanel.add(new JLabel("PIN:"));
        pinField = new JPasswordField(20);
        loginPanel.add(pinField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        loginPanel.add(loginButton);

        // Create menu panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());

        // Display area for balance and transactions
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        menuPanel.add(new JScrollPane(displayArea));

        // Button to check balance
        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
        menuPanel.add(checkBalanceButton);

        // Button to deposit money
        depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
        menuPanel.add(depositButton);

        // Button to withdraw money
        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
        menuPanel.add(withdrawButton);

        // Button to logout
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        menuPanel.add(logoutButton);

        // Add panels to the content pane
        contentPane.add(loginPanel, "Login");
        contentPane.add(menuPanel, "Menu");

        // Show login screen initially
        frame.setVisible(true);
    }

    // Method to handle login
    private void login() {
        String username = usernameField.getText();
        String pin = pinField.getText();

        // Validate username and PIN
        if (username.equals(storedUsername) && pin.equals(storedPin)) {
            // Switch to the menu screen
            CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
            cl.show(frame.getContentPane(), "Menu");

            // Clear the login fields
            usernameField.setText("");
            pinField.setText("");

            // Display the balance
            displayArea.setText("Login successful!\nBalance: " + balance);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or PIN.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to check balance
    private void checkBalance() {
        displayArea.append("\nCurrent balance: " + balance);
    }

    // Method to handle deposit
    private void deposit() {
        // Prompt for deposit amount
        String amount = JOptionPane.showInputDialog(frame, "Enter deposit amount:");
        
        // Validate amount
        try {
            double depositAmount = Double.parseDouble(amount);
            if (depositAmount <= 0) {
                JOptionPane.showMessageDialog(frame, "Amount must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                balance += depositAmount;
                displayArea.append("\nDeposited: " + depositAmount);
                displayArea.append("\nNew balance: " + balance);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to handle withdrawal
    private void withdraw() {
        // Prompt for withdrawal amount
        String amount = JOptionPane.showInputDialog(frame, "Enter withdrawal amount:");

        // Validate amount
        try {
            double withdrawAmount = Double.parseDouble(amount);
            if (withdrawAmount <= 0) {
                JOptionPane.showMessageDialog(frame, "Amount must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (withdrawAmount > balance) {
                JOptionPane.showMessageDialog(frame, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                balance -= withdrawAmount;
                displayArea.append("\nWithdrew: " + withdrawAmount);
                displayArea.append("\nNew balance: " + balance);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to logout and go back to login screen
    private void logout() {
        // Switch to the login screen
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), "Login");

        // Clear the display area
        displayArea.setText("");

        // Reset fields for next login attempt
        usernameField.setText("");
        pinField.setText("");
    }

    // Main method to run the ATM System
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATMSystem();
            }
        });
    }
}
