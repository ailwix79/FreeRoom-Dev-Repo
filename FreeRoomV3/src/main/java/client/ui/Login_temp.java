package client.ui;

import client.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** Ventana de autenticación de usuario */

public class Login_temp extends JFrame {

    String userID;

    public Login_temp() {

        JPanel panel;
        JLabel user_label, password_label, role_label;
        JTextField userName_text;
        JPasswordField password_text;
        JButton submit;

        Icon imagenLogo = new ImageIcon(Student_temp.class.getResource("/icons/logo.png"));
        JLabel logo = new JLabel(imagenLogo);
        JPanel pnlNorte = new JPanel(new FlowLayout());
        pnlNorte.setBorder(new EmptyBorder(10,0,0,0));
        pnlNorte.add(logo);

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
        submit = new JButton("Login");
        submit.setFont(new Font("Arial",Font.BOLD,22));

        panel = new JPanel(new GridLayout(3, 2));
        panel.add(role_label);
        panel.add(ch1);
        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);


        // LOGIN BUTTON HANDLER
        submit.addActionListener(e -> {
            boolean isLoginTrue = (boolean) Client.getData("loginData",userName_text.getText(), String.valueOf(password_text.getPassword()), ch1.getSelectedItem(), null,null);

            if(isLoginTrue && ch1.getSelectedIndex() == 0) {
                dispose();
                userID = userName_text.getText();
                new Teacher_temp(userID);
            }
            else if (isLoginTrue && ch1.getSelectedIndex() == 1) {
                dispose();
                userID = userName_text.getText();
                new Admin_temp(userID);
            }
            else if (isLoginTrue && ch1.getSelectedIndex() == 2) {
                dispose();
                userID = userName_text.getText();
                new Student_temp();
            }
            else
                JOptionPane.showMessageDialog(this, "Incorrect Username / Password", "ERROR", JOptionPane.ERROR_MESSAGE);
        });
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(pnlNorte,BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        JPanel pnlSouth = new JPanel(new FlowLayout());
        pnlSouth.add(submit);
        add(pnlSouth,BorderLayout.SOUTH);
        setSize(400, 250);
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