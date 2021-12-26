import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;

public class Student_temp extends JFrame {

    public Student_temp () {

        //Main Frame
        JFrame mainframe = new JFrame("FreeRoom");

        //First Container
        JPanel pnl1 = new JPanel();
        pnl1.setBackground(Color.lightGray);

        //Second Container
        JPanel pnl2 = new JPanel();

        JPanel pnl3 = new JPanel();
        JCheckBox cbox1 = new JCheckBox("Show all free rooms");
        URL url = Student_temp.class.getResource("logo.png");
        JLabel lblTitulo = new JLabel(new ImageIcon(url), JLabel.CENTER);
        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 20));

        Choice ch1 = new Choice();
        ch1.add("Floor 1");
        ch1.add("Floor 2");
        ch1.add("Floor 3");
        ch1.add("Floor 4");
        ch1.add("Floor 5");

        pnl3.setLayout(new BorderLayout());
        pnl3.add(lblTitulo,BorderLayout.WEST);
        pnl3.add(ch1,BorderLayout.CENTER);
        pnl3.add(cbox1,BorderLayout.EAST);
        pnl3.add(btnLogin,BorderLayout.EAST);

        pnl3.setBorder(new EmptyBorder(10, 20, 10, 10));
        for (int i=0;i<5;i++){
            pnl3.add(new JPanel());
        }

        JPanel pnl5 = new JPanel();
        pnl5.setBackground(Color.lightGray);
        pnl5.setLayout(new GridLayout(5,5,5,5));
        pnl5.setBorder(new EmptyBorder(5, 5, 5, 5));
        ArrayList lista = conexionDatos();
        ListIterator it = lista.listIterator();
        while(it.hasNext()) {
            Room room = (Room)it.next();
            JPanel pnlMini = new JPanel();
            pnlMini.setLayout(new GridBagLayout());
            JLabel label = new JLabel(room.getId());
            label.setFont(new Font("Verdana", Font.BOLD, 35));
            pnlMini.add(label);
            if (room.getIsFree().equals("0"))
                pnlMini.setBackground(Color.RED);
            else
                pnlMini.setBackground(Color.GREEN);
            pnl5.add(pnlMini);
        }
        /*
        for (int i=0;i<25;i++) {
            JPanel pnlMini = new JPanel();
            pnlMini.setBackground(Color.green);
            pnl5.add(pnlMini);
        }
        */
        pnl2.setLayout(new BorderLayout(3,3));
        pnl2.add(pnl3,BorderLayout.NORTH);
        pnl2.add(pnl5,BorderLayout.CENTER);


        mainframe.setLayout(new BorderLayout(3,3));
        mainframe.add(pnl1,BorderLayout.NORTH);
        mainframe.add(pnl2,BorderLayout.CENTER);

        mainframe.setSize(1000, 800);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setVisible(true);
    }
    
    private ArrayList conexionDatos(){
        ArrayList lista = Client.obtenerDatos();
        //lista.forEach((n) -> System.out.println(n));
        return lista;
        }
}
