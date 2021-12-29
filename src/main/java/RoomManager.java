import java.util.ArrayList;

public class RoomManager {

    public static void main(String[] args) {

    }

    public static ArrayList<Room> getTodayRooms(ArrayList<Room> lista){
        var result = new ArrayList<Room>();
        for (Room room: lista) {

            if (room.getCdate().equals(TimeManager.getCurrentWeekDay())) {
                result.add(room);
            }
        }

        return result;
    }

    public static ArrayList<Room> dayFilter(String dia,ArrayList<Room> lista){
        //ArrayList<Room> filteredRoom = lista.stream().filter(room -> room.getCDate.contains("test")).collect(Collectors.toList());
        var result = new ArrayList<Room>();
        for (Room room: lista) {

            if (room.getCdate().equals(dia)) {
                result.add(room);
            }
        }

        return result;
    }
}
