package pl.krystiankaniowski.weatherapp.utils.converters;

import junit.framework.Assert;

import org.junit.Test;

public class TemperatureConverterTest {
    @Test
    public void toCelsius() throws Exception {
        Assert.assertEquals(-273.15, TemperatureConverter.toCelsius(0), 0.1);
        Assert.assertEquals(0.0, TemperatureConverter.toCelsius(273.15f), 0.1);
        Assert.assertEquals(700.0, TemperatureConverter.toCelsius(973.15f), 0.1);
    }

    @Test
    public void toFahrenheit() throws Exception {
        Assert.assertEquals(-459.7, TemperatureConverter.toFahrenheit(0), 0.1);
        Assert.assertEquals(-423.7, TemperatureConverter.toFahrenheit(20), 0.1);
        Assert.assertEquals(-369.7, TemperatureConverter.toFahrenheit(50), 0.1);
        Assert.assertEquals(-279.7, TemperatureConverter.toFahrenheit(100), 0.1);
        Assert.assertEquals(8.3, TemperatureConverter.toFahrenheit(260), 0.1);
        Assert.assertEquals(1160.3, TemperatureConverter.toFahrenheit(900), 0.1);
    }

}