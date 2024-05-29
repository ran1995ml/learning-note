package com.ran.java.effective.item_30;

import java.util.HashSet;
import java.util.Set;

/**
 * UnionSet
 *
 * @author rwei
 * @since 2024/5/14 11:04
 */
public class UnionSet {

    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }
}
