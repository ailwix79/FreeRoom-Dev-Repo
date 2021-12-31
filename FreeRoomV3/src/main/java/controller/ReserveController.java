package controller;

import domain.Room;
import server.TableCreator;
import server.dao.ConectionSingleton;
import server.dao.DBConnectionApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/** Clase de gestión de reservas.  */

public class ReserveController {

    public static boolean checkReservation(String roomID, String startTime, String endTime, String date, String userID) throws Exception {
        /** Comprueba una reserva con hora de inicio y final */
        ResultSet rs = DBConnectionApp.classFullRead();
        Connection connection = ConectionSingleton.connectionSingleton();
        ArrayList<Room> lista = new ArrayList<>();

        lista.add(new Room(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4), rs.getInt(5)));
        while (rs.next()) {
            lista.add(new Room(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4), rs.getInt(5)));
        }

        if (endTime != null && startTime != null) {
            int i1 = Integer.parseInt(endTime) - Integer.parseInt(startTime);

            if (i1 == 1) {
                for (int i = 0; i < lista.size(); i++) {
                    // ONLY 1 HOUR
                    if (((lista.get(i).getId()).equals(roomID)) && ((lista.get(i).getCtime()) == Integer.parseInt(startTime)) && ((lista.get(i).getCdate()).equals(date))) {
                        if (lista.get(i).getIsFree()) {
                            DBConnectionApp.updateClassData(connection,new TableCreator(Integer.parseInt(lista.get(i).getId()), lista.get(i).getCdate(), lista.get(i).getCtime(), lista.get(i).getUserID()));
                            return true;
                        }
                    }
                }
            } else {
                int freeCounter = 0;
                for (int j = 0; j < i1; j++) {
                    for (int i = 0; i < lista.size(); i++) {
                        if (((lista.get(i).getId()).equals(roomID)) && ((lista.get(i).getCtime()) == (Integer.parseInt(startTime) + j)) && ((lista.get(i).getCdate()).equals(date))) {
                            if (lista.get(i).getIsFree()) {
                                DBConnectionApp.updateClassData(connection,new TableCreator(Integer.parseInt(lista.get(i).getId()), lista.get(i).getCdate(), lista.get(i).getCtime(), lista.get(i).getUserID()));
                                freeCounter = freeCounter + 1;
                            }
                        }
                    }
                }
                if (freeCounter == i1)
                    return true;
            }
            return false;
        }
        return true;
    }
}