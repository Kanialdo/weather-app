package pl.krystiankaniowski.weatherapp.data.cities;

import com.google.gson.annotations.SerializedName;

import pl.krystiankaniowski.weatherapp.adapter.ViewElement;

import static pl.krystiankaniowski.weatherapp.adapter.ViewElementType.RESULT_CITY_ITEM;

/**
 * Created by kryst on 13.10.2016.
 */

public class City implements ViewElement {

    @SerializedName("id")
    int id;

    @SerializedName("name")
    private String name;

    @SerializedName("longitude")
    private Float longitude;

    @SerializedName("latitude")
    private Float latitude;

    @SerializedName("country_code")
    private String countryCode;

    @SerializedName("photo_url")
    private String photoUrl;

    public City(int id, String name, Float longitude, Float latitude, String countryCode) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.countryCode = countryCode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public int getViewType() {
        return RESULT_CITY_ITEM.ordinal();
    }

}
