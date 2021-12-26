import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Teacher_temp extends JFrame {

    public Teacher_temp () {

        //Main Frame
        JFrame mainframe = new JFrame("Free Room");
        mainframe.setVisible(true);
        mainframe.setSize(1000, 800);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //First Container
        JPanel pnl1 = new JPanel();
        pnl1.setBackground(Color.lightGray);



        //Second Container
        JPanel pnl2 = new JPanel();

        JPanel pnl3 = new JPanel();
        JCheckBox cbox1 = new JCheckBox("Show all free room");
        Choice ch1 = new Choice();
        ch1.add("Floor 1");
        ch1.add("Floor 2");
        ch1.add("Floor 3");
        ch1.add("Floor 4");
        ch1.add("Floor 5");


        class BtnReservListener implements ActionListener {
            // ActionEvent handler - Called back upon button-click.
            @Override
            public void actionPerformed(ActionEvent evt) {
                JFrame Rsv_temp = new JFrame("Reserve a classroom");
                Rsv_temp.setVisible(true);
                Rsv_temp.setSize(400,400);
                Rsv_temp.setLayout(new GridLayout(9,1));
                Choice rsv_room = new Choice();
                Choice rsv_date = new Choice();
                Choice rsv_time_start = new Choice();
                Choice rsv_time_end = new Choice();
                JButton vldate_rsv = new JButton("Validate");
                Rsv_temp.add(new JLabel("Room"));Rsv_temp.add(rsv_room);
                Rsv_temp.add(new JLabel("Date"));Rsv_temp.add(rsv_date);
                Rsv_temp.add(new JLabel("Start Time"));Rsv_temp.add(rsv_time_start);
                Rsv_temp.add(new JLabel("End Time"));Rsv_temp.add(rsv_time_end);
                Rsv_temp.add(vldate_rsv);
            }
        }
        Button reserv_button = new Button("Reserve");
        BtnReservListener listener = new BtnReservListener();
        reserv_button.addActionListener(listener);
        pnl3.setLayout(new GridLayout(3,5));
        for (int i=0;i<5;i++){
            pnl3.add(new JPanel());
        }
        pnl3.add(ch1);
        pnl3.add(new JPanel());pnl3.add(new JPanel());
        pnl3.add(cbox1);
        pnl3.add(reserv_button);
        for (int i=0;i<5;i++){
            pnl3.add(new JPanel());
        }


        JPanel pnl4 = new JPanel();


        JPanel pnl5 = new JPanel();
        pnl5.setBackground(Color.lightGray);
        pnl5.setLayout(new GridLayout(5,5,5,5));
        for (int i=0;i<25;i++) {
            pnl5.add(new JPanel());
        }


        pnl2.setLayout(new BorderLayout(3,3));
        pnl2.add(pnl3,BorderLayout.NORTH);
        pnl2.add(pnl4, BorderLayout.WEST);
        pnl2.add(pnl5,BorderLayout.CENTER);


        mainframe.setLayout(new BorderLayout(3,3));
        mainframe.add(pnl1,BorderLayout.NORTH);
        mainframe.add(pnl2,BorderLayout.CENTER);


    }

}
