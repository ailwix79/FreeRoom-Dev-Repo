package controller;

import domain.Room;
import server.dao.DBConnectionApp;
import server.dao.RoomDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/** Clase de gestión de aulas */

public abstract class RoomControler {

	public static void getRoom(ArrayList<Room> lista) throws SQLException {
		RoomDAO.getRooms(lista);
	}

	public static void getRoomDayTime(ArrayList<Room> listaDayTime, String dia, int hora) throws SQLException {
		RoomDAO.getRoomsDayTime(listaDayTime,dia,hora);
	}

	public static void getSingleRoom(ArrayList<Room> listaSingle, String roomId) throws SQLException {
		RoomDAO.getRoomsDayTime(listaSingle,roomId);
	}

	public static Boolean addRoom(String roomID,int planta) {
		//Primero se comprueba que el aula no existe
		if (checkRoomExists(roomID)) {
			return false;
		}
		//Una vez comprobado que no existe, se procede a añadir los registros a la base de datos
		else {
			for (String d : Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")) {
				for (int h = 8; h < 21; ++h) {
					DBConnectionApp.insertClassData(roomID, d.toUpperCase(), h, Boolean.TRUE, planta);
				}
			}
			//Comprobacion
			return checkRoomExists(roomID);
		}
	}

	public static Boolean checkRoomExists(String roomID){
		ArrayList<Room> listaSingle = new ArrayList<>();
		try {
			RoomDAO.getRoomsDayTime(listaSingle,roomID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (listaSingle.size()==0)
			return false;
		else
			return true;
	}

}
