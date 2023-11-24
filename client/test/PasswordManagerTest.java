package client.test;

import org.junit.Test;

class PasswordManagerTest {

    @Test
    void testLogin() {
        Users users = new Users();
        Passwords passwords = new Passwords();
        PasswordManager passwordManager = new PasswordManager(users, passwords);

        // Add a user and their password
        users.addUser("testUser");
        passwords.addPassword("testUser", "testPassword");

        // Test successful login
        assertTrue(passwordManager.login("testUser", "testPassword"));

        // Test unsuccessful login
        assertFalse(passwordManager.login("testUser", "wrongPassword"));
    }

    // Add more tests for other methods in PasswordManager
}