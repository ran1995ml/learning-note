package com.ran.pattern.observer.push;

/**
 * Subject
 *
 * @author rwei
 * @since 2024/8/1 17:34
 */
public interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
