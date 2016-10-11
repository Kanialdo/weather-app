package pl.krystiankaniowski.weatherapp.data.places

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by kryst on 11.10.2016.
 */

class Data {


    @SerializedName("html_attributions")
    @Expose
    val attributions: Array<Object>? = null

    @SerializedName("results")
    @Expose
    val results: Array<Places>? = null

    @SerializedName("status")
    @Expose
    val status: String? = null

}

class Places {

    @SerializedName("photos")
    @Expose
    val places: Array<Result>? = null

}

class Result {

    @SerializedName("height")
    @Expose
    val height: Int? = null

    @SerializedName("width")
    @Expose
    val width: Int? = null

    @SerializedName("photo_reference")
    @Expose
    val reference: String? = null


}
