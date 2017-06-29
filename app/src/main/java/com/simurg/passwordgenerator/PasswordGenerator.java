package com.simurg.passwordgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by uyegen on 08.03.2017.
 */

enum Characters {
    Symbol, Numbers, LowerCharacters, UpperCharacters, SimilarCharacters, AmbiguousCharacters
}

public class PasswordGenerator {
    private List<String> charList;
    private int len;
    private String symbols = "é,;.:-_*?=})](/{&%+$#'!<>\\\"";
    private String numbers = "1234567890";
    private String lowerCharacters = "abcdefghijklmnopqrstuvwxyz";
    private String upperCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String similarCharacters = "il1Lo0O";
    private String ambiguousCharacters = "{}[]()/\\'\"`~,;:.<>";

    /**
     * @param sy Does password contains symbols? -> é,;.:-_*?=})](/{&%+$#'!<>\" <-
     * @param nu Does password contains numbers? -> 1234567890 <-
     * @param lo Does password contains lower characters? -> abcdefghijklmnopqrstuvwxyz <-
     * @param up Does password contains upper characters? -> ABCDEFGHIJKLMNOPQRSTUVWXYZ <-
     * @param si Does password contains similar characters? -> il1Lo0O <-
     * @param am Does password contains ambiguous characters? -> {}[]()/\'"`~,;:.<> <-
     * @param len How much character does password contains?
     */
    public PasswordGenerator(boolean sy, boolean nu, boolean lo, boolean up, boolean si, boolean am,
                             int len) {
        this.charList = new ArrayList<>();
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

    /**
     * @return Generated Password for given parameters to Constructor.
     */
    public String generate() {
        String t = "";
        Random r = new Random();
        Collections.shuffle(this.charList);
        while (t.length() < this.len) {
            t += this.charList.get(r.nextInt(this.charList.size()));
        }
        return t;
    }

    /**
     * @param characters Symbol, Numbers, LowerCharacters, UpperCharacters, SimilarCharacters, AmbiguousCharacters
     * @return Selected string pack
     */
    public String getCharacters(Characters characters) {
        switch (characters) {
            case Symbol:
                return this.symbols;
            case Numbers:
                return this.numbers;
            case LowerCharacters:
                return this.lowerCharacters;
            case UpperCharacters:
                return this.upperCharacters;
            case SimilarCharacters:
                return this.similarCharacters;
            case AmbiguousCharacters:
                return this.ambiguousCharacters;
        }
        return "";
    }
}
