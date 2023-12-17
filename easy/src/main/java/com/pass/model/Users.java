package com.pass.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Diese Klasse verwaltet eine Liste von Benutzern.
 */
public class Users {
    private List<User> users; // Die Liste der Benutzer

    // Konstruktor
    public Users() {
        this.users = new ArrayList<>();
    }

    /**
     * Gibt einen User zurück, basierend auf seiner UID.
     *
     * @param uid Die eindeutige ID des Benutzers.
     * @return Der User mit der angegebenen UID, oder null, wenn nicht gefunden.
     */
    public User getUser(Integer id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Invalid ID");
        }
        return users.stream().filter(user -> Objects.equals(user.getID(), id)).findFirst()
                .orElse(null);
    }

    /**
     * Fügt einen neuen User hinzu oder ersetzt einen bestehenden mit derselben UID.
     *
     * @param user Der hinzuzufügende User.
     * @return Der hinzugefügte oder aktualisierte User.
     */
    public User addUser(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        users.add(user);
        return user;
    }

    /**
     * Löscht einen User anhand seiner ID.
     *
     * @param id Die ID des zu löschenden Users.
     * @return Der gelöschte User, oder null, wenn der User nicht gefunden wurde.
     */
    public User deleteUser(int id) throws IndexOutOfBoundsException {
        return users.stream().filter(user -> user.getID() == id).findFirst().map(userToDelete -> {
            users.remove(userToDelete);
            return userToDelete;
        }).orElse(null);
    }

    /**
     * Aktualisiert die Daten eines Users basierend auf seiner UID.
     *
     * @param uid Die UID des zu aktualisierenden Users.
     * @param updatedUser Der User mit den aktualisierten Daten.
     * @return Der aktualisierte User.
     */
    public User updateUser(int id, User updateUser)
            throws IndexOutOfBoundsException, IllegalArgumentException {
        int index = users.indexOf(
                users.stream().filter(user -> user.getID() == id).findFirst().orElse(null));
        if (index == -1) {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
        if (updateUser == null) {
            throw new IllegalArgumentException("Updated user cannot be null");
        }

        users.set(index, updateUser);
        return updateUser;
    }

    public User[] getUsers() {
        return users.toArray(new User[0]);
    }

    public void setUsers(User[] array) {}
}
