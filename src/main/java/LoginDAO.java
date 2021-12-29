

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDAO {

    public static ResultSet getLogins(String userName, String password, String role) throws SQLException {

        ResultSet rs = DBConnectionApp.loginFullRead(userName, password, role);
        return rs;
    }
}
