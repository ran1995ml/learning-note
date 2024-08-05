package com.ran.pattern.observer.pull;

import java.util.Observable;
import java.util.Observer;

/**
 * CurrentConditionDisplay
 *
 * @author rwei
 * @since 2024/8/2 10:39
 */
public class CurrentConditionDisplay implements Observer {
    private Observable observable;

    private float temperature;

    private float humidity;

    private float pressure;

    public CurrentConditionDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) observable;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            this.pressure = weatherData.getPressure();
            display();
        }
    }

    public void display() {
        System.out.printf("Current conditions: temperature: %s, humidity: %s, pressure: %s%n", this.temperature, this.humidity, this.pressure);
    }
}
