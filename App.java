import client.helper.*;
import client.model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        String username;
        String password;
        String newUsername;
        String newPassword;
        String newNotes;
        String newWebsite;

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
                System.out.println("===================================");
                System.out.println("|          PASSWORD MANAGER       |");
                System.out.println("===================================");
                System.out.println("| Options:                        |");
                System.out.println("|        1. Login                 |");
                System.out.println("|        2. Register              |");
                System.out.println("|        3. Logout                |");
                System.out.println("|        4. Add password          |");
                System.out.println("|        5. Get all passwords     |");
                System.out.println("|        6. Update password       |");
                System.out.println("|        7. Delete password       |");
                System.out.println("|        8. Exit                  |");
                System.out.println("===================================");

                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("===================================");
                        System.out.println("|             LOGIN              |");
                        System.out.println("===================================");
                        System.out.println("Please enter your username:");
                        username = scanner.next();
                        System.out.println("Please enter your password:");
                        password = scanner.next();
                        User user = new User(username, password);
                        boolean loginSuccessful = passwordManager.loginUser(user);
                        if (loginSuccessful) {
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Login failed. User does not exists.");
                        }
                        break;
                    case 2:
                        System.out.println("===================================");
                        System.out.println("|           REGISTER             |");
                        System.out.println("===================================");
                        System.out.println("Please enter your username:");
                        newUsername = scanner.next();
                        System.out.println("Please enter your password:");
                        newPassword = scanner.next();
                        User newUser = new User(newUsername, newPassword);
                        User addedUser = users.addUser(newUser);
                        if (addedUser != null) {
                            System.out.println("Registration successful!");
                        } else {
                            System.out.println("Registration failed. Please try again.");
                        }
                        break;
                    case 3:
                        boolean logout = false;
                        User currentUser = passwordManager.getCurrentUser();
                        if (currentUser != null) {
                            logout = passwordManager.logoutUser(currentUser.getUsername(), currentUser.getPassword());
                        }

                        if (logout) {
                            System.out.println("Logout successful!");
                        } else {
                            System.out.println("User is not logged-in");
                        }
                        break;
                    case 4:
                        if (passwordManager.getCurrentUser() == null) {
                            System.out.println("Please login first.");
                            break;
                        }
                        System.out.println("===================================");
                        System.out.println("|         ADD PASSWORD           |");
                        System.out.println("===================================");
                        System.out.println("Please enter the website:");
                        newWebsite = scanner.next();
                        System.out.println("Please enter the username:");
                        newUsername = scanner.next();
                        newPassword = passwordGenerator.generatePassword(12);
                        System.out.println("Generated password: " + newPassword);
                        System.out.println("Please enter any notes:");
                        newNotes = scanner.next();
                        Password newPassword2 = new Password(newWebsite, newUsername, newPassword, newNotes);
                        Password addedPassword = passwords.addPassword(newPassword2);
                        if (addedPassword != null) {
                            System.out.println("Password added successfully!");
                        } else {
                            System.out.println("Password addition failed. Please try again.");
                        }
                        break;
                    case 5:
                        if (passwordManager.getCurrentUser() == null) {
                            System.out.println("Please login first.");
                            break;
                        }
                        System.out.println("===================================");
                        System.out.println("|        ALL PASSWORDS           |");
                        System.out.println("===================================");
                        for (Password password3 : passwords.getAllPasswords()) {
                            System.out.println("Website: " + password3.getWebsite());
                            System.out.println("Username: " + password3.getUsername());
                            System.out.println("Password: " + password3.getPassword());
                            System.out.println("Notes: " + password3.getNotes());
                            System.out.println("===================================");
                        }
                        break;
                    case 6:
                        if (passwordManager.getCurrentUser() == null) {
                            System.out.println("Please login first.");
                            break;
                        }
                        System.out.println("===================================");
                        System.out.println("|        UPDATE PASSWORD         |");
                        System.out.println("===================================");
                        System.out.println("Please enter the ID of the password to update:");

                        int id = scanner.nextInt();
                        Password passwordToUpdate = passwords.getPasswordById(id);
                        if (passwordToUpdate == null) {
                            System.out.println("Password not found");
                            break;
                        }
                        System.out.println("Please enter the new website:");
                        newWebsite = scanner.next();

                        System.out.println("Please enter the new username:");
                        newUsername = scanner.nextLine();

                        newPassword = passwordGenerator.generatePassword(12);
                        System.out.println("Generated password: " + newPassword);

                        System.out.println("Please enter any new notes:");
                        newNotes = scanner.next();
                        Password updatedPassword = new Password(newWebsite, newUsername, newPassword, newNotes);
                        Password updateSuccessful = passwords.updatePassword(id, updatedPassword);

                        if (updateSuccessful != null) {
                            System.out.println("Password has been updated with new Password" + newPassword);
                        }

                        break;
                    case 8:
                        scanner.close();
                        System.exit(0);
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
