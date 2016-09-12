package pl.krystiankaniowski.weatherapp.data;

import android.util.Log;

import java.io.IOException;

import pl.krystiankaniowski.weatherapp.data.openweathermap.OpenWeatherMapService;
import pl.krystiankaniowski.weatherapp.data.openweathermap.model.WeatherData;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherDataManager {

    private static final String TAG = WeatherDataManager.class.getSimpleName();

    private final OpenWeatherMapService service;

    public WeatherDataManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OpenWeatherMapService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(OpenWeatherMapService.class);

    }

    public void getWeather(final String city) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Call<WeatherData> call = service.getCurrentData(city, OpenWeatherMapService.API_KEY);
                    Log.v(TAG, "request: " + call.request().url());
                    Response<WeatherData> response = call.execute();
                    Log.v(TAG, "response: " + response.body());
                    Log.v(TAG, "Temperature: " + response.body().getMain().getTemp());
                } catch (IOException e) {

                }
            }
        }).start();

    }

}
