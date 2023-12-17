package com.pass.model;

import java.rmi.server.UID;

/**
 * Diese Klasse repräsentiert einen Benutzer.
 */
public class User {

    private int id;
    public String username;
    private String password;

    /**
     * Gibt die ID des Benutzers zurück.
     * 
     * @return die ID des Benutzers
     */
    public int getID() {
        return id;
    }

    /**
     * Gibt den Benutzernamen zurück.
     * 
     * @return der Benutzername
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gibt das Passwort des Benutzers zurück.
     * 
     * @return das Passwort des Benutzers
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setzt das Passwort des Benutzers.
     * 
     * @param newPassword das neue Passwort
     * @return das aktualisierte Passwort des Benutzers
     */
    public String setPassword(String newPassword) {
        password = newPassword;
        return password;
    }

    /**
     * Erstellt einen neuen Benutzer mit dem angegebenen Benutzernamen und Passwort.
     * 
     * @param UserName der Benutzername
     * @param Password das Passwort
     */
    public User(String UserName, String Password) {
        id = new UID().hashCode();
        username = UserName;
        password = Password;
    }
}
