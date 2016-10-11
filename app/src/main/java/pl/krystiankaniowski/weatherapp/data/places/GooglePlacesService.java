package pl.krystiankaniowski.weatherapp.data.places;

import pl.krystiankaniowski.weatherapp.data.openweathermap.model.WeatherData;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import static pl.krystiankaniowski.weatherapp.BuildConfig.GOOGLE_PLACES_API;

/**
 * Created by kryst on 11.10.2016.
 */

public interface GooglePlacesService {

    String BASE_URL = "https://maps.googleapis.com/maps/api/place/";

    String API_KEY = GOOGLE_PLACES_API;

    @GET("nearbysearch/json")
    Observable<Data> findPlaces(@Query("location") String location, @Query("radius") int radius, @Query("key") String api);

    @GET("photo")
    Observable<WeatherData> getPhoto(@Query("photoreference") int photoReference, @Query("maxwidth") float[] maxWidth, @Query("key") String api);

}
