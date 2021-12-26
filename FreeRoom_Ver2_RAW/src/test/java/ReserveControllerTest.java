import org.junit.Test;

import static org.junit.Assert.*;

public class ReserveControllerTest {

    @Test
    public void checkReservation() {
        //Comprobando reserva realizada por professor lunes a las 10
        try {
            assertEquals(Boolean.TRUE,ReserveController.checkReservation("102","10","10","MON","professor"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Comprobando que no hay reserva realizada por professor lunes a las 12
        try {
            assertEquals(Boolean.FALSE,ReserveController.checkReservation("102","12","13","MON","professor"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}