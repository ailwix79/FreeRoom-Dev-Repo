import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

public class DBConnectionApp{

    private static final Logger log;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(DBConnectionApp.class.getName());
    }

    // EXAMPLE MAIN FUNCTION FOR CREATING DE TABLE AND INSERTING DATA
    public static void main(String[] args) throws Exception {

        createDBSchema();
        Connection connection = makeConnection();

        ResultSet rs = readData(connection,null);



        /*//EXAMPLE CODE
        TableCreator table = new TableCreator(0001,04/10/2021,08,true);
        insertData(table, makeConnection());
        ResultSet Result = readData(makeConnection());
        */
    }

    public static ResultSet lecturaCompleta(){
        Connection connection = null;
        try {
            connection = makeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = readData(connection,null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    private static void createDBSchema() throws Exception {
        // THIS FUNCTION MUST ONLY BE RUN ONCE TO CREATE THE DATASET
        // THE FILE schema.sql / test_data.sql CONTAINS ALL THE NECESSARY DATA TO CREATE THE DATABASE

        Connection connection = makeConnection();
        log.info("Create table and Insert classroom data");
        Scanner scanner = new Scanner(DBConnectionApp.class.getClassLoader().getResourceAsStream("test_data.sql"));
        Statement statement = connection.createStatement();
        int i = 0;
        while (scanner.hasNextLine()) {
            statement.execute(scanner.nextLine());
            log.info("Query executed " + i);
            i = i+1;
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

    private static void insertData(TableCreator tableCreator, Connection connection) throws SQLException {
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

    public static ResultSet readData(Connection connection, Long classId) throws SQLException {
        // THIS FUNCTION ALLOWS TO READ ALL DATA
        // SHOULD BE MADE TO ALSO BE ABLE TO READ ROWS
        log.info("Read data");
        PreparedStatement readStatement;

        if(classId != null){
            readStatement = connection.prepareStatement("SELECT * FROM tableCreator WHERE classId = ?;");
            readStatement.setLong(1,classId);
        } else {
            readStatement = connection.prepareStatement("SELECT * FROM tableCreator;");
        }

        ResultSet resultSet = readStatement.executeQuery();

        if (!resultSet.next()) {
            log.info("There is no data in the database");
            return null;
        }

        /*
        RETURN QUERY INFO AS TABLECREATOR OBJECT
        TableCreator queryResult = new TableCreator();
        queryResult.setClassId(resultSet.getLong(1));
        queryResult.setDate(resultSet.getDate(2));
        queryResult.setTime(resultSet.getTime(3));
        queryResult.setIsFree(resultSet.getBoolean(4));
        log.info("Data read from the database: " + queryResult.toString());
        return queryResult;
        */

        log.info("Data has been read correctly");
        return resultSet;
    }

    private static void updateData(TableCreator tableCreator, Connection connection) throws SQLException {
        // THIS FUNCTION ALLOWS TO UPDATE A ROW
        log.info("Update data");
        PreparedStatement updateStatement = connection.prepareStatement("UPDATE tableCreator SET date = ?, time = ?, isFree = ? WHERE classId = ?;");

        updateStatement.setDate(1, tableCreator.getDate());
        updateStatement.setTime(2, tableCreator.getTime());
        updateStatement.setBoolean(3, tableCreator.getIsFree());
        updateStatement.setLong(4, tableCreator.getClassId());
        updateStatement.executeUpdate();
        log.info("The database has been updated");
        //readData(connection);

    }

    private static void deleteData(TableCreator tableCreator, Connection connection) throws SQLException {
        // IN CASE A DELETE IS NECESSARY, THIS FUNCTION ALLOWS TO DELETE A ROW
        log.info("Delete data");
        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM tableCreator WHERE classId = ?;");
        deleteStatement.setLong(1, tableCreator.getClassId());
        deleteStatement.executeUpdate();
        log.info("Data deleted");
        //readData(connection);
    }
}