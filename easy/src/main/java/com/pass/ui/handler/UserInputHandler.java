package com.pass.ui.handler;

import java.util.List;
import com.pass.helper.PasswordManager;
import com.pass.model.Password;
import com.pass.model.Passwords;
import com.pass.model.User;
import com.pass.model.Users;

public class UserInputHandler {

    public static User registerUser(String newUsername, char[] newPassword, Users users) {
        User newUser = new User(newUsername, new String(newPassword));
        return users.addUser(newUser);
    }

    public static boolean login(String username, String password, PasswordManager passwordManager) {
        User user = new User(username, password);
        return passwordManager.loginUser(user);
    }

    public static boolean logout(String username, String password,
            PasswordManager passwordManager) {

        return passwordManager.logoutUser(username, password);
    }

    public static Password addPassword(PasswordManager passwordManager, Password newPassword,
            Passwords passwords) {
        return passwords.addPassword(newPassword);
    }

    public static List<Password> getAllPasswords(PasswordManager passwordManager,
            Passwords passwords) {
        return passwords.getAllPasswords();
    }


    public static Password updatePassword(PasswordManager passwordManager, int id,
            Password updatedPassword, Passwords passwords) {
        return passwords.updatePassword(id, updatedPassword);
    }

    public static boolean deletePassword(PasswordManager passwordManager, int id,
            Passwords passwords) {
        return passwords.deletePassword(id);
    }

    // Similar changes for other methods
}
