package com.pass.helper;

import java.util.Random;

public class PasswordGenerator {

    private final String ZEICHEN = "0123456789!@#$%^&*()_-+=<>?";

    public String generatePassword(int laenge) {
        Random rand = new Random();
        StringBuilder pin = new StringBuilder();

        for (int i = 0; i < laenge; i++) {
            char zeichen = ZEICHEN.charAt(rand.nextInt(ZEICHEN.length()));
            pin.append(zeichen);
        }
        return pin.toString();
    }
}
