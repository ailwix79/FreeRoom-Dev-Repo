package client.ui;

import client.Client;
import domain.Room;
import server.dao.DBConnectionApp;
import util.TimeManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/** Ventana principal del Administrador. Vista general estado aulas y botones con
 * funciones privilegiadas y vista de las aulas en tiempo real */

public class Admin_temp extends JFrame {

    private JButton ReFresh;
    private ArrayList<Room> list;
    private JPanel pnl5;
    private JPanel pnlMini;

    public static void main(String[] args) {
        new Admin_temp(null);
    }

    public Admin_temp(String userID) {

        //Main Frame
        super("FreeRoom - Logged as " + userID);

        //First Container
        JPanel pnl1 = new JPanel();
        pnl1.setBackground(Color.lightGray);

        //Second Container
        JPanel pnl2 = new JPanel();

        JPanel pnl3 = new JPanel(new GridLayout(2,1));

        JPanel pnl3arriba = new JPanel(new FlowLayout());
        JPanel pnl3abajo = new JPanel(new FlowLayout());

        JCheckBox cbox1 = new JCheckBox("Mostrar solo aulas disponibles");
        JLabel lblTitulo = new JLabel(new ImageIcon(Admin_temp.class.getResource("/icons/logo.png")), JLabel.CENTER);
        JButton btnUsers = new JButton("Gestionar usuarios");
        btnUsers.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnAddRoom = new JButton("Añadir aula");
        btnAddRoom.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnDeleteRoom = new JButton("Eliminar aula");
        btnDeleteRoom.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel lblFiltrado = new JLabel("Filtrado por plantas: ");

        JButton btnReservar = new JButton("Reservar aula");
        btnReservar.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnCSV = new JButton("Cargar archivo CSV");
        btnCSV.setFont(new Font("Arial", Font.BOLD, 20));

        Choice ch1 = new Choice();
        ch1.add("All");
        ch1.add("Floor 1"); // 1
        ch1.add("Floor 2"); // 2
        ch1.add("Floor 3"); // 3
        ch1.add("Floor 4"); // 4
        ch1.add("Floor 5"); // 5

        pnl3arriba.add(lblTitulo, BorderLayout.CENTER);
        pnl3arriba.add(lblFiltrado);
        pnl3arriba.add(ch1);
        pnl3arriba.add(cbox1);
        pnl3abajo.add(btnUsers);
        pnl3abajo.add(btnAddRoom);
        pnl3abajo.add(btnDeleteRoom);
        pnl3abajo.add(btnReservar);
        pnl3abajo.add(btnCSV);
        ReFresh = new JButton("Refrescar");
        pnl3arriba.add(ReFresh);

        pnl3.add(pnl3arriba);
        pnl3.add(pnl3abajo);

        pnl3.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl5 = new JPanel();

        pnl5.setBackground(Color.lightGray);
        pnl5.setLayout(new GridLayout(5, 5, 5, 5));
        pnl5.setBorder(new EmptyBorder(5, 5, 5, 5));

        refresh(); //Descarga de datos y mostrar en pantalla

        cbox1.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                pnl2.remove(pnl5);
                pnl5.removeAll();
                for (Room room : list) {
                    if (room.getIsFree()) {
                        JPanel pnlMini = new JPanel();
                        pnlMini.setLayout(new GridBagLayout());
                        Room_card classroom = new Room_card(Integer.parseInt(room.getId()));
                        pnlMini.add(classroom);
                        JLabel label = new JLabel(room.getId());
                        label.setFont(new Font("Verdana", Font.BOLD, 35));
                        pnlMini.add(label);
                        pnlMini.setBackground(Color.GREEN);
                        pnl5.add(pnlMini);
                    }
                }
                pnl2.add(pnl5, BorderLayout.CENTER);
                pnl2.repaint();
                this.revalidate();
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                if (ch1.getSelectedIndex() == 0) {
                    pnl2.remove(pnl5);
                    pnl5.removeAll();
                    for (Room room : list) {
                        JPanel pnlMini = new JPanel();
                        pnlMini.setLayout(new GridBagLayout());
                        Room_card classroom = new Room_card(Integer.parseInt(room.getId()));
                        pnlMini.add(classroom);
                        JLabel label = new JLabel(room.getId());
                        label.setFont(new Font("Verdana", Font.BOLD, 35));
                        pnlMini.add(label);

                        if (!room.getIsFree())
                            pnlMini.setBackground(Color.RED);
                        else
                            pnlMini.setBackground(Color.GREEN);
                        pnl5.add(pnlMini);
                    }
                    pnl2.add(pnl5, BorderLayout.CENTER);
                    pnl2.repaint();
                    this.revalidate();
                } else {
                    pnl2.remove(pnl5);
                    pnl5.removeAll();
                    for (Room room : list) {
                        if (room.getFloor() == ch1.getSelectedIndex()) {
                            JPanel pnlMini = new JPanel();
                            pnlMini.setLayout(new GridBagLayout());
                            Room_card classroom = new Room_card(Integer.parseInt(room.getId()));
                            pnlMini.add(classroom);
                            JLabel label = new JLabel(room.getId());
                            label.setFont(new Font("Verdana", Font.BOLD, 35));
                            pnlMini.add(label);

                            if (!room.getIsFree())
                                pnlMini.setBackground(Color.RED);
                            else
                                pnlMini.setBackground(Color.GREEN);
                            pnl5.add(pnlMini);
                        }
                    }
                    pnl2.add(pnl5, BorderLayout.CENTER);
                    pnl2.repaint();
                    this.revalidate();
                }
            }
        });

        ch1.addItemListener(e -> {
            if (ch1.getSelectedIndex() == 0) {
                pnl2.remove(pnl5);
                pnl5.removeAll();
                for (Room room : list) {
                    JPanel pnlMini = new JPanel();
                    pnlMini.setLayout(new GridBagLayout());
                    Room_card classroom = new Room_card(Integer.parseInt(room.getId()));
                    pnlMini.add(classroom);
                    JLabel label = new JLabel(room.getId());
                    label.setFont(new Font("Verdana", Font.BOLD, 35));
                    pnlMini.add(label);

                    if (!room.getIsFree())
                        pnlMini.setBackground(Color.RED);
                    else
                        pnlMini.setBackground(Color.GREEN);
                    pnl5.add(pnlMini);
                }
                pnl2.add(pnl5, BorderLayout.CENTER);
                pnl2.repaint();
                this.revalidate();
            } else {
                pnl2.remove(pnl5);
                pnl5.removeAll();
                for (Room room : list) {
                    if (room.getFloor() == ch1.getSelectedIndex()) {
                        JPanel pnlMini = new JPanel();
                        pnlMini.setLayout(new GridBagLayout());
                        Room_card classroom = new Room_card(Integer.parseInt(room.getId()));
                        pnlMini.add(classroom);
                        JLabel label = new JLabel(room.getId());
                        label.setFont(new Font("Verdana", Font.BOLD, 35));
                        pnlMini.add(label);

                        if (!room.getIsFree())
                            pnlMini.setBackground(Color.RED);
                        else
                            pnlMini.setBackground(Color.GREEN);
                        pnl5.add(pnlMini);
                    }
                }
                pnl2.add(pnl5, BorderLayout.CENTER);
                pnl2.repaint();
                this.revalidate();
            }
        });

        pnl2.setLayout(new BorderLayout(3, 3));
        pnl2.add(pnl3, BorderLayout.NORTH);
        pnl2.add(pnl5, BorderLayout.CENTER);


        this.setLayout(new BorderLayout(3, 3));
        this.add(pnl1, BorderLayout.NORTH);
        this.add(pnl2, BorderLayout.CENTER);

        this.setSize(1100, 800);
        this.setVisible(true);

        btnUsers.addActionListener(e -> {
            new Users_temp();
        });

        ReFresh.addActionListener(e -> {
            refresh();
        });

        btnAddRoom.addActionListener(e -> {
            String s = (String) JOptionPane.showInputDialog(this, "Introduzca el ID del aula que desea añadir", "Añadir aula", JOptionPane.PLAIN_MESSAGE);
            if (s == null)
                return;
            String ps = (String) JOptionPane.showInputDialog(this, "Introduzca la planta en la que se encuentra el aula", "Añadir aula", JOptionPane.PLAIN_MESSAGE);
            if (ps == null || ps.equals(""))
                return;
            else {
                int p = Integer.parseInt(ps);
                JFrame espera = new JFrame("Un momento");
                JLabel lbl = new JLabel("Creando nueva aula...");
                lbl.setFont(new Font("Arial",Font.BOLD,24));
                JPanel pnlAux = new JPanel(new FlowLayout());
                pnlAux.add(lbl);
                espera.setLayout(new FlowLayout());
                espera.add(pnlAux);
                espera.setSize(400,100);
                espera.setLocationRelativeTo(null);
                espera.setVisible(true);
                Boolean conf = (Boolean) Client.getData("addRoom", s, p, null, null, null);
                if (conf) {
                    espera.dispose();
                    JOptionPane.showMessageDialog(this, "Aula creada correctamente");
                }
                else
                    JOptionPane.showMessageDialog(this, "Se ha producido un error al añadir el aula, es posible que el aula ya exista","ERROR",JOptionPane.ERROR_MESSAGE);
                refresh();
            }
        });

        btnDeleteRoom.addActionListener(e -> {
            String s = (String) JOptionPane.showInputDialog(this, "Introduzca el ID del aula que desea eliminar", "Eliminar aula", JOptionPane.PLAIN_MESSAGE);
            if (s == null)
                return;
            else {
                Boolean conf = (Boolean) Client.getData("deleteRoom", s, null, null, null, null);
                if (conf)
                    JOptionPane.showMessageDialog(this, "Aula borrada correctamente");
                else
                    JOptionPane.showMessageDialog(this, "Se ha producido un error al borrar el aula","ERROR",JOptionPane.ERROR_MESSAGE);
                refresh();
            }
        });

        btnReservar.addActionListener(e -> {
            //Llamada a la ventana de reserva sin limite de tiempo de reserva
            JFrame reserva = new Reservar_temp(list,userID, false);
        });

        btnCSV.addActionListener(e -> {
            FileDialog fd = new FileDialog(this, "Cargar archivo CSV datos aulas", FileDialog.LOAD);
            fd.setDirectory("C:\\");
            fd.setFile("*.csv");
            fd.setVisible(true);
            String filename = fd.getFile();
            //Falta conexion con servidor, funcion de carga de datos creada en DBConnectionApp
        });


        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        this.setLocationRelativeTo(null);
    }

    private void dataConnection() {
        /** Realiza la conexión con el servidor para obtener datos */
        list = (ArrayList<Room>) Client.getData("roomDataDayTime", TimeManager.getCurrentWeekDay(), TimeManager.getCurrentHour(), null, null, null);
    }

    private void refresh(){
        /** Función encargada de refrescar la pantalla con datos actualizados */
        dataConnection();
        pnl5.removeAll();
        for (Room room : list) {
            JPanel pnlMini = new JPanel();
            pnlMini.setLayout(new GridBagLayout());
            Room_card classroom = new Room_card(Integer.parseInt(room.getId()));
            pnlMini.add(classroom);
            JLabel label = new JLabel(room.getId());
            label.setFont(new Font("Verdana", Font.BOLD, 35));
            pnlMini.add(label);
            if (!room.getIsFree())
                pnlMini.setBackground(Color.RED);
            else
                pnlMini.setBackground(Color.GREEN);
            pnl5.add(pnlMini);
        }
        pnl5.repaint();
        this.revalidate();
    }
}
