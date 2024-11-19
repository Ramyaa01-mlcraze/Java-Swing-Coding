import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculator extends JFrame {
    
    // Declare components
    private JLabel labelSubject1, labelSubject2, labelSubject3, labelSubject4, labelSubject5;
    private JTextField textFieldSubject1, textFieldSubject2, textFieldSubject3, textFieldSubject4, textFieldSubject5;
    private JButton calculateButton;
    private JTextArea resultArea;
    private JLabel resultLabel;

    // Constructor to set up the GUI
    public GradeCalculator() {
        // Set the title of the window
        setTitle("Grade Calculator");

        // Set layout manager
        setLayout(new FlowLayout());

        // Initialize labels and text fields
        labelSubject1 = new JLabel("Enter marks for English (out of 100):");
        labelSubject2 = new JLabel("Enter marks for Physics (out of 100):");
        labelSubject3 = new JLabel("Enter marks for Chemistry (out of 100):");
        labelSubject4 = new JLabel("Enter marks for Information Technology (out of 100):");
        labelSubject5 = new JLabel("Enter marks for Maths (out of 100):");

        textFieldSubject1 = new JTextField(5);
        textFieldSubject2 = new JTextField(5);
        textFieldSubject3 = new JTextField(5);
        textFieldSubject4 = new JTextField(5);
        textFieldSubject5 = new JTextField(5);

        // Initialize result area and label
        resultLabel = new JLabel("Result: ");
        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        // Initialize Calculate Button
        calculateButton = new JButton("Calculate");

        // Add components to the window
        add(labelSubject1);
        add(textFieldSubject1);
        add(labelSubject2);
        add(textFieldSubject2);
        add(labelSubject3);
        add(textFieldSubject3);
        add(labelSubject4);
        add(textFieldSubject4);
        add(labelSubject5);
        add(textFieldSubject5);
        add(calculateButton);
        add(resultLabel);
        add(resultArea);

        // Set the size of the window
        setSize(350, 400);
        
        // Set the behavior when closing the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the window visible
        setVisible(true);

        // Add action listener to calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from the text fields
                try {
                    int marks1 = Integer.parseInt(textFieldSubject1.getText());
                    int marks2 = Integer.parseInt(textFieldSubject2.getText());
                    int marks3 = Integer.parseInt(textFieldSubject3.getText());
                    int marks4 = Integer.parseInt(textFieldSubject4.getText());
                    int marks5 = Integer.parseInt(textFieldSubject5.getText());

                    // Validate the marks (must be between 0 and 100)
                    if (marks1 < 0 || marks1 > 100 || marks2 < 0 || marks2 > 100 ||
                        marks3 < 0 || marks3 > 100 || marks4 < 0 || marks4 > 100 || marks5 < 0 || marks5 > 100) {
                        JOptionPane.showMessageDialog(null, "Marks should be between 0 and 100. Please enter valid marks.");
                        return;
                    }

                    // Calculate total and percentage
                    int totalMarks = marks1 + marks2 + marks3 + marks4 + marks5;
                    double percentage = totalMarks / 5.0;
                    String grade = calculateGrade(percentage);

                    // Display the result
                    resultArea.setText("Total Marks: " + totalMarks + "\n" +
                                        "Percentage: " + String.format("%.2f", percentage) + "%" + "\n" +
                                        "Grade: " + grade);

                } catch (NumberFormatException ex) {
                    // Handle invalid input (e.g., if the user enters non-numeric characters)
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for all subjects.");
                }
            }
        });
    }

    // Method to calculate the grade based on percentage
    private String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B+";
        } else if (percentage >= 60) {
            return "B";
        } else if (percentage >= 50) {
            return "C";
        } else if (percentage >= 40) {
            return "D";
        } else {
            return "Fail";
        }
    }

    // Main method to start the program
    public static void main(String[] args) {
        // Create an instance of the GradeCalculator and display it
        new GradeCalculator();
    }
}
