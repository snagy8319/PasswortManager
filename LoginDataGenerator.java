import java.util.Scanner;

public class LoginDataGenerator {
  public static void main(String[] args) {

    boolean isrunning = true;
    String loginUsername;
    String logionApplication;
    LoginDataList loginDataList = new LoginDataList();
    Scanner scanner = new Scanner(System.in);

    while (isrunning) {
      System.out.print("\033\143"); // Clear the console
      System.out.println("1. Logindaten hinzufuegen"); // Add task
      System.out.println("2. Logindaten entfernen"); // Remove task
      System.out.println("3. Alle Logindaten anzeigen"); // Show list
      System.out.println("4. Beenden \n"); // Exit

      int choice = scanner.nextInt(); // Read user input
      scanner.nextLine(); // Consume the newline character

      switch (choice) {
        case 1:
          System.out.println("Username"); // Prompt for task title
          loginUsername = scanner.nextLine(); // Read task title
          System.out.println("Applikation"); // Prompt for task description
          logionApplication = scanner.nextLine(); // Read task description
          LoginDaten loginData = new LoginDaten(loginUsername, logionApplication); // Create
                                                                                   // new
                                                                                   // task
          loginDataList.addLoginData(loginData); // Add task to list
          break;
        case 2:
          System.out.println("\n Logindaten entfernen, gib den index an"); // Prompt for task index to remove
          int index = scanner.nextInt(); // Read task index
          loginDataList.removeLoginData(index); // Remove task from list
          break;
        case 3:
          if (loginDataList.isEmpty()) {
            System.out.println("\n Passwortliste ist leer");
            break;
          }
          loginDataList.getLoginData();
          break;
        case 4:
          System.out.println("\n Programm wird beendet.");
          isrunning = false;
          System.out.print("\033\143");
          break;
        default:
          System.out.println("\n Ungültige Auswahl!");
      }

    }
    scanner.close();
  }

}
