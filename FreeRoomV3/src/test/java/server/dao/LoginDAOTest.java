package server.dao;

import domain.Login;
import org.junit.Test;
import server.dao.LoginDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LoginDAOTest {

    @Test
    public void getUsers1() {
        //Se comprueba que la función devuelve un Array con objetos Login
        try {
            ArrayList<Login> lista = new ArrayList<>();
            LoginDAO.getUsers(lista);

            assertEquals(Login.class, lista.get(1).getClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUsers2() {
        //Se comprueba que los objetos que devuelve tienen contenido
        try {
            ArrayList<Login> lista = new ArrayList<>();
            LoginDAO.getUsers(lista);

            assertEquals(String.class, lista.get(1).getUser().getClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}