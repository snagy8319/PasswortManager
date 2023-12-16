package com.pass.ui.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.pass.database.DatabaseHandler;
import com.pass.helper.PasswordManager;
import com.pass.model.Password;
import com.pass.model.Users;

public class UserInputHandler {

    private static DatabaseHandler dbHandler = new DatabaseHandler();



    public static boolean registerUser(String newUsername, String newPassword, Users users) {
        try {
            // Use the addUser method from DatabaseHandler to add the user
            boolean isUserAdded = dbHandler.addUser(newUsername, newPassword.toString());
            if (isUserAdded) {
                System.out.println("User registered successfully!");
                return true;
            } else {
                System.out.println("User registration failed.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while registering the user: " + e.getMessage());
            return false;
        }
    }

    public static boolean login(String username, String password, PasswordManager passwordManager) {
        try {
            // Use the validateUser method from DatabaseHandler to validate the user
            Integer userId = dbHandler.validateUser(username, password);
            if (userId != null) {
                System.out.println("User logged in successfully!");
                return true;
            } else {
                System.out.println("Invalid username or password.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while logging in the user: " + e.getMessage());
            return false;
        }
    }

    public static boolean logout(String username, String password,
            PasswordManager passwordManager) {

        return passwordManager.logoutUser(username, password);
    }



    public static boolean addEasyPassword(Password passwordInput) throws SQLException {
        boolean addPasswordSuccessful = dbHandler.addEasyPassword(passwordInput.getWebsite(),
                passwordInput.getUsername(), passwordInput.getPassword(), passwordInput.getNotes(),
                passwordInput.getId());
        if (addPasswordSuccessful) {
            System.out.println("Password added successfully!");
            return true;
        } else {
            System.out.println("An error occurred while adding the password.");
            return false;
        }
    }

    public static List<Password> getEasyPasswords(String userNameCurrent) {
        List<Password> passwords = new ArrayList<>();

        try {
            ResultSet allPasswords = dbHandler.getAllEasyPasswords(userNameCurrent);
            while (allPasswords.next()) {
                String website = allPasswords.getString("website");
                String username = allPasswords.getString("username");
                String password = allPasswords.getString("password");
                String notes = allPasswords.getString("notes");
                Password passwordObj = new Password(website, username, password, notes);
                passwords.add(passwordObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passwords;
    }

    public static boolean updateEasyPassword(String username, String newPassword, int passID) {
        try {
            // Use the updateEasyPassword method from DatabaseHandler to update the password
            dbHandler.updateEasyPassword(username, newPassword, passID);
            System.out.println("Password updated successfully!");
            return true;
        } catch (SQLException e) {
            System.err.println("An error occurred while updating the password: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteEasyPassword(int passID) throws SQLException {
        // Use the deleteEasyPassword method from DatabaseHandler to delete the password
        dbHandler.deleteEasyPassword(passID);
        System.out.println("Password deleted successfully!");
        return true;
    }

    // Similar changes for other methods
}
