package pl.krystiankaniowski.weatherapp.data.openweathermap.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Clouds {

    /**Cloudiness, %*/
    @SerializedName("all")
    @Expose
    val all: Int? = null

}
