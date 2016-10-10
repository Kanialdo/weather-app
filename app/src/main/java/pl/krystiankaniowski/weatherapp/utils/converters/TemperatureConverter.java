package pl.krystiankaniowski.weatherapp.utils.converters;

public class TemperatureConverter {

    public static float toCelsius(float kelvinTemperature) {
        return kelvinTemperature - 273.15f;
    }

    public static float toFahrenheit(float kelvinTemperature) {
        return (kelvinTemperature - 273.15f) * 1.8f + 32.0f;
    }

}