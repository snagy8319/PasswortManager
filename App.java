import java.util.InputMismatchException;
import java.util.Scanner;

import client.helper.PasswordGenerator;
import client.helper.PasswordManager;
import client.helper.ui.PrintedColor;
import client.model.Passwords;
import client.model.Users;
import client.ui.*;

public class App {
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

                switch (option) {
                    case 1:
                        boolean isloggedin = UserInputOptions.login(scanner, passwordManager);
                        OutputConsole.writeConsole(isloggedin, "loggedin");
                        break;
                    case 2:
                        boolean isregistered = UserInputOptions.registration(scanner, users);
                        OutputConsole.writeConsole(isregistered, "registering");
                        break;
                    case 3:
                        boolean isloggedout = UserInputOptions.logout(passwordManager);
                        OutputConsole.writeConsole(isloggedout, "logout");
                        break;
                    case 4:
                        UserInputOptions.addPassword(passwordManager, passwordGenerator, passwords, scanner);
                        break;
                    case 5:
                        UserInputOptions.getAllPasswords(passwordManager, passwords);
                        break;
                    case 6:
                        UserInputOptions.updatePassword(passwordManager, passwords, passwordGenerator, scanner);
                        break;
                    case 7:
                        UserInputOptions.deletePassord(passwordManager, passwords, scanner);
                        break;
                    case 8:
                        scanner.close();
                        System.exit(0);
                    default:
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println(
                        PrintedColor.errorMessage + "Invalid input. Please enter a number." + PrintedColor.resetColor);
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println(
                        PrintedColor.errorMessage + "An error occurred: " + e.getMessage() + PrintedColor.resetColor);
                scanner.nextLine();
            }
        }
    }
}
