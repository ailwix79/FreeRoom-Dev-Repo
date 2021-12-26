import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

// TIME HANDLER FUNCTION.
public abstract class TimeManager {
    public static void main(String[] args) {
        //SOLO PARA PRUEBAS
        System.out.println(getCurrentWeekDay());
    }

    public static LocalDateTime getCurrentDate(){
        return LocalDateTime.now();
    }

    public static String getCurrentWeekDay(){
        LocalDateTime localDate = LocalDateTime.now();
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
        String dayName = dayOfWeek.name();
        return dayName.substring(0,3);
    }

    public static int getCurrentHour() {
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        return validateHour(hour);
    }

    private static int validateHour(int hour) {
        //Esta es una solución temporal para cuando se inicia el programa fuera del horario establecido (08:00 a 20:00)
        if (hour>19)
            return 19;
        if (hour<8)
            return 8;
        else
            return hour;
    }
}
