package com.pass.helper;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pass.model.Password;
import com.pass.model.Passwords;
import com.pass.model.User;
import com.pass.model.Users;

public class PasswordManager {

    private UID uid = null;
    private Users users;
    private Passwords passwords;
    private User currentUser;

    public PasswordManager(Users userslist, Passwords passwordslist) {
        UID uid = new UID();
        users = userslist;
        passwords = passwordslist;
        currentUser = null;
    }

    public boolean loginUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        for (User u : users.getUsers()) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                currentUser = u;
                return true;
            }
        }
        return false;
    }

    public boolean logoutUser(String username, String password) {
        currentUser = getCurrentUser();
        if (currentUser != null) {
            currentUser = null;
            return true;

        }
        return false;
    }

    public boolean registerUser(String username, String password) {
        List<User> userList = new ArrayList<>(Arrays.asList(users.getUsers()));
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        User user = new User(username, password);
        userList.add(user);
        users.addUser(user);
        currentUser = user;
        return true;
    }

    public User getCurrentUser() {
        if (currentUser != null) {
            return currentUser;
        }

        return null;
    }

    public boolean changeUserPassword(String oldPassword, String newPassword) {
        if (currentUser != null && currentUser.getPassword().equals(oldPassword)) {
            currentUser.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public boolean addPassword(String website, String username, String password, String notes) {
        if (currentUser != null) {
            Password newpassword = new Password(website, username, password, notes);
            passwords.addPassword(newpassword);
            return true;
        }
        return false;
    }

    public Passwords getAllPasswords() {
        if (currentUser != null) {
            return passwords;
        }

        return new Passwords();
    }

}
