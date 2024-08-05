package com.ran.pattern.observer.push;

/**
 * Main
 * 观察者push模式，当有数据变化，主题主动推送数据
 *
 * @author rwei
 * @since 2024/8/2 09:57
 */
public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        weatherData.setMeasurement(80, 50, 30);
        weatherData.registerObserver(currentConditionDisplay);
    }
}
