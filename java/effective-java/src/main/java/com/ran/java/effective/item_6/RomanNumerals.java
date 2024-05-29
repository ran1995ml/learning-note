package com.ran.java.effective.item_6;

import java.util.regex.Pattern;

/**
 * RomanNumerals
 *
 * @author rwei
 * @since 2024/4/23 09:46
 */
public class RomanNumerals {
    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})");

    static boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches();
    }
}
