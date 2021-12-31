package server.dao;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ConectionSingletonTest {

    /** Se comprueba el correcto funcionamiento del PATRON SINGLETON */

    @Test
    public void connectionSingleton() {
        Connection connection = ConectionSingleton.connectionSingleton();
        Connection connection2 = ConectionSingleton.connectionSingleton();
        assertEquals(connection.hashCode(),connection2.hashCode());
    }
}