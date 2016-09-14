package pl.krystiankaniowski.weatherapp.data.openweathermap.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather {

    /** Weather condition id */
    @SerializedName("id")
    @Expose
    val id: Int? = null

    /** Group of weather parameters (Rain, Snow, Extreme etc.) */
    @SerializedName("main")
    @Expose
    val main: String? = null

    /** Weather condition within the group */
    @SerializedName("description")
    @Expose
    val description: String? = null

    /** Weather icon id */
    @SerializedName("icon")
    @Expose
    val icon: String? = null

}
