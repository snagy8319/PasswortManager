package com.pass.ui;

import java.util.List;
import java.util.Scanner;

import com.pass.helper.PasswordGenerator;
import com.pass.helper.PasswordManager;
import com.pass.helper.logging.LoggingHelper;
import com.pass.helper.ui.PrintedColor;
import com.pass.model.Password;
import com.pass.model.Passwords;
import com.pass.model.User;
import com.pass.model.Users;
import com.pass.ui.handler.UserInputHandler;

public class UserInputOptions {
    private static Scanner scanner;

    public UserInputOptions(Scanner scanner, UserInputHandler userInputHandler) {}

    /**
     * Gibt eine Begrüßungsnachricht und eine Liste von Optionen auf der Konsole aus.
     * 
     * Die Optionen sind: 1. Anmelden 2. Registrieren 3. Abmelden 4. Passwort hinzufügen 5. Alle
     * Passwörter abrufen 6. Passwort aktualisieren 7. Passwort löschen 8. Beenden
     * 
     * Jede Option ist nummeriert und wird in einer neuen Zeile gedruckt. Die Nachricht und die
     * Optionen sind von einem Kasten aus '=' Zeichen umgeben.
     */
    public static void welcomeMessage() {
        String[] options = {"Login", "Register", "Logout", "Add password", "Get all passwords",
                "Update password", "Delete password", "Exit"};

        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|          PASSWORD MANAGER       |");
        System.out.println("===================================" + PrintedColor.resetColor);
        System.out.println("| " + PrintedColor.underlinedMessage + "Options:"
                + PrintedColor.resetColor + "                        |");

        for (int i = 0; i < options.length; i++) {
            System.out.printf("|        %d. %s%n", i + 1, options[i]);
        }

        System.out.println("===================================");
    }

    /**
     * Registriert einen neuen Benutzer, indem Eingaben vom Benutzer entgegengenommen und der
     * Benutzer zur Users-Sammlung hinzugefügt wird.
     * 
     * @param scanner Das Scanner-Objekt, das zum Lesen der Benutzereingabe verwendet wird.
     * @param users Das Users-Objekt, das die Sammlung von Benutzern darstellt.
     * @return true, wenn der Benutzer erfolgreich registriert ist, andernfalls false.
     */
    public static void registration(Scanner scanner, Users users) {

        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|           REGISTER             |");
        System.out.println("===================================" + PrintedColor.resetColor);

        System.out.println("Please enter your username:");
        String newUsername = scanner.next().trim();
        if (newUsername.isEmpty()) {
            System.out.println("Username cannot be empty");
        }

        System.out.println("Please enter your password:");
        char[] newPassword = System.console().readPassword();
        if (newPassword.length == 0) {
            System.out.println("Password cannot be empty");
        }

        User addedUser = UserInputHandler.registerUser(newUsername, newPassword, users);

        if (addedUser != null) {
            LoggingHelper.logToFile("User has been registered " + addedUser.getUsername());
        } else {
            LoggingHelper.logToFile("User registration has failed " + newUsername);
        }

    }

    /**
     * Führt den Anmeldevorgang für einen Benutzer durch.
     * 
     * @param scanner Das Scanner-Objekt, das für die Benutzereingabe verwendet wird.
     * @param passwordManager Das PasswordManager-Objekt, das für die Benutzerauthentifizierung
     *        verwendet wird.
     * @return true, wenn die Anmeldung erfolgreich ist, andernfalls false.
     */
    public static void login(PasswordManager passwordManager) {

        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|             LOGIN              |");
        System.out.println("===================================" + PrintedColor.resetColor);

        System.out.println("Please enter your username:");
        String username = scanner.next();
        System.out.println("Please enter your password:");
        String password = scanner.next();

        boolean isLoginSuccessful = UserInputHandler.login(username, password, passwordManager);


        if (isLoginSuccessful) {
            LoggingHelper.logToFile(
                    PrintedColor.successMessage + "User has been logged in with Username "
                            + username + PrintedColor.resetColor);
        } else {
            LoggingHelper.logToFile(
                    PrintedColor.errorMessage + "User login has been failed with Username "
                            + username + PrintedColor.resetColor);
        }


    }

    /**
     * Meldet den aktuellen Benutzer vom Passwort-Manager ab.
     * 
     * @param passwordManager die Instanz des Passwort-Managers
     * @return true, wenn der Benutzer erfolgreich abgemeldet ist, false sonst (Fehler beim Abmelden
     *         oder kein Benutzer ist derzeit angemeldet)
     */
    public static void logout(PasswordManager passwordManager) {
        User currentUser = passwordManager.getCurrentUser();
        if (currentUser == null) {
            LoggingHelper.logToFile("No user is currently logged in");
        }

        String username = currentUser.getUsername();
        String password = currentUser.getPassword();

        boolean isLogout = UserInputHandler.logout(username, password, passwordManager);


        if (isLogout) {
            LoggingHelper.logToFile("User " + currentUser.getUsername() + " has been logged out");
        } else {
            LoggingHelper.logToFile("User " + currentUser.getUsername() + " failed to log out");
        }

    }


    public static void addPassword(PasswordManager passwordManager,
            PasswordGenerator passwordGenerator, Passwords passwords, Scanner userInputScanner) {

        if (passwordManager.getCurrentUser() == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|         ADD PASSWORD           |");
        System.out.println("===================================" + PrintedColor.resetColor);

        System.out.println("Please enter the website:");
        String websiteName = userInputScanner.next().trim();
        if (websiteName.isEmpty()) {
            System.out.println("Website cannot be empty");
            return;
        }

        System.out.println("Please enter the username:");
        String userName = userInputScanner.next().trim();
        if (userName.isEmpty()) {
            System.out.println("Username cannot be empty");
            return;
        }

        System.out.println("Please enter the desired password length:");
        int desiredPasswordLength = userInputScanner.nextInt();
        String generatedPassword = passwordGenerator.generatePassword(desiredPasswordLength);
        System.out.println("Generated password: " + PrintedColor.boldMessage + generatedPassword
                + PrintedColor.resetColor);

        System.out.println("Please enter any notes:");
        userInputScanner.nextLine(); // consume the newline left by nextInt
        String userNotes = userInputScanner.nextLine();

        Password newPassword = new Password(websiteName, userName, generatedPassword, userNotes);
        Password addedPassword =
                UserInputHandler.addPassword(passwordManager, newPassword, passwords);


        if (addedPassword != null) {
            System.out.println(PrintedColor.successMessage + "Password added successfully!"
                    + PrintedColor.resetColor);
        } else {
            System.out.println(PrintedColor.errorMessage
                    + "Password addition failed. Please try again." + PrintedColor.resetColor);
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

        List<Password> allPasswords = UserInputHandler.getAllPasswords(passwordManager, passwords);

        if (allPasswords.isEmpty()) {
            System.err.println(PrintedColor.infoMessage + "No Passwords currently available"
                    + PrintedColor.resetColor);
            return;
        }

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
            System.out.println(
                    PrintedColor.errorMessage + "Password not found" + PrintedColor.resetColor);
            return;
        }

        System.out.println("Please enter the new website:");
        String newWebsite = scanner.next();

        System.out.println("Please enter the new username:");
        String newUsername = scanner.next();

        String newPassword = passwordGenerator.generatePassword(12);
        System.out.println("Generated password: " + PrintedColor.boldMessage + newPassword
                + PrintedColor.resetColor);

        System.out.println("Please enter any new notes:");
        String newNotes = scanner.next();

        Password updatedPassword = new Password(newWebsite, newUsername, newPassword, newNotes);
        Password updateSuccessful =
                UserInputHandler.updatePassword(passwordManager, id, updatedPassword, passwords);

        if (updateSuccessful != null) {
            System.out.println(PrintedColor.successMessage
                    + "Password has been updated with the new password: " + PrintedColor.boldMessage
                    + newPassword + PrintedColor.resetColor);
        }
    }

    public static void deletePassword(PasswordManager passwordManager, Passwords passwords,
            Scanner scanner) {

        if (passwordManager.getCurrentUser() == null) {
            System.out.println("Please login first");
            return;
        }

        System.out.println(PrintedColor.warningMessage + "===================================");
        System.out.println("|        DELETE PASSWORD         |");
        System.out.println("===================================" + PrintedColor.resetColor);

        System.out.println("Please enter the ID of the password to delete:");
        int id = scanner.nextInt();
        Password passwordToDelete = passwords.getPasswordById(id);

        if (passwordToDelete == null) {
            System.out.println(
                    PrintedColor.errorMessage + "Password not found" + PrintedColor.resetColor);
            return;
        }

        boolean deletionSuccessful =
                UserInputHandler.deletePassword(passwordManager, id, passwords);

        if (deletionSuccessful) {
            System.out.println("Password with ID " + id + " has been deleted successfully");
        } else {
            System.out.println(PrintedColor.errorMessage + "Failed to delete the password"
                    + PrintedColor.resetColor);
        }

    }

}
