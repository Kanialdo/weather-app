package pl.krystiankaniowski.weatherapp.utils.formatters;

public class DataFormatter {

    public static String formatPa(float value) {
        return String.valueOf(Math.round(value * 10) / 10f) + " hPa";
    }

}
