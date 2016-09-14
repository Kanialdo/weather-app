package pl.krystiankaniowski.weatherapp.utils.converters;

import junit.framework.Assert;

import org.junit.Test;

public class TemperatureConverterTest {

    @Test
    public void testToCelsius() throws Exception {
        Assert.assertEquals("-273,1 °C", TemperatureConverter.toCelsius(0));
        Assert.assertEquals("0,0 °C", TemperatureConverter.toCelsius(273.15f));
        Assert.assertEquals("700,0 °C", TemperatureConverter.toCelsius(973.15f));
    }

    @Test
    public void testToFahrenheit() throws Exception {
        Assert.assertEquals("-459,7 °F", TemperatureConverter.toFahrenheit(0));
        Assert.assertEquals("-423,7 °F", TemperatureConverter.toFahrenheit(20));
        Assert.assertEquals("-369,7 °F", TemperatureConverter.toFahrenheit(50));
        Assert.assertEquals("-279,7 °F", TemperatureConverter.toFahrenheit(100));
        Assert.assertEquals("8,3 °F", TemperatureConverter.toFahrenheit(260));
        Assert.assertEquals("1160,3 °F", TemperatureConverter.toFahrenheit(900));
    }

}