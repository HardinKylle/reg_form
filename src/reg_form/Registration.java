package reg_form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

/**
 * The Registration class creates a registration form GUI using Swing.
 * Users can input their personal information such as name, course,
 * year level, gender, address, email, and contact number.
 * The form includes options to register, clear the fields, or exit the application.
 */

public class Registration extends JFrame {

    public Registration() {
        setTitle("Registration Form");
        setSize(350, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        JLabel formTitleLabel = new JLabel("Registration Form", JLabel.CENTER);
        formTitleLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
        
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(30);

        JLabel courseLabel = new JLabel("Course:");
        String[] courses = {"", "BSCS-SE", "BSIT-AGD", "BSCE", "BSME", "BSCpE"};
        JComboBox<String> courseBox = new JComboBox<>(courses);

        JLabel yearLabel = new JLabel("Year Level:");
        String[] yearLevels = {"", "1st Year", "2nd Year", "3rd Year", "4th Year"};
        JComboBox<String> yearBox = new JComboBox<>(yearLevels);

        JLabel genderLabel = new JLabel("Gender:");
        JRadioButton maleButton = new JRadioButton("Male");
        JRadioButton femaleButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(50);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(30);

        JLabel contactLabel = new JLabel("Contact No:");
        JTextField contactField = new JTextField(11);

        JButton registerButton = new JButton("Register");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        registerButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String course = (String) courseBox.getSelectedItem();
            String year = (String) yearBox.getSelectedItem();
            String gender = maleButton.isSelected() ? "Male" : femaleButton.isSelected() ? "Female" : "";
            String address = addressField.getText().trim();
            String email = emailField.getText().trim();
            String contact = contactField.getText().trim();

            // Validate input fields
            if (name.isEmpty() || course.isEmpty() || year.isEmpty() || gender.isEmpty() ||
                address.isEmpty() || email.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(this, "Invalid email format!", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidContactNumber(contact)) {
                JOptionPane.showMessageDialog(this, "Contact number must be 11 digits!", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            nameField.setText("");
            addressField.setText("");
            emailField.setText("");
            contactField.setText("");
            courseBox.setSelectedIndex(0);
            yearBox.setSelectedIndex(0);
            genderGroup.clearSelection();
        });

        exitButton.addActionListener(e -> System.exit(0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; 
        add(formTitleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(courseLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(courseBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(yearLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        add(yearBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(genderLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(maleButton, gbc); 

        gbc.gridx = 2;
        add(femaleButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        add(contactLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(contactField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(registerButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3; 
        add(buttonPanel, gbc);

        setVisible(true);
    }

    /**
     * Validates the email format using a regex pattern.
     * @param email The email string to validate.
     * @return true if the email format is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    /**
     * Validates the contact number to ensure it contains exactly 11 digits.
     * @param contact The contact number string to validate.
     * @return true if the contact number is valid, false otherwise.
     */
    private boolean isValidContactNumber(String contact) {
        return contact.matches("\\d{11}");
    }

    /**
     * The main method to run the Registration application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Registration();
    }
}
