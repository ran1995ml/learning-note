package com.ran.pattern.combination;

import java.util.Iterator;
import java.util.Stack;

/**
 * CompositeIterator
 *
 * @author rwei
 * @since 2024/9/8 18:25
 */
public class CompositeIterator implements Iterator<MenuComponent> {
    Stack<Iterator<MenuComponent>> stack = new Stack<>();

    public CompositeIterator(Iterator<MenuComponent> iterator) {
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        } else {
            Iterator<MenuComponent> iterator = stack.peek();
            if (iterator.hasNext()) {
                return true;
            } else {
                stack.pop();
                return hasNext();
            }
        }
    }

    @Override
    public MenuComponent next() {
        if (hasNext()) {
            Iterator<MenuComponent> iterator = stack.peek();
            MenuComponent menuComponent = iterator.next();
            stack.push(menuComponent.createIterator());
            return menuComponent;
        } else {
            return null;
        }
    }
}
