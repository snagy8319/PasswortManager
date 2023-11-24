package client.model;

import java.rmi.server.UID;

public class User {

    private int id;
    public String username;
    private String password;

    public int getID() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String newPassword) {
        password = newPassword;
        return password;
    }

    public User(String UserName, String Password) {
        id = new UID().hashCode();
        username = UserName;
        password = Password;
    }
}