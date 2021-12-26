import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Teacher_temp extends JFrame {

    ArrayList<Room> list;
    int maxReserveTime = 2;

    public Teacher_temp () {

        Login_temp login_temp = new Login_temp();
        //Main Frame
        JFrame mainframe = new JFrame("FreeRoom");
        mainframe.setVisible(true);
        mainframe.setSize(1000, 800);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //First Container
        JPanel pnl1 = new JPanel();
        pnl1.setBackground(Color.lightGray);

        //Second Container
        JPanel pnl2 = new JPanel();

        JPanel pnl3 = new JPanel();
        JCheckBox cbox1 = new JCheckBox("Show all free classrooms");
        JLabel lblTitulo = new JLabel(new ImageIcon(Student_temp.class.getResource("logo.png")), JLabel.CENTER);
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
                JFrame Rsv_temp = new JFrame("Reserve a classroom");
                Rsv_temp.setVisible(true);
                Rsv_temp.setSize(400,400);
                Rsv_temp.setLocationRelativeTo(null);
                Rsv_temp.setLayout(new GridLayout(9,1));

                Choice rsv_room = new Choice();
                for (int i=0;i<list.size();i++){
                    rsv_room.add(list.get(i).getId());
                }
                Choice rsv_date = new Choice();

                String [] days= {"MON","TUE","WED","THU","FRI","SAT","SUN"};
                for (int i=0;i<7;i++){
                    rsv_date.add(days[i]);
                }

                Choice rsv_time_start = new Choice();
                String [] hor={"08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00"};
                String [] hor_aux={"8","9","10","11","12","13","14","15","16","17","18","19","20"};

                for (int i=0;i<13;i++){
                    rsv_time_start.add(hor[i]);
                }

                Choice rsv_time_end = new Choice();

                for (int i=0;i<13;i++){
                    rsv_time_end.add(hor[i]);
                }

                JButton vldate_rsv = new JButton("Validate");
                Rsv_temp.add(new JLabel("Classroom"));Rsv_temp.add(rsv_room);
                Rsv_temp.add(new JLabel("Date"));Rsv_temp.add(rsv_date);
                Rsv_temp.add(new JLabel("Start Time"));Rsv_temp.add(rsv_time_start);
                Rsv_temp.add(new JLabel("End Time"));Rsv_temp.add(rsv_time_end);
                Rsv_temp.add(vldate_rsv);

                vldate_rsv.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (rsv_time_start.getSelectedIndex() >= rsv_time_end.getSelectedIndex())
                            JOptionPane.showMessageDialog(Teacher_temp.this, "Reservation Failed. Please check the specified Start / End time", "ERROR", JOptionPane.ERROR_MESSAGE);
                        else if (Integer.parseInt(hor_aux[rsv_time_end.getSelectedIndex()]) - Integer.parseInt(hor_aux[rsv_time_start.getSelectedIndex()]) > maxReserveTime)
                            JOptionPane.showMessageDialog(Teacher_temp.this, "Reservation Failed. You may not reserve more than " + maxReserveTime +" hours together (Time period fixed by the Admin)", "ERROR", JOptionPane.ERROR_MESSAGE);
                        else {
                            Boolean reserveVerdict = (Boolean) Client.getData("reserveData", rsv_room.getSelectedItem(), hor_aux[rsv_time_start.getSelectedIndex()], hor_aux[rsv_time_end.getSelectedIndex()], rsv_date.getSelectedItem(), login_temp.getUserIDFromLogin());
                            if (reserveVerdict)
                                JOptionPane.showMessageDialog(Teacher_temp.this, "Reservation Done Successfully", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(Teacher_temp.this, "Reservation Failed. Classroom Not available for the specified parameters", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

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
        list = dataConnection();

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

        pnl2.setLayout(new BorderLayout(3,3));
        pnl2.add(pnl3,BorderLayout.NORTH);
        pnl2.add(pnl4, BorderLayout.WEST);
        pnl2.add(pnl5,BorderLayout.CENTER);

        mainframe.setLayout(new BorderLayout(3,3));
        mainframe.add(pnl1,BorderLayout.NORTH);
        mainframe.add(pnl2,BorderLayout.CENTER);

        mainframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?", "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        mainframe.setLocationRelativeTo(null);
    }

    private ArrayList<Room> dataConnection(){
        ArrayList<Room> list = (ArrayList<Room>) Client.getData("roomDataDayTime",TimeManager.getCurrentWeekDay(),TimeManager.getCurrentHour() , null,null,null);
        return list;
    }
}
