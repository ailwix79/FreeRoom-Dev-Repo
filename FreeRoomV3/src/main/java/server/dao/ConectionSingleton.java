package server.dao;

import server.dao.DBConnectionApp;

import java.sql.Connection;

/** Clase PATRÓN SINGLETON: solo permite una conexión simultánea a la base de datos */

public class ConectionSingleton {

    private static Connection connection;

    public static Connection connectionSingleton() {

        if (connection == null)
        {
            try {
                connection = DBConnectionApp.makeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}