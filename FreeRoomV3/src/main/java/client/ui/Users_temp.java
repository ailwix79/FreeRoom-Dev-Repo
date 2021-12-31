package client.ui;

import client.Client;
import domain.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

/** Ventana para la gestión de usuarios por parte de un usuario Administrador. Incluye vista en forma
 * de tabla de los usuarios y botones para añadir o eliminar usuarios */

public class Users_temp extends JFrame {

    DefaultTableModel tabla1 = new DefaultTableModel();

    public static void main(String[] args)
    {
        new Users_temp();
    }

    public Users_temp() throws HeadlessException {
        super("FreeRoom User Management");

        this.setLayout(new BorderLayout());

        //Texto superior
        JLabel lblTitulo = new JLabel("Lista de usuarios");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        JPanel pnlNorte = new JPanel(new FlowLayout());
        pnlNorte.add(lblTitulo);
        URL url = Users_temp.class.getResource("/icons/addCliente1.png");
        Icon icon = new ImageIcon(url);
        JButton btnAddCliente = new JButton("Añadir usuario",icon);
        btnAddCliente.addActionListener(e ->
        {
            new NewUser_temp(Users_temp.this);
        });
        pnlNorte.add(btnAddCliente);


        //Boton eliminar usuario
        URL url2 = Users_temp.class.getResource("/icons/EliminarCliente1.png");
        Icon icon2 = new ImageIcon(url2);
        JButton btnDeleteUser = new JButton("Eliminar usuario seleccionado",icon2);
        pnlNorte.add(btnDeleteUser);

        this.add(pnlNorte,BorderLayout.NORTH);


        //Tabla
        tabla1.addColumn("User");
        tabla1.addColumn("Password");
        tabla1.addColumn("Role");

        cargarTabla();

        JTable tabla2 = new JTable(tabla1){
            //Sobreescribimos el metodo isCellEditable para que no se puedan modificar los datos de la tabla.
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };

        //Configuracion tabla
        tabla2.getTableHeader().setReorderingAllowed(false);
        tabla2.setShowHorizontalLines(true);
        tabla2.setShowVerticalLines(false);
        tabla2.setCellSelectionEnabled(false);
        tabla2.setRowSelectionAllowed(true);
        tabla2.setGridColor(Color.BLACK);
        tabla2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane panelcentro = new JScrollPane(tabla2);

        this.add(panelcentro, BorderLayout.CENTER);

        this.setSize(700, 300);
        this.setLocationRelativeTo(null);
        //this.pack();
        this.setVisible(true);

        btnDeleteUser.addActionListener(e ->
        {
            borrarUsuarioSeleccionado(tabla2);
        });

    }

    /**
     Refresca la información mostrada en la tabla
     */
    public void cargarTabla()
    {

        tabla1.setRowCount(0);
        //Lectura de los datos de todos los usuarios
        ArrayList<Login> users = (ArrayList<Login>) Client.getData("users",null,null,null,null,null);
        for (Login login:users
        ) {
            System.out.println(login);
        }
        users.stream().forEach(l->tabla1.addRow(new Object[]{l.getUser(), l.getPasswd(), l.getRole()}));
    }

    /**
     Elimina el usuario seleccionado en la tabla
     @param tabla2 tabla en la que se ha seleccionado al usuario
     */

    public void borrarUsuarioSeleccionado(JTable tabla2)
    {
        int row = tabla2.getSelectedRow();
        if (row == -1)
            JOptionPane.showMessageDialog(this,"No se ha seleccionado ningún usuario", "Usuario no borrado", JOptionPane.ERROR_MESSAGE);
        else
        {
            Login l = getUserWithID((String) tabla2.getValueAt(row,0));
            int resp = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar a " + l.getUser() + " " + "?","Eliminar usuario",JOptionPane.OK_CANCEL_OPTION);
            if (resp==0)
            {
                Client.getData("users",l.getUser(),null,null,null,null);
                cargarTabla();
            }
        }

    }

    public Login getUserWithID(String username)
    {
        ArrayList<Login> listaClientes = (ArrayList<Login>) Client.getData("users",null,null,null,null,null);
        for(Login l : listaClientes)
        {
            if (l.getUser().equals(username))
                return l;
        }
        return null;
    }


}
