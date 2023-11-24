package client.model;

import java.rmi.server.UID;

public class Password {

    private int id;
    private String website;
    private String username;
    private String password;
    private String notes;

    public int getId() {
        return id;
    }

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

    public Password(String website, String username, String password, String notes) {
        id = new UID().hashCode();
        this.website = website;
        this.username = username;
        this.password = password;
        this.notes = notes;
    }

}
