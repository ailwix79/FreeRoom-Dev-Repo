
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomControler {

	public void getRoom(ArrayList<Room> lista) throws SQLException {
		RoomDAO.getRooms(lista);
	}	
}
