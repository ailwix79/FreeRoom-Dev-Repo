package client.ui;

import client.Client;
import domain.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** Ventana de diálogo para realizar una reserva.
 * Es lanzada solo por Profesores o Administrador.
 * El parámetro maxLimit activa o no el límite de tiempo de reserva.
 * El parámetro userID es el nombre de usuario que realiza la reserva */

public class Reservar_temp extends JFrame{

    int maxReserveTime = 2;

    public Reservar_temp(ArrayList<Room> list, String userID,boolean maxLimit) {
        super("Reserve a classroom");

        this.setLayout(new GridLayout(9,1));

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
        this.add(new JLabel("Classroom"));this.add(rsv_room);
        this.add(new JLabel("Date"));this.add(rsv_date);
        this.add(new JLabel("Start Time"));this.add(rsv_time_start);
        this.add(new JLabel("End Time"));this.add(rsv_time_end);
        this.add(vldate_rsv);

        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        vldate_rsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rsv_time_start.getSelectedIndex() >= rsv_time_end.getSelectedIndex())
                    JOptionPane.showMessageDialog(null, "Reservation Failed. Please check the specified Start / End time", "ERROR", JOptionPane.ERROR_MESSAGE);
                else if (Integer.parseInt(hor_aux[rsv_time_end.getSelectedIndex()]) - Integer.parseInt(hor_aux[rsv_time_start.getSelectedIndex()]) > maxReserveTime && maxLimit)
                    JOptionPane.showMessageDialog(null, "Reservation Failed. You may not reserve more than " + maxReserveTime +" hours together (Time period fixed by the Admin)", "ERROR", JOptionPane.ERROR_MESSAGE);
                else {
                    Boolean reserveVerdict = (Boolean) Client.getData("reserveData", rsv_room.getSelectedItem(), hor_aux[rsv_time_start.getSelectedIndex()], hor_aux[rsv_time_end.getSelectedIndex()], rsv_date.getSelectedItem(), userID);
                    if (reserveVerdict)
                        JOptionPane.showMessageDialog(null, "Reservation Done Successfully", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "Reservation Failed. Classroom Not available for the specified parameters", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
