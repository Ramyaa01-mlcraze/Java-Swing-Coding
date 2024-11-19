import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Student class to store student information
class Student {
    String name, rollNo, course, email;

    // Constructor to initialize student object
    public Student(String name, String rollNo, String course, String email) {
        this.name = name;
        this.rollNo = rollNo;
        this.course = course;
        this.email = email;
    }

    // Overriding toString method to display student info
    @Override
    public String toString() {
        return "Name: " + name + ", Roll No: " + rollNo + ", Course: " + course + ", Email: " + email;
    }
}

public class StudentManagementSystem {
    // List to store student records
    private List<Student> studentList = new ArrayList<>();

    // GUI Components
    private JFrame frame;
    private JTextField nameField, rollNoField, courseField, emailField;
    private JTextArea displayArea;

    public StudentManagementSystem() {
        // Create the frame
        frame = new JFrame("Student Management System");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create input fields with labels
        frame.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        frame.add(nameField);

        frame.add(new JLabel("Roll No:"));
        rollNoField = new JTextField(20);
        frame.add(rollNoField);

        frame.add(new JLabel("Course:"));
        courseField = new JTextField(20);
        frame.add(courseField);

        frame.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        frame.add(emailField);

        // Button to save the student
        JButton saveButton = new JButton("Save Student");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveStudent();
            }
        });
        frame.add(saveButton);

        // Button to display all students
        JButton displayButton = new JButton("Display Students");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });
        frame.add(displayButton);

        // Area to display students
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        frame.add(new JScrollPane(displayArea));

        // Button to exit
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.add(exitButton);

        frame.setVisible(true);
    }

    // Method to save a student
    private void saveStudent() {
        String name = nameField.getText().trim();
        String rollNo = rollNoField.getText().trim();
        String course = courseField.getText().trim();
        String email = emailField.getText().trim();

        // Validate the fields
        if (name.isEmpty() || rollNo.isEmpty() || course.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(frame, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a new student and add to the list
        Student student = new Student(name, rollNo, course, email);
        studentList.add(student);

        // Clear the input fields after saving
        nameField.setText("");
        rollNoField.setText("");
        courseField.setText("");
        emailField.setText("");

        JOptionPane.showMessageDialog(frame, "Student saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to display all students
    private void displayStudents() {
        if (studentList.isEmpty()) {
            displayArea.setText("No student records found.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Student student : studentList) {
                sb.append(student.toString()).append("\n");
            }
            displayArea.setText(sb.toString());
        }
    }

    // Method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSystem();
            }
        });
    }
}
