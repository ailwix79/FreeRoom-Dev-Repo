

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDAO {
	
	
	
	public static void getRooms(ArrayList<Room> lista) throws SQLException {
		//Connection con=ConnectionDAO.getInstance().getConnection();

			ResultSet rs = DBConnectionApp.lecturaCompleta();
			lista.add(new Room(rs.getString(1),rs.getString(2)));
            while (rs.next()) {
            	lista.add(new Room(rs.getString(1),rs.getString(2)));
            }

	}
	
	public static void main(String[] args) throws SQLException {
		
		
		ArrayList<Room> lista=new ArrayList<Room>();
		RoomDAO.getRooms(lista);
		
		
		 for (Room room : lista) {
			System.out.println("He leído el id: "+ room.getId()+" isFree: "+ room.getIsFree());
		}
		
	
	}

}
