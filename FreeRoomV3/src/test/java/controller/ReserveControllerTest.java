package controller;

import controller.ReserveController;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReserveControllerTest {

    @Test
    public void checkReservation() {
        //Comprobando reserva realizada por professor lunes a las 10
        try {
            assertEquals(Boolean.TRUE, ReserveController.checkReservation("102","10","10","MON","professor"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}