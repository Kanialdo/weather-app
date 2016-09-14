package pl.krystiankaniowski.weatherapp.data.openweathermap.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class WeatherData {

    @SerializedName("coord")
    @Expose
    val coordinates: Coordinates? = null

    @SerializedName("weather")
    @Expose
    val weather: List<Weather> = ArrayList()

    /**  Internal parameter */
    @SerializedName("base")
    @Expose
    val base: String? = null

    @SerializedName("main")
    @Expose
    val main: Main? = null

    @SerializedName("wind")
    @Expose
    val wind: Wind? = null

    @SerializedName("clouds")
    @Expose
    val clouds: Clouds? = null

    @SerializedName("rain")
    @Expose
    val rain: Rain? = null

    @SerializedName("snow")
    @Expose
    val snow: Snow? = null

    /** Time of data calculation, unix, UTC */
    @SerializedName("dt")
    @Expose
    val dataCalculation: Int? = null

    @SerializedName("sys")
    @Expose
    val systemInfo: System? = null

    /** City ID */
    @SerializedName("id")
    @Expose
    val id: Int? = null

    /** City name */
    @SerializedName("name")
    @Expose
    val cityName: String? = null

    /** Internal parameter */
    @SerializedName("cod")
    @Expose
    val cod: Int? = null

}
