package domain;

import java.io.Serializable;

/** Clase de dominio que representa a un Usuario */

public class Login implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String userName;
    private String passwd;
    private String role;

    // LOGIN HANDLER FUNCTION.

    public Login(String userName, String passwd, String role) {
        this.setUser(userName);
        this.setPasswd(passwd);
        this.setRole(role);
    }

    public String getUser() { return userName; }
    public String getPasswd() {
        return passwd;
    }
    public String getRole() { return role; }

    public void setUser(String userName) { this.userName = userName; }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "domain.Login{" +
                "userName='" + userName + '\'' +
                ", passwd='" + passwd + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}