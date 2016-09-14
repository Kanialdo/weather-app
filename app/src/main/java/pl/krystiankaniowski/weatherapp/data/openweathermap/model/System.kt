package pl.krystiankaniowski.weatherapp.data.openweathermap.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class System {

    /** Internal parameter */
    @SerializedName("type")
    @Expose
    val type: Int? = null

    /** Internal parameter */
    @SerializedName("id")
    @Expose
    val id: Int? = null

    /** Internal parameter */
    @SerializedName("message")
    @Expose
    val message: Double? = null

    /** Country code (GB, JP etc.) */
    @SerializedName("country")
    @Expose
    val country: String? = null

    /** Sunrise time, unix, UTC */
    @SerializedName("sunrise")
    @Expose
    val sunrise: Int? = null

    /** Sunset time, unix, UTC */
    @SerializedName("sunset")
    @Expose
    val sunset: Int? = null

}
