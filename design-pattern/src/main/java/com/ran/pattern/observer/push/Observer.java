package com.ran.pattern.observer.push;

/**
 * Observer
 *
 * @author rwei
 * @since 2024/8/1 17:35
 */
public interface Observer {
    void update(float temp, float humidity, float pressure);
}
