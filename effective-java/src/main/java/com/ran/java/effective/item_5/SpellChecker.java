package com.ran.java.effective.item_5;

import java.util.Objects;

/**
 * SpellChecker
 *
 * @author rwei
 * @since 2024/4/22 21:55
 */
public class SpellChecker {
    private final Lexicon dictionary;

    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }
}
