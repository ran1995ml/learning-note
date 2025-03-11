package com.ran.algorithm.stack;

import java.util.Iterator;

/**
 * ResizeStack
 * 动态扩容栈，容量不够扩容为之前的两倍
 * 使用率25%缩容为之前的一半
 * @author rwei
 * @since 2025/2/25 22:40
 */
public class ResizeStack<T> implements Iterable<T> {
    private T[] a;

    private int N;

    public ResizeStack(int capacity) {
        a = (T[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(T item) {
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    public T pop() {
        T item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }

    private void resize(int max) {
        T[] temp = (T[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return a[--i];
        }
    }
}
