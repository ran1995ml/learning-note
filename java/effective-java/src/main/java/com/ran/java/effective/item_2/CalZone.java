package com.ran.java.effective.item_2;

/**
 * CalZone
 *
 * @author rwei
 * @since 2024/4/21 21:58
 */
public class CalZone extends Pizza {
    private final boolean sauceInside;

    private CalZone(Builder builder) {
        super(builder);
        this.sauceInside = builder.sauceInside;
    }

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false;

        public Builder sauceInside() {
            this.sauceInside = true;
            return this;
        }


        @Override
        public CalZone build() {
            return new CalZone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
