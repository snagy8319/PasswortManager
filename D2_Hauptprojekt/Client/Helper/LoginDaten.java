
public class LoginDaten {
    String Title;
    String Description;
    String PinCode;

    public LoginDaten(String title, String description) {
        Title = title;
        Description = description;
        PinCode = PinCodeGenerator.generierePin(12);
    }
}
