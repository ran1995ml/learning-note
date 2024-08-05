package com.ran.pattern.observer.push;

/**
 * CurrentConditionDisplay
 *
 * @author rwei
 * @since 2024/8/1 17:52
 */
public class CurrentConditionDisplay implements Observer, Display {
    private Subject subject;

    private float temp;

    private float humidity;

    private float pressure;

    public CurrentConditionDisplay(Subject subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.printf("Current conditions: temp: %s, humidity: %s, pressure: %s%n", this.temp, this.humidity, this.pressure);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }
}
