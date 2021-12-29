
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

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

}
