package pl.krystiankaniowski.weatherapp.data.openweathermap.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coordinates {

    /** City geo location, longitude */
    @SerializedName("lon")
    @Expose
    val longitude: Float? = null

    /** City geo location, latitude */
    @SerializedName("lat")
    @Expose
    val latitude: Float? = null

}
