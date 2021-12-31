package client.ui;

import client.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/** Ventana de creación nuevo usuario con botones de Cancelar y Guardado */

public class NewUser_temp extends JFrame {

    public static void main(String[] args) {
        new NewUser_temp(null);
    }

    public NewUser_temp(Users_temp ventanaUser)
    {
        super("Añadir usuario");
        this.setLayout(new BorderLayout());

        //Texto superior
        URL url = NewUser_temp.class.getResource("/icons/addCliente1.png");
        JLabel lblTitulo = new JLabel("Nuevo usuario",new ImageIcon(url), JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        JPanel pnlNorte = new JPanel(new FlowLayout());
        pnlNorte.add(lblTitulo);
        this.add(pnlNorte,BorderLayout.NORTH);

        JPanel pnlCentro = new JPanel(new GridLayout(3,2));
        JLabel lblRole = new JLabel("Role: ");
        Choice ch1 = new Choice();
        ch1.add("Professor");
        ch1.add("Administrative");
        ch1.add("Student");
        JLabel lblUserID = new JLabel("UserID: ");
        JTextField txtUsername = new JTextField(10);
        JLabel lblApellido = new JLabel("Password: ");
        JTextField txtPasswd = new JTextField(10);
        pnlCentro.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnlCentro.add(lblRole);
        pnlCentro.add(ch1);
        pnlCentro.add(lblUserID);
        pnlCentro.add(txtUsername);
        pnlCentro.add(lblApellido);
        pnlCentro.add(txtPasswd);

        this.add(pnlCentro, BorderLayout.CENTER);

        JPanel pnlSur = new JPanel(new FlowLayout());
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnGuardar = new JButton("Guardar");

        pnlSur.add(btnCancelar);
        pnlSur.add(btnGuardar);

        btnCancelar.addActionListener(e ->
        {
            this.dispose();
        });

        btnGuardar.addActionListener(e ->
        {
            boolean confirmation = (boolean) Client.getData("users",txtUsername.getText(),txtPasswd.getText(),ch1.getSelectedItem(),null,null);
            if (confirmation) {
                JOptionPane.showConfirmDialog(NewUser_temp.this,"New user created successfully","Success",JOptionPane.DEFAULT_OPTION);
                ventanaUser.cargarTabla();
                this.dispose();
            }
            else
                JOptionPane.showMessageDialog(NewUser_temp.this, "Failed to add new user", "ERROR", JOptionPane.ERROR_MESSAGE);
        });

        this.add(pnlSur, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.setSize(500, 300);
        this.pack();
        this.setVisible(true);

    }
}
