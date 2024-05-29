package com.ran.java.effective.item_2;

/**
 * NutritionFacts
 *
 * @author rwei
 * @since 2024/4/21 21:27
 */
public class NutritionFacts {
    private final int size;
    private final int fat;
    private final int sodium;

    private NutritionFacts(Builder builder) {
        size = builder.size;
        fat = builder.fat;
        sodium = builder.sodium;
    }

    public static class Builder {
        private final int size;
        private final int fat;
        private int sodium = 0;

        public Builder(int size, int fat) {
            this.size = size;
            this.fat = fat;
        }

        public Builder sodium(int val) {
            this.sodium = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }
}
