import client.helper.PasswordManager;

public class App {
    public static void main(String[] args) {

        PasswordManager password = new PasswordManager();

        password.registerUser("Nase", "11334556566");
        System.out.println(password.getCurrentUser().getUsername());
        password.loginUser("Nase", "11334556566");
        password.addPassword("google", "nase", "1234", "test eintrag");
    }
}
