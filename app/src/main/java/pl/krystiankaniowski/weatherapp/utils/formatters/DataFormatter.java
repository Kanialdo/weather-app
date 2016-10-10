package pl.krystiankaniowski.weatherapp.utils.formatters;

import java.text.DecimalFormat;

public class DataFormatter {

    private static DecimalFormat format = new DecimalFormat("###0.0");

    public static String formatPa(float value) {
        return format.format(value) + " hPa";
    }

    public static String formatCelcius(float value) {
        return format.format(value) + " °C";
    }

    public static String formatFahrenheit(float value) {
        return format.format(value) + " °F";
    }

    public static String formatPercentage(float value) {
        return format.format(value) + " %";
    }

}
