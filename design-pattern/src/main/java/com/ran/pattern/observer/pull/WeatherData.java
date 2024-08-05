package com.ran.pattern.observer.pull;

import java.util.Observable;

/**
 * WeatherData
 *
 * @author rwei
 * @since 2024/8/2 10:15
 */
public class WeatherData extends Observable {
    private float temperature;

    private float humidity;

    private float pressure;

    public WeatherData() {
    }

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
