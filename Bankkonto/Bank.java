package Bankkonto;

import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wilkommen bei deiner Bank. Was möchten Sie machen?");

        System.out.print("Geben Sie den Inhaber ein: ");
        String inhaber = scanner.nextLine();

        System.out.print("Geben Sie die Kontonummer ein: ");
        String kontonummer = scanner.nextLine();

        System.out.print("Geben Sie den Kontostand ein: ");
        double kontostand = scanner.nextDouble();

        Account meinAccount = new Account(inhaber, kontonummer, kontostand);

        String option = "";
        while (!option.equals("exit")) {
            System.out.print("Abheben oder Einzahlen (+/-), exit zum Beenden: ");
            option = scanner.next();

            if (option.equals("+")) {
                System.out.print("Geben Sie den Betrag an: ");
                double hinzufuegenderBetrag = scanner.nextDouble();
                if (meinAccount.einzahlen(hinzufuegenderBetrag)) {
                    System.out.println("Einzahlung erfolgreich");
                } else {
                    System.out.println("Einzahlung war nicht erfolgreich");
                }
            } else if (option.equals("-")) {
                System.out.print("Geben Sie den Betrag an: ");
                double abziehenderBetrag = scanner.nextDouble();
                if (meinAccount.auszahlen(abziehenderBetrag)) {
                    System.out.println("Auszahlung erfolgreich");
                } else {
                    System.out.println("Auszahlung war nicht erfolgreich");
                }
            } else if (!option.equals("exit")) {
                System.out.println("Ungültige Option");
            }
        }

        scanner.close();
    }
}
