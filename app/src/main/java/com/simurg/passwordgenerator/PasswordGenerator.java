package com.simurg.passwordgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by uyegen on 08.03.2017.
 */

class PasswordGenerator {
    private String charStr = "";
    private int len;

    PasswordGenerator(boolean sy, boolean nu, boolean lo, boolean up, boolean si, boolean am,
                      int len) {
        String symbols = "é,;.:-_*?=})](/{&%+$#'!<>\\\"";
        String numbers = "1234567890";
        String lowerCharacters = "abcdefghijklmnopqrstuvwxyz";
        String upperCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String similarCharacters = "il1Lo0O";
        String ambiguousCharacters = "{}[]()/\\'\"`~,;:.<>";
        if (sy) {
            charStr += symbols;
        }
        if (nu) {
            charStr += numbers;
        }
        if (lo) {
            charStr += lowerCharacters;
        }
        if (up) {
            charStr += upperCharacters;
        }
        if (si) {
            charStr += similarCharacters;
        }
        if (am) {
            charStr += ambiguousCharacters;
        }
        this.len = len;
    }

    String generate() {
        String t = "";
        Random r = new Random();
        List<String> charList = new ArrayList<>(Arrays.asList(charStr.split("")));
        Collections.shuffle(charList);
        while (t.length() < this.len) {
            t += charList.get(r.nextInt(charList.size()));
        }
        return t;
    }
}
