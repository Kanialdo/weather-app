package pl.krystiankaniowski.weatherapp.data.openweathermap.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main {

    /** Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit. */
    @SerializedName("temp")
    @Expose
    val temperature: Double? = null

    /** Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa */
    @SerializedName("pressure")
    @Expose
    val pressure: Double? = null

    /** Humidity, % */
    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null

    /** Minimum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit. */
    @SerializedName("temp_min")
    @Expose
    val temperatureMin: Double? = null

    /** Maximum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit. */
    @SerializedName("temp_max")
    @Expose
    val temperatureMax: Double? = null

    /** Atmospheric pressure on the sea level, hPa */
    @SerializedName("sea_level")
    @Expose
    private val seaLevelPressure: Double? = null

    /** Atmospheric pressure on the ground level, hPa */
    @SerializedName("grnd_level")
    @Expose
    private val groundLevelPressure: Double? = null

}
