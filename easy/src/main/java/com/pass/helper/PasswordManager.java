package com.pass.helper;

import java.rmi.server.UID;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.pass.database.DatabaseHandler;
import com.pass.model.Password;
import com.pass.model.Passwords;
import com.pass.model.User;
import com.pass.model.Users;


public class PasswordManager {

    private UID uid = null;
    private Users users;
    private Passwords passwords;
    private User currentUser;
    private DatabaseHandler dbHandler; // Declare a DatabaseHandler instance variable



    public PasswordManager(Users userslist, Passwords passwordslist) {
        this.uid = new UID();
        this.users = userslist;
        this.passwords = passwordslist;
        this.currentUser = null;
        this.dbHandler = new DatabaseHandler();
    }

    public boolean loginUser(User user) throws SQLException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        Integer userId = dbHandler.validateUser(user.getUsername(), user.getPassword());

        if (userId == null) {
            System.out.println("User has not registered. Please register a User first");
            return false;
        } else if (userId == -1) {
            System.out.println("Invalid password.");
            return false;
        } else {
            System.out.println("Login successful!");
            return true;
        }
    }

    public boolean logoutUser(String username, String password) {
        currentUser = getCurrentUser();
        if (currentUser != null) {
            currentUser = null;
            return true;

        }
        return false;
    }

    public boolean registerUser(String username, String password) throws SQLException {
        // Check if the user already exists in the database
        if (dbHandler.validateUser(username, password) != null) {
            return false;
        }

        // Add the user to the database
        boolean addUserSuccessful = dbHandler.addUser(username, password);



        if (addUserSuccessful) {
            User user = new User(username, password);
            currentUser = user;
            System.out.println("New User has been registered");
            return true;
        } else {
            return false;
        }
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
            Password newpassword = new Password(website, username, password, notes);
            passwords.addPassword(newpassword);
            return true;
        }
        return false;
    }

    public Passwords getEasyPasswords() {
        Passwords passwords = new Passwords();

        if (currentUser != null) {
            return passwords;
        }

        try {
            ResultSet allPasswords = dbHandler.getAllEasyPasswords(currentUser.getUsername());
            while (allPasswords.next()) {
                String website = allPasswords.getString("website");
                String username = allPasswords.getString("username");
                String password = allPasswords.getString("password");
                String notes = allPasswords.getString("notes");
                Password passwordObj = new Password(website, username, password, notes);
                passwords.addPassword(passwordObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passwords;
    }

}
