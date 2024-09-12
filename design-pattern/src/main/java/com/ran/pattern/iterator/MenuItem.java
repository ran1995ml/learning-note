<<<<<<< HEAD
package com.ran.pattern.iterator;

/**
 * MenuItem
 *
 * @author rwei
 * @since 2024/8/20 13:45
 */
public class MenuItem {
    private String name;

    private String description;
    private boolean vegetarian;
    private Double price;

    public MenuItem(String name, String description, boolean vegetarian, Double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", vegetarian=" + vegetarian +
                ", price=" + price +
                '}';
    }
=======
package com.ran.pattern.iterator;/** 
 * MenuItem
 * 
 * @author rwei
 * @since 2024/8/20 13:45
 */public class MenuItem {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
