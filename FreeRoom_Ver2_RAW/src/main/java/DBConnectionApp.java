import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

public class DBConnectionApp {

    private static final Logger log;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(DBConnectionApp.class.getName());
    }

    public static void createDBSchema(String file) throws Exception {
        // THIS FUNCTION MUST ONLY BE RUN ONCE TO CREATE THE DATASET
        // THE FILE schema.sql / test_data.sql CONTAINS ALL THE NECESSARY DATA TO CREATE THE DATABASE

        Connection connection = makeConnection();
        log.info("Create table and Insert classroom data");
        Scanner scanner = new Scanner(DBConnectionApp.class.getClassLoader().getResourceAsStream(file));
        Statement statement = connection.createStatement();
        int i = 0;
        while (scanner.hasNextLine()) {
            statement.execute(scanner.nextLine());
            log.info("Query executed " + i);
            i = i + 1;
        }
        log.info("The table has been created");
        log.info("Closing database connection");
        connection.close();
    }

    private static Properties loadProperties() throws Exception {
        // THIS FUNCTION LOADS THE DATABASE PROPERTIES
        log.info("Loading application properties");
        Properties properties = new Properties();
        properties.load(DBConnectionApp.class.getClassLoader().getResourceAsStream("application.properties"));
        return properties;
    }

    public static Connection makeConnection() throws Exception {
        // THIS FUNCTION MAKES THE CONNECTION WITH THE DATABASE
        Properties properties = loadProperties();
        log.info("Connecting to the database");
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        log.info("Database connection test: " + connection.getCatalog());
        log.info("Database connected");
        return connection;
    }

    private static void insertClassData(TableCreator tableCreator, Connection connection) throws SQLException {
        // THIS FUNCTION ALLOWS TO INSERT NEW ROWS
        log.info("Insert data");
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO todo (classId, date, time, isFree) VALUES (?, ?, ?, ?);");

        insertStatement.setLong(1, tableCreator.getClassId());
        //insertStatement.setDate(2, tableCreator.getDate());
        //insertStatement.setTime(3, tableCreator.getTime());
        insertStatement.setBoolean(2, tableCreator.getIsFree());

        insertStatement.executeUpdate();
        log.info("The data has been inserted");
    }

    public static ResultSet readClassData(Connection connection, Object classId) throws SQLException {
        // THIS FUNCTION ALLOWS TO READ ALL DATA
        // SHOULD BE MADE TO ALSO BE ABLE TO READ ROWS
        log.info("Read data");
        PreparedStatement readStatement;

        if (classId != null) {
            readStatement = connection.prepareStatement("SELECT * FROM tableCreator2 WHERE classId = ?;");
            readStatement.setInt(1, (Integer) classId);
        } else {
            readStatement = connection.prepareStatement("SELECT * FROM tableCreator2;");
        }

        ResultSet resultSet = readStatement.executeQuery();

        if (!resultSet.next()) {
            log.info("There is no data in the database");
            return null;
        }

        log.info("Data has been read correctly");
        return resultSet;
    }

    public static void updateClassData(TableCreator tableCreator) throws Exception {
        // THIS FUNCTION ALLOWS TO UPDATE A ROW
        // CONNECTION MADE INSIDE DUE TO PRACTICALITY REASONS.
        Connection connection = makeConnection();
        log.info("Update data");
        PreparedStatement updateStatement = connection.prepareStatement("UPDATE tableCreator2 SET isFree = ?, userID = ? WHERE classId = ? AND dia = ? AND hora = ?;");

        updateStatement.setBoolean(1, false);
        updateStatement.setString(2, tableCreator.getUserID());
        updateStatement.setInt(3, tableCreator.getClassId());
        updateStatement.setString(4, tableCreator.getDate());
        updateStatement.setInt(5, tableCreator.getTime());
        updateStatement.executeUpdate();
        log.info("The database has been updated");
        //readData(connection);

    }

    private static void deleteClassData(TableCreator tableCreator, Connection connection) throws SQLException {
        // IN CASE A DELETE IS NECESSARY, THIS FUNCTION ALLOWS TO DELETE A ROW
        log.info("Delete data");
        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM tableCreator2 WHERE classId = ?;");
        deleteStatement.setLong(1, tableCreator.getClassId());
        deleteStatement.executeUpdate();
        log.info("Data deleted");
        //readData(connection);
    }

    public static ResultSet classFullRead() {
        Connection connection = null;
        try {
            connection = makeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = readClassData(connection, null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    public static ResultSet readDayTime(String dia, int hora) throws SQLException {
        // THIS FUNCTION ALLOWS TO READ ALL DATA
        // SHOULD BE MADE TO ALSO BE ABLE TO READ ROWS
        log.info("Read data");
        PreparedStatement readStatement;
        Connection connection = null;
        try {
            connection = makeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dia != null) {
            readStatement = connection.prepareStatement("SELECT * FROM tableCreator2 WHERE dia = ? AND hora = ?;");
            readStatement.setString(1, dia);
            readStatement.setInt(2,hora);
        } else {
            readStatement = connection.prepareStatement("SELECT * FROM tableCreator2;");
        }

        ResultSet resultSet = readStatement.executeQuery();

        if (!resultSet.next()) {
            log.info("There is no data in the database");
            return null;
        }

        log.info("Data has been read correctly");
        return resultSet;
    }

    public static ResultSet readSingleRoom(String roomId) throws SQLException {
        // THIS FUNCTION ALLOWS TO READ ALL DATA
        // SHOULD BE MADE TO ALSO BE ABLE TO READ ROWS
        log.info("Read data");
        PreparedStatement readStatement;
        Connection connection = null;
        try {
            connection = makeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (roomId != null) {
            readStatement = connection.prepareStatement("SELECT * FROM tableCreator2 WHERE classId = ?;");
            readStatement.setString(1, roomId);
        } else {
            readStatement = connection.prepareStatement("SELECT * FROM tableCreator2;");
        }

        ResultSet resultSet = readStatement.executeQuery();

        if (!resultSet.next()) {
            log.info("There is no data in the database");
            return null;
        }

        log.info("Data has been read correctly");
        return resultSet;
    }

    public static ResultSet readLoginData(Connection connection, String userName) throws SQLException {
        // THIS FUNCTION ALLOWS TO READ ALL DATA
        // SHOULD BE MADE TO ALSO BE ABLE TO READ ROWS
        log.info("Read data");
        PreparedStatement readStatement;

        if (userName != null) {
            readStatement = connection.prepareStatement("SELECT * FROM tableLogin WHERE UserId = ?;");
            readStatement.setString(1, userName);
        } else {
            readStatement = connection.prepareStatement("SELECT * FROM tableLogin;");
        }

        ResultSet resultSet = readStatement.executeQuery();

        if (!resultSet.next()) {
            log.info("There is no data in the database");
            return null;
        }

        log.info("Data has been read correctly");
        return resultSet;
    }

    public static ResultSet loginFullRead() {
        Connection connection = null;
        try {
            connection = makeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = readLoginData(connection, null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    public static void LoadCSVData() {
        URL csvFilePath = Student_temp.class.getResource("datos.csv");

        int batchSize = 20;
        Connection connection = null;
        try {
            connection = makeConnection();
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            String sql = "INSERT INTO tableCreator2 (classId,dia,hora,isFree,planta) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath.getPath()));
            String lineText = null;

            int count = 0;
            int i = 0;
            lineReader.readLine(); // skip header line

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(";");
                String classId = data[0];
                String dia = data[1];
                String hora = data[2];
                String isFree = data[3];
                String planta = data[4];

                statement.setInt(1,Integer.parseInt(classId));
                statement.setString(2,dia);
                statement.setInt(3,Integer.parseInt(hora));
                statement.setString(4,isFree);
                statement.setString(5,planta);
                statement.addBatch();

                if (count % batchSize == 0) {
                    statement.executeBatch();
                }

                log.info("Query executed from CSV " + i);
                i = i + 1;

            }

            lineReader.close();

            // execute the remaining queries
            statement.executeBatch();

            connection.commit();
            connection.close();

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}