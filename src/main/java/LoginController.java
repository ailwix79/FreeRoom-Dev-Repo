
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// LOGIN CHECKING FUNCTION. COMMUNICATES WITH DAO AND COMPARES USERNAME AND PASSWORD
public abstract class LoginController {

    public static boolean checkLogin(String userName, String password, String role) throws SQLException {
        ResultSet loginList = LoginDAO.getLogins(userName, password, role);
        if (loginList == null)
            return false;
        else
            return true;

    }
}
