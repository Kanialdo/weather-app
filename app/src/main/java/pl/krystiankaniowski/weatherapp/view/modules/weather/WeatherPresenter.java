package pl.krystiankaniowski.weatherapp.view.modules.weather;

import pl.krystiankaniowski.weatherapp.data.WeatherDataManager;
import pl.krystiankaniowski.weatherapp.data.openweathermap.model.WeatherData;
import pl.krystiankaniowski.weatherapp.utils.converters.TemperatureConverter;
import pl.krystiankaniowski.weatherapp.utils.formatters.DataFormatter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kryst on 08.10.2016.
 */

public class WeatherPresenter implements WeatherContract.Presenter {

    private WeatherContract.View view;
    private CompositeSubscription subscriptions;

    public WeatherPresenter(WeatherContract.View view) {

        this.view = view;

        subscriptions = new CompositeSubscription();

        view.setPresenter(this);

    }

    @Override
    public void requestWeather(int cityId) {

        subscriptions.add(
                new WeatherDataManager().getWeather(cityId)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Subscriber<WeatherData>() {
                                    @Override
                                    public final void onCompleted() {
                                        // do nothing
                                    }

                                    @Override
                                    public final void onError(Throwable e) {
                                        view.setError(e.getMessage());
                                    }

                                    @Override
                                    public final void onNext(WeatherData data) {
                                        view.setCityName(data.getCityName());
                                        view.setHumidity(String.valueOf(data.getMain().getHumidity()));
                                        view.setTemperature(TemperatureConverter.toCelsius(data.getMain().getTemperature()));
                                        view.setWeather(data.getWeather().get(0).getDescription());
                                        view.setPressure(DataFormatter.formatPa(data.getMain().getPressure()));
                                    }
                                }
                        )
        );

    }

    @Override
    public void subscribe() {
        // EventBus.getDefault().register(this);
    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
        // EventBus.getDefault().unregister(this);
    }

}
