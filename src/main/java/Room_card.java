import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// TIMETABLE POP UP FUNCTION.

public class Room_card extends Button{
    public Room_card(int classNumber) {

        class PopTimeTable implements ActionListener {
            // ActionEvent handler - Called back upon button-click.
            @Override
            public void actionPerformed(ActionEvent evt) {
                JFrame TimeTable = new JFrame("Timetable: Room "+classNumber);
                JLabel roomlbl = new JLabel("Room " + classNumber);
                TimeTable.add(roomlbl);
                TimeTable.setBackground(Color.lightGray);
                TimeTable.setLayout(new GridBagLayout());
                GridBagConstraints constr = new GridBagConstraints();

                if (true) {
                    constr.fill = GridBagConstraints.BOTH;
                    constr.weighty=1;
                    constr.weightx=1;
                    constr.insets = new Insets(3,3,3,3);
                }

                String [] days= {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
                for (int i=1;i<8;i++){
                    constr.gridx=i;
                    constr.gridy=0;
                    TimeTable.add(new JLabel(days[i-1]),constr);
                }
                String [] hor={"08h-09h","09h-10h","10h-11h","11h-12h","12h-13h","13h-14h","14h-15h","15h-16h","16h-17h","17h-18h","18h-19h","19h-20h"};
                for (int i=1;i<13;i++){
                    constr.gridx=0;
                    constr.gridy=i;
                    TimeTable.add(new JLabel(hor[i-1]),constr);
                }

                TimeTable.setSize(720,1000);
                TimeTable.setVisible(true);

                //Data
                ArrayList<Room> list = dataConnection(String.valueOf(classNumber));



                //TimeTable Cells
                for(int i=1; i<8; i++){
                    for ( int j=1; j<13; j++){
                        constr.gridx=i;
                        constr.gridy=j;
                        JLabel x= new JLabel();
                        x.setOpaque(true);
                        //x.setBackground(Color.GREEN);
                        if (getOcupation(list,days[i-1].toUpperCase(),j+7))
                            x.setBackground(Color.GREEN);
                        else
                            x.setBackground(Color.RED);
                        TimeTable.add(x,constr);
                    }
                }

            }
        }
        PopTimeTable listen_tt = new PopTimeTable();
        this.addActionListener(listen_tt);


    }

    private ArrayList<Room> dataConnection(String roomID) {
        ArrayList<Room> list = (ArrayList<Room>) Client.getData("roomSingle",roomID,null , null,null,null);
        return list;
    }

    private boolean getOcupation(ArrayList<Room> lista,String dia,int hora) {
        for (Room room: lista
             ) {
            if(room.getCdate().equals(dia) && room.getCtime()==hora){
                if (room.getIsFree())
                        return true;
                else
                    return false;
            }
        }
        return false;
    }

    //private void consultar

}
