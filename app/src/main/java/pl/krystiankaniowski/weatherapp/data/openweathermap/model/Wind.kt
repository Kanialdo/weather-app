package pl.krystiankaniowski.weatherapp.data.openweathermap.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind {

    /** Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour. */
    @SerializedName("speed")
    @Expose
    val speed: Double? = null

    /** Wind direction, degrees (meteorological) */
    @SerializedName("deg")
    @Expose
    val degrees: Double? = null

}
