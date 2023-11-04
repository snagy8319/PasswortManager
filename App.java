import java.util.InputMismatchException;
import java.util.Scanner;

import client.helper.PasswordGenerator;
import client.helper.PasswordManager;
import client.helper.UserInputOptions;
import client.helper.ui.PrintedColor;
import client.model.Passwords;
import client.model.Users;

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

                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        UserInputOptions.login(scanner, passwordManager);
                        break;
                    case 2:
                        UserInputOptions.registration(scanner, users);
                        break;
                    case 3:
                        UserInputOptions.logout(passwordManager);
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
