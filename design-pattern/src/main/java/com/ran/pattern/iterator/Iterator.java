package com.ran.pattern.iterator;

/**
 * Iterator
 *
 * @author rwei
 * @since 2024/8/20 14:14
 */
public interface Iterator<T> {
    boolean hasNext();

    T next();
}
