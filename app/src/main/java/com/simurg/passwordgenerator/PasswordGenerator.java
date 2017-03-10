package com.simurg.passwordgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by uyegen on 08.03.2017.
 */

public class PasswordGenerator {
    private List<String> charList;
    private int len;

    public PasswordGenerator(boolean sy, boolean nu, boolean lo, boolean up, boolean si, boolean am,
                             int len) {
        this.charList = new ArrayList<>();
        String symbols = "é,;.:-_*?=})](/{&%+$#'!<>\\\"";
        String numbers = "1234567890";
        String lowerCharacters = "abcdefghijklmnopqrstuvwxyz";
        String upperCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String similarCharacters = "il1Lo0O";
        String ambiguousCharacters = "{}[]()/\\'\"`~,;:.<>";
        if (sy) {
            this.charList.addAll(Arrays.asList(symbols.split("")));
        }
        if (nu) {
            this.charList.addAll(Arrays.asList(numbers.split("")));
        }
        if (lo) {
            this.charList.addAll(Arrays.asList(lowerCharacters.split("")));
        }
        if (up) {
            this.charList.addAll(Arrays.asList(upperCharacters.split("")));
        }
        if (si) {
            this.charList.removeAll(Arrays.asList(similarCharacters.split("")));
        }
        if (am) {
            this.charList.removeAll(Arrays.asList(ambiguousCharacters.split("")));
        }
        this.len = len;
    }

    public String generate() {
        String t = "";
        Random r = new Random();
        Collections.shuffle(this.charList);
        while (t.length() < this.len) {
            t += this.charList.get(r.nextInt(this.charList.size()));
        }
        return t;
    }
}
