package client.ui;

import client.Client;
import domain.Room;
import util.TimeManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/** Ventana de la aplicación para usuarios de tipo Profesor. Incluye funciones de Reserva
 * y visualización de horarios de aulas. */

public class Teacher_temp extends JFrame {

    private JButton ReFresh;
    private ArrayList<Room> list;
    private JPanel pnl5;
    private JPanel pnlMini;

    public Teacher_temp (String userID) {
        
        super("FreeRoom - logged as " + userID);

        //First Container
        JPanel pnl1 = new JPanel();
        pnl1.setBackground(Color.lightGray);

        //Second Container
        JPanel pnl2 = new JPanel();

        JPanel pnl3 = new JPanel();
        JCheckBox cbox1 = new JCheckBox("Show all free classrooms");
        JLabel lblTitulo = new JLabel(new ImageIcon(Student_temp.class.getResource("/icons/logo.png")), JLabel.CENTER);
        pnl3.add(lblTitulo,BorderLayout.CENTER);
        Choice ch1 = new Choice();
        ch1.add("All");
        ch1.add("Floor 1");
        ch1.add("Floor 2");
        ch1.add("Floor 3");
        ch1.add("Floor 4");
        ch1.add("Floor 5");

        // RESERVATION SYSTEM CODE.
        class BtnReservListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JFrame reserva = new Reservar_temp(list,userID,true);
            }
        }

        Button reserv_button = new Button("Reserve");
        BtnReservListener listener = new BtnReservListener();
        reserv_button.addActionListener(listener);
        pnl3.setLayout(new FlowLayout());

        for (int i=0;i<5;i++){
            pnl3.add(new JPanel());
        }
        pnl3.add(ch1);
        pnl3.add(new JPanel());
        pnl3.add(new JPanel());
        pnl3.add(cbox1);
        pnl3.add(reserv_button);

        for (int i=0;i<5;i++){
            pnl3.add(new JPanel());
        }

        JPanel pnl4 = new JPanel();

        JPanel pnl5 = new JPanel();
        pnl5.setBackground(Color.lightGray);
        pnl5.setLayout(new GridLayout(5,5,5,5));
        pnl5.setBorder(new EmptyBorder(5, 5, 5, 5));

        // THIS FUNCTION OBTAINS THE CLASSROOM DATA
        dataConnection();

        // CODE USED FOR CLASSROOM CLASSIFICATION
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
            }

            else if (e.getStateChange() == ItemEvent.DESELECTED) {
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
            }
            else {
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

        pnl2.setLayout(new BorderLayout(3,3));
        pnl2.add(pnl3,BorderLayout.NORTH);
        pnl2.add(pnl4, BorderLayout.WEST);
        pnl2.add(pnl5,BorderLayout.CENTER);

        this.setLayout(new BorderLayout(3,3));
        this.add(pnl1,BorderLayout.NORTH);
        this.add(pnl2,BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?", "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void dataConnection() {
        list = (ArrayList<Room>) Client.getData("roomDataDayTime", TimeManager.getCurrentWeekDay(), TimeManager.getCurrentHour(), null, null, null);
    }

    private void refresh(){
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
