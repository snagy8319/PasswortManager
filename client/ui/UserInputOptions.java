package client.ui;

import java.util.Scanner;

import client.helper.PasswordGenerator;
import client.helper.PasswordManager;
import client.helper.logging.LoggingHelper;
import client.helper.ui.PrintedColor;
import client.model.Password;
import client.model.Passwords;
import client.model.User;
import client.model.Users;

public class UserInputOptions {

    public static void welcomeMessage() {
        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|          PASSWORD MANAGER       |");
        System.out.println("===================================" + PrintedColor.resetColor);
        System.out.println("| " + PrintedColor.underlinedMessage + "Options:" + PrintedColor.resetColor
                + "                        |");
        System.out.println("|        1. Login                 |");
        System.out.println("|        2. Register              |");
        System.out.println("|        3. Logout                |");
        System.out.println("|        4. Add password          |");
        System.out.println("|        5. Get all passwords     |");
        System.out.println("|        6. Update password       |");
        System.out.println("|        7. Delete password       |");
        System.out.println("|        8. Exit                  |");
        System.out.println("===================================");
    }

    public static boolean registration(Scanner scanner, Users users) {

        boolean isregistered;

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
            isregistered = true;
            LoggingHelper.logToFile("User has been registered " + addedUser.getUsername());
        } else {
            LoggingHelper.logToFile("User registration has been failed " + addedUser.getUsername());
            isregistered = false;
        }
        return isregistered;
    }

    public static boolean login(Scanner scanner, PasswordManager passwordManager) {

        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|             LOGIN              |");
        System.out.println("===================================" + PrintedColor.resetColor);

        System.out.println("Please enter your username:");
        String username = scanner.next();
        System.out.println("Please enter your password:");
        String password = scanner.next();

        User user = new User(username, password);
        boolean isLoginSuccessful = passwordManager.loginUser(user);
        if (isLoginSuccessful) {
            LoggingHelper.logToFile(PrintedColor.successMessage + "User has been logged in with Username "
                    + user.getUsername() + PrintedColor.resetColor);
        } else {
            LoggingHelper.logToFile(PrintedColor.errorMessage + "User login has been failed with Username "
                    + user.getUsername() + PrintedColor.resetColor);
        }
        return isLoginSuccessful;
    }

    public static boolean logout(PasswordManager passwordManager) {

        boolean isLogout = false;
        User currentUser = passwordManager.getCurrentUser();
        if (currentUser != null) {
            isLogout = passwordManager.logoutUser(currentUser.getUsername(), currentUser.getPassword());
        }

        if (isLogout) {
            LoggingHelper.logToFile("User has been logged-out ");
        } else {
            LoggingHelper.logToFile("User has failed to logged-out ");
        }
        return isLogout;

    }

    public static void addPassword(PasswordManager passwordManager, PasswordGenerator passwordGenerator,
            Passwords passwords, Scanner scanner) {

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
            System.out.println(PrintedColor.errorMessage + "Password addition failed. Please try again."
                    + PrintedColor.resetColor);
        }

    }

    public static void getAllPasswords(PasswordManager passwordManager, Passwords passwords) {

        if (passwordManager.getCurrentUser() == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|        ALL PASSWORDS           |");
        System.out.println("===================================" + PrintedColor.resetColor);

        for (Password password : passwords.getAllPasswords()) {
            System.out.println("Website: " + password.getWebsite());
            System.out.println("Username: " + password.getUsername());
            System.out.println("Password: " + password.getPassword());
            System.out.println("Notes: " + password.getNotes());
            System.out.println("ID: " + password.getId());
            System.out.println("===================================");
        }

    }

    public static void updatePassword(PasswordManager passwordManager, Passwords passwords,
            PasswordGenerator passwordGenerator, Scanner scanner) {

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
            System.out.println("Password has been updated with the new password: " + PrintedColor.boldMessage
                    + newPassword + PrintedColor.resetColor);
        }
    }

    public static void deletePassord(PasswordManager passwordManager, Passwords passwords, Scanner scanner) {

        if (passwordManager.getCurrentUser() == null) {
            System.out.println("Please login first");
            return;
        }

        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|        DELETE PASSWORD         |");
        System.out.println("===================================" + PrintedColor.resetColor);

        System.out.println("Please enter the ID of the password to delete:");
        int id = scanner.nextInt();
        Password password2Delete = passwords.getPasswordById(id);

        if (password2Delete == null) {
            System.out.println(PrintedColor.errorMessage + "Password not found" + PrintedColor.resetColor);
            return;
        }

        boolean deleted = passwords.deletePassword(id);

        if (deleted) {
            System.out.println("Password with ID " + id + " has been deleted successfully");
        } else {
            System.out.println(PrintedColor.errorMessage + "Failed to delete the password" + PrintedColor.resetColor);
        }

    }

}
