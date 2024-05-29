package com.ran.java.effective.item_2;

import java.util.Objects;

/**
 * NyPizza
 *
 * @author rwei
 * @since 2024/4/21 21:52
 */
public class NyPizza extends Pizza {
    private final Size size;

    private NyPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }

    public enum Size {SMALL, LARGE}

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
