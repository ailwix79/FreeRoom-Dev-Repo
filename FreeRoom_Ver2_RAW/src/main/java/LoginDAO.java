

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDAO {

    public static ArrayList<Login> getLogins() throws SQLException {

        ResultSet rs = DBConnectionApp.loginFullRead();
        ArrayList<Login> loginList = new ArrayList<>();
        loginList.add(new Login(rs.getString(1),rs.getString(2),rs.getString(3)));
        while (rs.next()) {
            loginList.add(new Login(rs.getString(1),rs.getString(2),rs.getString(3)));
        }

        return loginList;
    }
}
