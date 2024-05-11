package com.ran.java.effective.effective_2;

/**
 * Main
 *
 * @author rwei
 * @since 2024/4/21 21:36
 */
public class Main {
    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new NutritionFacts.Builder(1, 1)
                .sodium(1)
                .build();
    }
}
