package com.ran.java.effective.item_2;

import java.util.EnumSet;
import java.util.Set;

/**
 * Pizza
 *
 * @author rwei
 * @since 2024/4/21 21:40
 */
public abstract class Pizza {
    final Set<Topping> toppings;

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();  // item 50
    }

    public enum Topping {PEPPER, HAM}

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(topping);
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }
}
