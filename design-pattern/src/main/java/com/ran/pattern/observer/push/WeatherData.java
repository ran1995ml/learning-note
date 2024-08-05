package com.ran.pattern.observer.push;

import java.util.ArrayList;
import java.util.List;

/**
 * WeatherData
 *
 * @author rwei
 * @since 2024/8/1 14:24
 */
public class WeatherData implements Subject {
    private final List<Observer> observers;
    private float temp;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.temp, this.humidity, this.pressure);
        }
    }

    public void setMeasurement(float temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }

    public void measurementChanged() {
        notifyObservers();
    }


}
