import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Student_temp extends JFrame {

    public Student_temp() {

        //Main Frame
        JFrame login_temp = new Login_temp();
        JFrame mainframe = new JFrame("FreeRoom");

        //First Container
        JPanel pnl1 = new JPanel();
        pnl1.setBackground(Color.lightGray);

        //Second Container
        JPanel pnl2 = new JPanel();

        JPanel pnl3 = new JPanel(new FlowLayout());

        JCheckBox cbox1 = new JCheckBox("Show all free classrooms");
        JLabel lblTitulo = new JLabel(new ImageIcon(Student_temp.class.getResource("logo.png")), JLabel.CENTER);

        Choice ch1 = new Choice();
        ch1.add("All");
        ch1.add("Floor 1"); // 1
        ch1.add("Floor 2"); // 2
        ch1.add("Floor 3"); // 3
        ch1.add("Floor 4"); // 4
        ch1.add("Floor 5"); // 5

        pnl3.add(lblTitulo,BorderLayout.CENTER);
        pnl3.add(ch1);
        pnl3.add(cbox1);

        pnl3.setBorder(new EmptyBorder(10, 20, 10, 10));
        for (int i = 0; i < 5; i++) {
            pnl3.add(new JPanel());
        }

        JPanel pnl5 = new JPanel();
        pnl5.setBackground(Color.lightGray);
        pnl5.setLayout(new GridLayout(5, 5, 5, 5));
        pnl5.setBorder(new EmptyBorder(5, 5, 5, 5));
        ArrayList<Room> list = dataConnection();

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
                mainframe.revalidate();
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
                    mainframe.revalidate();
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
                    mainframe.revalidate();
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
                mainframe.revalidate();
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
                mainframe.revalidate();
            }
        });

        pnl2.setLayout(new BorderLayout(3, 3));
        pnl2.add(pnl3, BorderLayout.NORTH);
        pnl2.add(pnl5, BorderLayout.CENTER);


        mainframe.setLayout(new BorderLayout(3, 3));
        mainframe.add(pnl1, BorderLayout.NORTH);
        mainframe.add(pnl2, BorderLayout.CENTER);

        mainframe.setSize(1000, 800);
        mainframe.setVisible(true);

        mainframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        mainframe.setLocationRelativeTo(null);
    }
    private ArrayList<Room> dataConnection() {
        ArrayList<Room> list = (ArrayList<Room>) Client.getData("roomDataDayTime",TimeManager.getCurrentWeekDay(),TimeManager.getCurrentHour() , null,null,null);
        return list;
    }
}
