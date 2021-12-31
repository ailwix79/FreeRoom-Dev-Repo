package server.dao;

import domain.Room;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RoomDAOTest {

    @Test
    public void getRooms() {
        ArrayList<Room> lista = new ArrayList<>();
        try {
            RoomDAO.getRooms(lista);
            //Se comprueba que la lista no está vacía
            assertEquals(lista.size()>0,Boolean.TRUE);
            //Se comprueba que devuelve objetos que tipo Room.
            assertEquals(lista.get(1).getClass(),Room.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getRoomsDayTime() {
        ArrayList<Room> lista = new ArrayList<>();
        try {
            RoomDAO.getRoomsDayTime(lista,"MON",9);
            //Se comprueba que la lista no está vacía
            assertEquals(lista.size()>0,Boolean.TRUE);
            //Se comprueba que devuelve objetos que tipo Room.
            assertEquals(lista.get(1).getClass(),Room.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetRoomsDayTime() {
        ArrayList<Room> lista = new ArrayList<>();
        try {
            RoomDAO.getRoomsDayTime(lista,"101");
            //Se comprueba que la lista no está vacía
            assertEquals(lista.size()>0,Boolean.TRUE);
            //Se comprueba que devuelve objetos que tipo Room.
            assertEquals(lista.get(1).getClass(),Room.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}