
package pl.krystiankaniowski.weatherapp.data.openweathermap.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    @Expose
    private Double speed;

    @SerializedName("deg")
    @Expose
    private Double deg;

    public Double getDeg() {
        return deg;
    }

    public Double getSpeed() {
        return speed;
    }

}
