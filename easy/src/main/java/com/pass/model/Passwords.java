package com.pass.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert eine Sammlung von Passwörtern.
 */
public class Passwords {

    private List<Password> passwords;

    /**
     * Konstruktor für die Passwords-Klasse. Initialisiert die Liste der Passwörter.
     */
    public Passwords() {
        this.passwords = new ArrayList<>();
    }

    /**
     * Gibt das Passwort mit der angegebenen ID zurück.
     *
     * @param id Die ID des gesuchten Passworts.
     * @return Das Passwort mit der angegebenen ID.
     * @throws IllegalArgumentException Wenn die ID ungültig ist.
     */
    /*
     * public Password getPasswordById(int id) { for (Password password : passwords) { if
     * (password.getId() == id) { return password; } } throw new
     * IllegalArgumentException("Ungültige Passwort-ID"); }
     */

    /**
     * Gibt eine Kopie aller Passwörter zurück.
     *
     * @return Eine Liste mit allen Passwörtern.
     */

    public List<Password> getAllPasswords() {
        return new ArrayList<>(passwords);
    }

    /**
     * Fügt ein neues Passwort hinzu.
     *
     * @param password Das hinzuzufügende Passwort.
     * 
     * @return Das hinzugefügte Passwort.
     * 
     * @throws IllegalArgumentException Wenn das Passwort null ist.
     */
    /*
     * public Password addPassword(Password password) { if (password == null) { throw new
     * IllegalArgumentException("Passwort darf nicht null sein"); } passwords.add(password); return
     * password; }
     */
    /**
     * Aktualisiert das Passwort mit der angegebenen ID.
     *
     * @param id Die ID des zu aktualisierenden Passworts.
     * @param updatedPassword Das aktualisierte Passwort.
     * @return Das aktualisierte Passwort.
     * @throws IllegalArgumentException Wenn das aktualisierte Passwort null ist oder die ID
     *         ungültig ist.
     */
    /*
     * public Password updatePassword(int id, Password updatedPassword) { if (updatedPassword ==
     * null) { throw new IllegalArgumentException("Aktualisiertes Passwort darf nicht null sein"); }
     * for (int i = 0; i < passwords.size(); i++) { if (passwords.get(i).getId() == id) {
     * passwords.set(i, updatedPassword); return updatedPassword; } } throw new
     * IllegalArgumentException("Ungültige Passwort-ID"); }
     */
    /**
     * Löscht das Passwort mit der angegebenen ID.
     *
     * @param id Die ID des zu löschenden Passworts.
     * @return true, wenn das Passwort erfolgreich gelöscht wurde, sonst false.
     */
    /*
     * public boolean deletePassword(int id) { Password passwordToDelete = getPasswordById(id);
     * boolean isPasswordDeleted = passwords.remove(passwordToDelete); return isPasswordDeleted; }
     */

}
