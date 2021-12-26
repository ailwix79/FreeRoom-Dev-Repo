import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class LoginControllerTest {

    @Test
    public void checkLogin1() {
        //Se comprueba el login por defecto del perfil profesor
        try {
            assertEquals(Boolean.TRUE,LoginController.checkLogin("professor","professor","Professor"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkLogin2() {
        //Se comprueba el login por defecto del perfil administrativo
        try {
            assertEquals(Boolean.TRUE,LoginController.checkLogin("admin","admin","Administrative"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkLogin3() {
        //Se comprueba el caso de usuario/contraseña incorrectos
        try {
            assertEquals(Boolean.FALSE,LoginController.checkLogin("hola","1234","Professor"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}