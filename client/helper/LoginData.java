package client.helper;

public class LoginData {
    String Title;
    String Description;
    String PinCode;

    public LoginData(String title, String description) {
        Title = title;
        Description = description;
        PinCode = PinCodeGenerator.generierePin(12);
    }
}
