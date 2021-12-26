
import java.sql.SQLException;
import java.util.ArrayList;

// LOGIN CHECKING FUNCTION. COMMUNICATES WITH DAO AND COMPARES USERNAME AND PASSWORD
public abstract class LoginController {

    public static boolean checkLogin(String userName, String password, String role) throws SQLException {
        ArrayList<Login> loginList = LoginDAO.getLogins();
        for (int i = 0; i < loginList.size(); i++) {
            if ((loginList.get(i).getUser()).equals(userName) && (loginList.get(i).getPasswd()).equals(password) && (loginList.get(i).getRole()).equals(role)) {
                return true;
            }
            else {
                continue;
            }

        }
        return false;
    }
}
