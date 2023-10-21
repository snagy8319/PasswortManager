package Model;

import java.rmi.server.UID;

public class Password {

    private UID id;
    private String website;
    private String username;
    private String password;
    private String notes;

    // GET METHODS

    public String getWebsite() {
        return website;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNotes() {
        return notes;
    }
    // SET METHODS

    public Password(String website, String username, String password, String notes) {
        id = new UID();
        this.website = website;
        this.username = username;
        this.password = password;
        this.notes = notes;
    }
}
