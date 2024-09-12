package com.ran.pattern.combination;

import java.util.Iterator;

/**
 * MenuItem
 *
 * @author rwei
 * @since 2024/9/8 16:46
 */
public class MenuItem extends MenuComponent {
    private String name;

    private String description;

    private double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public Iterator<MenuComponent> createIterator() {
        return new NullIterator();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void print() {
        System.out.printf("name: %s, description: %s, price: %s%n", this.name, this.description, this.price);
    }
}
