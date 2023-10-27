package client.helper;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginDataList {
    private ArrayList<LoginData> LoginDataListe;
    private static final String BENUTZERNAME = "admin";
    private static final String PASSWORT = "passwort123";
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean isUserValid() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Benutzername: ");
        String username = scanner.nextLine();
        System.out.print("Passwort: ");
        String password = scanner.nextLine();
        return BENUTZERNAME.equals(username) && PASSWORT.equals(password);
    }

    public static void Login() {
        if (!isUserValid()) {
            System.out.println("Falscher Benutzername oder Passwort!");
            return;
        }
    }

    public LoginDataList() {
        this.LoginDataListe = new ArrayList<LoginData>();
    }

    public boolean addLoginData(LoginData loginData) {
        return LoginDataListe.add(loginData);
    }

    public void getLoginData() {
        for (LoginData loginData : LoginDataListe) {
            System.out.println("Ausgabe der gesamten Passwortliste");
            System.out.println("Username: " + loginData.Title + "\n" +
                    "Applikation: " + loginData.Description + "\n" +
                    "Password: " + loginData.PinCode + "\n");
        }
        scanner.nextLine();
    }

    public boolean isEmpty() {
        return LoginDataListe.isEmpty();
    }

    public boolean removeLoginData(int index) {
        if (index < 0 || index >= LoginDataListe.size()) {
            return false; // Index out of bounds
        }
        LoginDataListe.remove(index);
        return true;
    }
}
