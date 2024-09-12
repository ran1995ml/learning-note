package com.ran.pattern.combination;

import java.util.Iterator;

/**
 * NullIterator
 *
 * @author rwei
 * @since 2024/9/8 18:35
 */
public class NullIterator implements Iterator<MenuComponent> {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public MenuComponent next() {
        return null;
    }
}
