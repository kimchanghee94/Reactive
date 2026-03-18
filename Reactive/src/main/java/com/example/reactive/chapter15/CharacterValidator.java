package com.example.reactive.chapter15;

import java.util.zip.DataFormatException;

public class CharacterValidator {
    public static void isAlphabeticCharacter(char ch) throws DataFormatException {
        if (!Character.isAlphabetic(ch)) {
            throw new DataFormatException("Not Alphabetic");
        }
    }
}
