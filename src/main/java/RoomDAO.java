

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class RoomDAO {
	
	
	
	public static void getRooms(ArrayList<Room> lista) throws SQLException {
		//Connection con=ConnectionDAO.getInstance().getConnection();

			ResultSet rs = DBConnectionApp.classFullRead();
			lista.add(new Room(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBoolean(4),rs.getInt(5)));
            while (rs.next()) {
				lista.add(new Room(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBoolean(4),rs.getInt(5)));
            }

	}

	public static void getRoomsDayTime(ArrayList<Room> lista, String dia, int hora) throws SQLException {

		ResultSet rs = DBConnectionApp.readDayTime(dia,hora);
		lista.add(new Room(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBoolean(4),rs.getInt(5)));
		while (rs.next()) {
			lista.add(new Room(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBoolean(4),rs.getInt(5)));
		}

	}

	public static void getRoomsDayTime(ArrayList<Room> listaSingle,String roomId) throws SQLException {
		ResultSet rs = DBConnectionApp.readSingleRoom(roomId);
		listaSingle.add(new Room(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBoolean(4),rs.getInt(5)));
		while (rs.next()) {
			listaSingle.add(new Room(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBoolean(4),rs.getInt(5)));
		}


	}
}
