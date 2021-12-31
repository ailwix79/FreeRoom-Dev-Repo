package server.dao;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DBConnectionAppTest {

    @Test
    public void makeConnection() {
        try {
            assertEquals(DBConnectionApp.makeConnection().getClass(),Connection.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void readUsers() {

    }

    @Test
    public void addUser() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void insertClassData() {
    }

    @Test
    public void readClassData() {
    }

    @Test
    public void updateClassData() {
    }

    @Test
    public void deleteClassData() {
    }

    @Test
    public void classFullRead() {
    }

    @Test
    public void readDayTime() {
    }

    @Test
    public void readSingleRoom() {
    }

    @Test
    public void readLoginData() {
    }

    @Test
    public void loginFullRead() {
    }
}