package pl.krystiankaniowski.weatherapp.data;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import pl.krystiankaniowski.weatherapp.WeatherApplication;
import pl.krystiankaniowski.weatherapp.data.openweathermap.OpenWeatherMapService;
import pl.krystiankaniowski.weatherapp.data.openweathermap.model.WeatherData;
import pl.krystiankaniowski.weatherapp.eventbus.WeatherMessage;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeatherDataManager {

    private static final String TAG = WeatherDataManager.class.getSimpleName();

    private final OpenWeatherMapService service;

    @Inject
    Retrofit retrofit;

    public WeatherDataManager() {
        WeatherApplication.getBaseComponent().inject(this);
        service = retrofit.create(OpenWeatherMapService.class);
    }

    public void doWeatherRequest(final int cityId) {

        service.getCurrentData(cityId, OpenWeatherMapService.API_KEY)
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
                    public final void onNext(WeatherData data) {
                        EventBus.getDefault().post(new WeatherMessage("", data.getMain().getTemperature()));
                    }
                });

    }

    public Observable<WeatherData> getWeather(final int cityId) {
        return service.getCurrentData(cityId, OpenWeatherMapService.API_KEY);
    }

}
