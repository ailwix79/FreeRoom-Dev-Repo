import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login_temp extends JFrame {

    String userID;

    public Login_temp() {

        JPanel panel;
        JLabel user_label, password_label, role_label;
        JTextField userName_text;
        JPasswordField password_text;
        JButton submit;

        // User Label
        user_label = new JLabel();
        user_label.setText("User ID: ");
        userName_text = new JTextField();

        // Password
        password_label = new JLabel();
        password_label.setText("Password: ");
        password_text = new JPasswordField();

        //Teacher / Admin
        role_label = new JLabel();
        role_label.setText("Role: ");
        Choice ch1 = new Choice();
        ch1.add("Professor");
        ch1.add("Administrative");
        ch1.add("Student");

        // Submit
        submit = new JButton("LOGIN");

        panel = new JPanel(new GridLayout(4, 2));
        panel.add(role_label);
        panel.add(ch1);
        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);
        panel.add(submit);

        // LOGIN BUTTON HANDLER
        submit.addActionListener(e -> {
            boolean isLoginTrue = (boolean) Client.getData("loginData",userName_text.getText(), String.valueOf(password_text.getPassword()), ch1.getSelectedItem(), null,null);

            if(isLoginTrue && ch1.getSelectedIndex() == 0) {
                dispose();
                userID = userName_text.getText();
                new Teacher_temp();
            }
            else if (isLoginTrue && ch1.getSelectedIndex() == 1) {
                dispose();
                userID = userName_text.getText();
                JOptionPane.showMessageDialog(this, "Administrative Functionalities to be added in the next Sprint", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (isLoginTrue && ch1.getSelectedIndex() == 2) {
                dispose();
                userID = userName_text.getText();
                new Student_temp();
            }
            else
                JOptionPane.showMessageDialog(this, "Incorrect Username / Password", "ERROR", JOptionPane.ERROR_MESSAGE);
        });

        add(panel, BorderLayout.CENTER);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?", "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }

    public String getUserIDFromLogin() {
        return userID;
    }
}