/* 
 *  BankKonto 
 *  
 *   Bankkonto (Inhaber, Kontonummer, Kontostand, Methoden (einzahlen, auszahlen)
Negativer Kontostand?
gleiche Kontonummer nicht möglich
 * 
 */

package Bankkonto;

public class Account {

    private String inhaber;
    private String kontonummer;
    private double kontostand;

    public Boolean einzahlen(Double betrag) {
        if (betrag == null && betrag < 0) {
            return false;
        }
        kontostand += betrag;
        aktuellerWert();
        return true;
    }

    public Boolean auszahlen(Double betrag) {
        if (betrag == null && betrag <= 0) {
            return false;
        }
        kontostand -= betrag;
        aktuellerWert();
        return true;
    }

    private void aktuellerWert() {
        System.out.println("Neuer Kontostand:" + kontostand);
    }

    public Account(String Inhaber, String Kontonummer, Double Kontostand) {
        inhaber = Inhaber;
        kontonummer = Kontonummer;
        kontostand = Kontostand;
        System.out.println(
                "Ihre Kontonummer " + Kontonummer + "  " + " Inhaber " + inhaber + " Kontostand aktuell " + kontostand
                        + " €");
    }

}
