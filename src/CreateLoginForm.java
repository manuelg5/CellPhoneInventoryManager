
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class CreateLoginForm extends JFrame implements ActionListener {
    private JTextField textField1, textField2;
    private JButton jButtonSubmit, jButtonCreateAccount;
    private JLabel userLabel, passLabel;

    public CreateLoginForm() {
        setTitle("Login Window");
        setLayout(new GridLayout(4, 2));

        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");

        textField1 = new JTextField(15);
        textField2 = new JPasswordField(15);

        jButtonSubmit = new JButton("Login");
        jButtonCreateAccount = new JButton("Create Account");

        add(userLabel);
        add(textField1);
        add(passLabel);
        add(textField2);
        add(jButtonSubmit);
        add(jButtonCreateAccount);

        jButtonSubmit.addActionListener(this);

        jButtonCreateAccount.addActionListener(e -> {
            CreateAccount createAccountForm = new CreateAccount();
            createAccountForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            createAccountForm.setSize(400, 200);
            createAccountForm.setVisible(true);
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = textField1.getText();
        String password = textField2.getText();

        boolean isAuthenticated = false;

        try (Scanner scanner = new Scanner(new File("users.txt"))) {
            while (scanner.hasNextLine()) {
                String[] credentials = scanner.nextLine().split(" ");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    isAuthenticated = true;
                    break;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading user file.");
        }

        if (isAuthenticated) {
            JOptionPane.showMessageDialog(null, "Login successful!");
            new CellPhoneInventory();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials. Try again.");
        }
    }

    public static void main(String[] args) {
        new CreateLoginForm();
    }
}

class CreateAccount extends JFrame {
    private JTextField usrTextField, pwTextField;
    private JButton createButton;

    public CreateAccount() {
        setTitle("Create Account");
        setLayout(new GridLayout(3, 2));

        usrTextField = new JTextField(15);
        pwTextField = new JPasswordField(15);
        createButton = new JButton("Create");

        add(new JLabel("Username:"));
        add(usrTextField);
        add(new JLabel("Password:"));
        add(pwTextField);
        add(createButton);

        createButton.addActionListener(e -> {
            try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt", true))) {
                writer.println(usrTextField.getText() + " " + pwTextField.getText());
                JOptionPane.showMessageDialog(null, "Account created successfully!");
                dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error creating account.");
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setVisible(true);
    }
}
