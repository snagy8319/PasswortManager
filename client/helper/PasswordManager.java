package client.helper;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

import client.model.Password;
import client.model.User;

public class PasswordManager {

    private UID uid = null;
    private List<User> users;
    private List<Password> passwords;
    private User currentUser;

    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void logoutUser(String username, String password) {
        currentUser = null;
    }

    public boolean registerUser(String username, String password) {

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        User user = new User(username, password);
        users.add(user);
        currentUser = user;
        return true;
    }

    public User getCurrentUser() {
        if (currentUser != null) {
            return currentUser;
        }

        return null;
    }

    public boolean changeUserPassword(String oldPassword, String newPassword) {
        if (currentUser != null && currentUser.getPassword().equals(oldPassword)) {
            currentUser.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public boolean addPassword(String website, String username, String password, String notes) {
        if (currentUser != null) {
            PinCodeGenerator.generierePin(12);
            Password newpassword = new Password(website, username, password, notes);
            passwords.add(newpassword);
            currentUser.addPassword(newpassword);
            return true;
        }
        return false;
    }

    public List<Password> getAllPasswords() {
        if (currentUser != null) {
            return currentUser.getPasswords();
        }

        return new ArrayList<>();
    }

    public PasswordManager() {
        uid = new UID();
        users = new ArrayList<>();
        passwords = new ArrayList<>();
        currentUser = null;

    }
}
