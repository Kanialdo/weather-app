package pl.krystiankaniowski.weatherapp.data.openweathermap.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rain {

    /** Rain volume for the last 3 hours */
    @SerializedName("3h")
    @Expose
    val _3h: Int? = null

}
