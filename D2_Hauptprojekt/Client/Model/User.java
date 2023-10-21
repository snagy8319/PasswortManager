package Model;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

public class User {

    private UID id;
    private String username;
    private String password;
    private List<Password> passwords;

    public boolean changePassword(String oldPassword, String newPassword) {
        return false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Password> getPasswords() {
        return passwords;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addPassword(Password password) {
        passwords.add(password);
    }

    public User(String UserName, String Password) {
        id = new UID();
        username = UserName;
        password = Password;
        passwords = new ArrayList<>();
    }
}