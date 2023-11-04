package client.helper;

import java.util.Scanner;

import client.helper.logging.LoggingHelper;
import client.helper.ui.PrintedColor;
import client.model.Password;
import client.model.Passwords;
import client.model.User;
import client.model.Users;

public class UserInputOptions {

    public static void login(Scanner scanner, PasswordManager passwordManager) {

        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|             LOGIN              |");
        System.out.println("===================================" + PrintedColor.resetColor);

        System.out.println("Please enter your username:");
        String username = scanner.next();
        System.out.println("Please enter your password:");
        String password = scanner.next();

        User user = new User(username, password);
        boolean loginSuccessful = passwordManager.loginUser(user);
        LoggingHelper.logToFile(PrintedColor.successMessage + "User has been logged in: Username" + user.getUsername() + PrintedColor.resetColor);

        if (loginSuccessful) {
            System.out.println(PrintedColor.successMessage + "Login successful!" + PrintedColor.resetColor);
        } else {
            System.out.println(
                    PrintedColor.errorMessage + "Login failed. User does not exist." + PrintedColor.resetColor);
        }

        //return new User(username, password);

    }

    public static void registration(Scanner scanner, Users users) {

        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|           REGISTER             |");
        System.out.println("===================================" + PrintedColor.resetColor);

        System.out.println("Please enter your username:");
        String newUsername = scanner.next();
        System.out.println("Please enter your password:");
        String newPassword = scanner.next();

        User newUser = new User(newUsername, newPassword);
        User addedUser = users.addUser(newUser);

        if (addedUser != null) {
            System.out.println(PrintedColor.successMessage + "Registration successful!" + PrintedColor.resetColor);
            LoggingHelper.logToFile("User has been registered " + addedUser.getUsername());
        } else {
            System.out.println(PrintedColor.errorMessage + "Registration failed. Please try again." + PrintedColor.resetColor);
        }

        //return new User(newUsername, newPassword);
    }

    public static void logout(PasswordManager passwordManager) {

        boolean logout = false;
        User currentUser = passwordManager.getCurrentUser();
        if (currentUser != null) {
            logout = passwordManager.logoutUser(currentUser.getUsername(), currentUser.getPassword());
        }

        if (logout) {
            System.out.println(PrintedColor.successMessage + "Logout successful!" + PrintedColor.resetColor);
             LoggingHelper.logToFile("User has been logged-out ");
        } else {
            System.out.println(PrintedColor.errorMessage + "User is not logged in" + PrintedColor.resetColor);
        }

    }

    public static void addPassword(PasswordManager passwordManager, PasswordGenerator passwordGenerator, Passwords passwords, Scanner scanner) {

        if (passwordManager.getCurrentUser() == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|         ADD PASSWORD           |");
        System.out.println("===================================" + PrintedColor.resetColor);
        System.out.println("Please enter the website:");
        String newWebsite = scanner.next();
        System.out.println("Please enter the username:");
        String newUsername = scanner.next();
        String newPassword = passwordGenerator.generatePassword(12);
        System.out.println("Generated password: " + PrintedColor.boldMessage + newPassword + PrintedColor.resetColor);
        System.out.println("Please enter any notes:");
        String newNotes = scanner.next();
        Password newPassword2 = new Password(newWebsite, newUsername, newPassword, newNotes);
        Password addedPassword = passwords.addPassword(newPassword2);
        if (addedPassword != null) {
            System.out.println(PrintedColor.successMessage + "Password added successfully!" + PrintedColor.resetColor);
        } else {
            System.out.println(PrintedColor.errorMessage + "Password addition failed. Please try again." + PrintedColor.resetColor);
        }

    }

    public static void getAllPasswords(PasswordManager passwordManager, Passwords passwords) {
        
        if (passwordManager.getCurrentUser() == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.println(PrintedColor.warningMessage+ "===================================");
        System.out.println("|        ALL PASSWORDS           |");
        System.out.println("===================================" + PrintedColor.resetColor);

        for (Password password : passwords.getAllPasswords()) {
            System.out.println("Website: " + password.getWebsite());
            System.out.println("Username: " + password.getUsername());
            System.out.println("Password: " + password.getPassword());
            System.out.println("Notes: " + password.getNotes());
            System.out.println("===================================");
        }

        
    }

    public static void updatePassword(PasswordManager passwordManager, Passwords passwords, PasswordGenerator passwordGenerator, Scanner scanner) {

        if (passwordManager.getCurrentUser() == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|        UPDATE PASSWORD         |");
        System.out.println("===================================" + PrintedColor.resetColor);

        System.out.println("Please enter the ID of the password to update:");
        int id = scanner.nextInt();
        Password passwordToUpdate = passwords.getPasswordById(id);

        if (passwordToUpdate == null) {
            System.out.println(PrintedColor.errorMessage + "Password not found" + PrintedColor.resetColor);
            return;
        }

        System.out.println("Please enter the new website:");
        String newWebsite = scanner.next();

        System.out.println("Please enter the new username:");
        String newUsername = scanner.next();

        String newPassword = passwordGenerator.generatePassword(12);
        System.out.println("Generated password: " + PrintedColor.boldMessage + newPassword + PrintedColor.resetColor);

        System.out.println("Please enter any new notes:");
        String newNotes = scanner.next();

        Password updatedPassword = new Password(newWebsite, newUsername, newPassword, newNotes);
        Password updateSuccessful = passwords.updatePassword(id, updatedPassword);

        if (updateSuccessful != null) {
            System.out.println("Password has been updated with the new password: " + PrintedColor.boldMessage + newPassword + PrintedColor.resetColor);
        }

       
    }


    
}

