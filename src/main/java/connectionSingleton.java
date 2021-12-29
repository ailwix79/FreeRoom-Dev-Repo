import java.sql.Connection;

class ConectionSingleton {

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