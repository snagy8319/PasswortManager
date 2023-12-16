// No package declaration needed since the expected package is empty
package com.pass;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.pass.helper.PasswordGenerator;
import com.pass.helper.PasswordManager;
import com.pass.model.Passwords;
import com.pass.model.Users;
import com.pass.ui.*;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Please enter a number.";
    private static final String ERROR_MESSAGE = "An error occurred: ";

    /**
     * Die Hauptmethode des Programms. Erstellt einen neuen Passwortgenerator, Benutzer, Passwörter
     * und Passwortmanager. Liest Benutzereingaben ein und verarbeitet die entsprechende Option.
     * 
     * @param args Die Befehlszeilenargumente.
     */
    public static void main(String[] args) {
        // create a new password generator
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        // create a new users object
        Users users = new Users();

        // create a new passwords object
        Passwords passwords = new Passwords();

        // create a new password manager
        PasswordManager passwordManager = new PasswordManager(users, passwords);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                UserInputOptions.welcomeMessage();
                int option = scanner.nextInt();

                handleOption(option, scanner, passwordManager, users, passwords, passwordGenerator);
            } catch (InputMismatchException e) {
                LOGGER.warning(INVALID_INPUT_MESSAGE);
                scanner.nextLine(); // discard the current input
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, ERROR_MESSAGE + e.getMessage(), e);
            }
        }
    }

    /**
     * Handles the selected option by performing the corresponding action.
     *
     * @param option the selected option
     * @param scanner the scanner object for user input
     * @param passwordManager the password manager object
     * @param users the users object
     * @param passwords the passwords object
     * @param passwordGenerator the password generator object
     */
    private static void handleOption(int option, Scanner scanner, PasswordManager passwordManager,
            Users users, Passwords passwords, PasswordGenerator passwordGenerator) {
        switch (option) {
            case 1:
                handleLogin(scanner, passwordManager);
                break;
            case 2:
                handleRegistration(scanner, users);
                break;
            case 3:
                handleLogout(passwordManager);
                break;
            case 4:
                handleAddPassword(passwordManager, passwordGenerator, passwords, scanner);
                break;
            case 5:
                handleGetAllPasswords(passwordManager, passwords);
                break;
            case 6:
                handleUpdatePassword(passwordManager, passwords, passwordGenerator, scanner);
                break;
            case 7:
                handleDeletePassword(passwordManager, passwords, scanner);
                break;
            case 8:
                handleExit(scanner);
                break;
            default:
                LOGGER.warning("Invalid option. Please enter a valid option.");
                break;
        }
    }


    /**
     * Behandelt den Login-Vorgang.
     * 
     * @param scanner Der Scanner zum Einlesen der Benutzereingabe.
     * @param passwordManager Der PasswordManager zur Verwaltung der Passwörter.
     */
    private static void handleLogin(Scanner scanner, PasswordManager passwordManager) {
        UserInputOptions.login(passwordManager);
    }


    /**
     * Behandelt die Registrierung eines Benutzers.
     * 
     * @param scanner Das Scanner-Objekt zum Einlesen der Benutzereingabe.
     * @param users Das Users-Objekt, das die Benutzerdaten verwaltet.
     */
    private static void handleRegistration(Scanner scanner, Users users) {
        UserInputOptions.registration(scanner, users);
    }


    /**
     * Behandelt die Abmeldung des Benutzers.
     *
     * @param passwordManager Der PasswordManager, der verwendet wird.
     */
    private static void handleLogout(PasswordManager passwordManager) {
        UserInputOptions.logout(passwordManager);
    }

    /**
     * Fügt ein Passwort zum Passwort-Manager hinzu.
     * 
     * @param passwordManager Der Passwort-Manager, der das Passwort speichert.
     * @param passwordGenerator Der Passwort-Generator, der ein neues Passwort generiert.
     * @param passwords Die Liste der vorhandenen Passwörter.
     * @param scanner Der Scanner, um Benutzereingaben zu lesen.
     */
    private static void handleAddPassword(PasswordManager passwordManager,
            PasswordGenerator passwordGenerator, Passwords passwords, Scanner scanner) {
        UserInputOptions.addPassword(passwordManager, passwordGenerator, passwords, scanner);
    }

    /**
     * Behandelt das Abrufen aller Passwörter.
     *
     * @param passwordManager Das Passwort-Manager-Objekt.
     * @param passwords Die Passwort-Objekte.
     */
    private static void handleGetAllPasswords(PasswordManager passwordManager,
            Passwords passwords) {
        UserInputOptions.getAllPasswords(passwordManager, passwords);
    }

    /**
     * Behandelt das Aktualisieren des Passworts.
     * 
     * @param passwordManager Der PasswordManager, der für die Verwaltung der Passwörter verwendet
     *        wird.
     * @param passwords Die Passwörter, die aktualisiert werden sollen.
     * @param passwordGenerator Der PasswordGenerator, der zum Generieren neuer Passwörter verwendet
     *        wird.
     * @param scanner Der Scanner, der für die Benutzereingabe verwendet wird.
     */
    private static void handleUpdatePassword(PasswordManager passwordManager, Passwords passwords,
            PasswordGenerator passwordGenerator, Scanner scanner) {
        UserInputOptions.updatePassword(passwordManager, passwords, passwordGenerator, scanner);
    }

    /**
     * Löscht das angegebene Passwort aus dem PasswordManager.
     * 
     * @param passwordManager Der PasswordManager, der das Passwort enthält.
     * @param passwords Die Liste der Passwörter, aus der das Passwort gelöscht werden soll.
     * @param scanner Der Scanner, um Benutzereingaben zu lesen.
     */
    private static void handleDeletePassword(PasswordManager passwordManager, Passwords passwords,
            Scanner scanner) {
        UserInputOptions.deletePassword(passwordManager, passwords, scanner);
    }

    /**
     * Beendet das Programm und schließt den Scanner.
     *
     * @param scanner der Scanner, der geschlossen werden soll
     */
    private static void handleExit(Scanner scanner) {
        scanner.close();
        System.exit(0);
    }

}
