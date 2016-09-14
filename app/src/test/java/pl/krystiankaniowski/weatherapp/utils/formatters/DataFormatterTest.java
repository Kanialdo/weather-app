package pl.krystiankaniowski.weatherapp.utils.formatters;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by kryst on 14.09.2016.
 */
public class DataFormatterTest {

    @Test
    public void testFormatPa() throws Exception {
        Assert.assertEquals("10.0 hPa", DataFormatter.formatPa(10f));
        Assert.assertEquals("10.4 hPa", DataFormatter.formatPa(10.412f));
        Assert.assertEquals("10.0 hPa", DataFormatter.formatPa(9.9999f));
    }
}