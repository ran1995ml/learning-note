package com.ran.pattern.observer.pull;

/**
 * Main
 *
 * @author rwei
 * @since 2024/8/2 10:44
 */
public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        weatherData.setMeasurements(20, 30, 50);
        weatherData.notifyObservers();
    }
}
