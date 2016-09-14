package pl.krystiankaniowski.weatherapp.utils.converters;

import java.text.DecimalFormat;

public class TemperatureConverter {

    private static DecimalFormat decimalFormat = new DecimalFormat("###0.0");

    public static String toCelsius(float kelvinTemperature) {
        return decimalFormat.format(kelvinTemperature - 273.15f) + " °C";
    }

    public static String toFahrenheit(float kelvinTemperature) {
        return decimalFormat.format((kelvinTemperature - 273.15f) * 1.8f + 32.0f) + " °F";
    }

}