package client.model;

import java.util.ArrayList;
import java.util.List;

public class Passwords {

    private List<Password> passwords;

    public Passwords() {
        passwords = new ArrayList<>();
    }

    public Password getPasswordById(int id) {
        for (Password password : passwords) {
            if (password.getId() == id) {
                return password;
            }
        }
        throw new IllegalArgumentException("Invalid password ID");
    }

    public List<Password> getAllPasswords() {
        return new ArrayList<>(passwords);
    }

    public Password addPassword(Password password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        passwords.add(password);
        return password;
    }

    public Password updatePassword(int id, Password updatedPassword) {
        if (updatedPassword == null) {
            throw new IllegalArgumentException("Updated password cannot be null");
        }
        for (int i = 0; i < passwords.size(); i++) {
            if (passwords.get(i).getId() == id) {
                passwords.set(i, updatedPassword);
                return updatedPassword;
            }
        }
        throw new IllegalArgumentException("Invalid password ID");
    }

    public Password deletePassword(int id) {
        Password passwordToDelete = getPasswordById(id);
        passwords.remove(passwordToDelete);
        return passwordToDelete;
    }

}