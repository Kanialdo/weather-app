package pl.krystiankaniowski.weatherapp.utils.formatters;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by kryst on 14.09.2016.
 */
public class DataFormatterTest {

    @Test
    public void formatCelcius() throws Exception {
        Assert.assertEquals("10,0 °C", DataFormatter.formatCelcius(10f));
    }

    @Test
    public void formatFahrenheit() throws Exception {
        Assert.assertEquals("10,0 °F", DataFormatter.formatFahrenheit(10f));
    }

    @Test
    public void formatPercentage() throws Exception {
        Assert.assertEquals("10,0 %", DataFormatter.formatPercentage(10f));
    }

    @Test
    public void formatPa() throws Exception {
        Assert.assertEquals("10,0 hPa", DataFormatter.formatPa(10f));
        Assert.assertEquals("10,4 hPa", DataFormatter.formatPa(10.412f));
        Assert.assertEquals("10,0 hPa", DataFormatter.formatPa(9.9999f));
    }

}