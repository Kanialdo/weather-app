package pl.krystiankaniowski.weatherapp.data.openweathermap;

import pl.krystiankaniowski.weatherapp.data.openweathermap.model.WeatherData;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface OpenWeatherMapService {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    String API_KEY = "dacd3a49be6efee4c375a8465e244d69";

    @GET("weather")
    Observable<WeatherData> getCurrentData(@Query("id") int cityId, @Query("APPID") String api);

}
