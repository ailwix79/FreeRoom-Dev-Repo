package server.dao;

import domain.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** Clase auxiliar de conexión con base de datos para credenciales */

public class LoginDAO {

    public static ResultSet getLogins(String userName, String password, String role) throws SQLException {

        ResultSet rs = DBConnectionApp.loginFullRead(userName, password, role);
        return rs;
    }

    public static void getUsers(ArrayList<Login> lista) throws SQLException {
        ResultSet rs = DBConnectionApp.readUsers();
        lista.add(new Login(rs.getString(1),rs.getString(2),rs.getString(3)));
        while (rs.next()) {
            lista.add(new Login(rs.getString(1),rs.getString(2),rs.getString(3)));
        }

    }

}
