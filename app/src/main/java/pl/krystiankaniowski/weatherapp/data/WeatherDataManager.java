package pl.krystiankaniowski.weatherapp.data;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import pl.krystiankaniowski.weatherapp.data.openweathermap.OpenWeatherMapService;
import pl.krystiankaniowski.weatherapp.data.openweathermap.model.WeatherData;
import pl.krystiankaniowski.weatherapp.eventbus.WeatherMessage;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeatherDataManager {

    private static final String TAG = WeatherDataManager.class.getSimpleName();

    private final OpenWeatherMapService service;

    public WeatherDataManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OpenWeatherMapService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        service = retrofit.create(OpenWeatherMapService.class);

    }

    public void getWeather(final String city) {

        service.getCurrentData(city, OpenWeatherMapService.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherData>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public final void onNext(WeatherData response) {
                        EventBus.getDefault().post(new WeatherMessage(city, response.getMain().getTemp()));
                    }
                });

    }

}
