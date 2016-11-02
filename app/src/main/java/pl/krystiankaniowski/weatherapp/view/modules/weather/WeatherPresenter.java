package pl.krystiankaniowski.weatherapp.view.modules.weather;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.krystiankaniowski.weatherapp.WeatherApplication;
import pl.krystiankaniowski.weatherapp.data.WeatherDataManager;
import pl.krystiankaniowski.weatherapp.data.cities.City;
import pl.krystiankaniowski.weatherapp.data.openweathermap.model.WeatherData;
import pl.krystiankaniowski.weatherapp.eventbus.FavouritesChanged;
import pl.krystiankaniowski.weatherapp.eventbus.PhotoUrlReceived;
import pl.krystiankaniowski.weatherapp.settings.CacheManager;
import pl.krystiankaniowski.weatherapp.utils.converters.TemperatureConverter;
import pl.krystiankaniowski.weatherapp.utils.formatters.DataFormatter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kryst on 08.10.2016.
 */

@Singleton
public class WeatherPresenter implements WeatherContract.Presenter {

    private WeatherContract.View view;
    private CompositeSubscription subscriptions;

    private int cityId;
    private City city;

    private boolean viewReady;

    @Inject
    WeatherDataManager weatherManager;

    @Inject
    CacheManager cacheManager;

    @Inject
    EventBus eventBus;

    public WeatherPresenter(WeatherContract.View view, int cityId) {

        WeatherApplication.getBaseComponent().inject(this);

        this.view = view;
        this.cityId = cityId;

        city = cacheManager.getCity(cityId);

        assert city != null;

        subscriptions = new CompositeSubscription();

        view.setPresenter(this);

    }

    @Override
    public void requestWeather() {

        subscriptions.add(
                weatherManager.getWeather(cityId)
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
                                        view.setErrorView(e.getMessage());
                                    }

                                    @Override
                                    public final void onNext(WeatherData data) {
                                        view.setCityName(data.getCityName());
                                        view.setHumidity(DataFormatter.formatPercentage(data.getMain().getHumidity()));
                                        view.setTemperature(DataFormatter.formatCelcius(TemperatureConverter.toCelsius(data.getMain().getTemperature())));
                                        view.setWeather(data.getWeather().get(0).getDescription());
                                        view.setPressure(DataFormatter.formatPa(data.getMain().getPressure()));
                                    }
                                }
                        )
        );

    }

    @Override
    public void subscribe() {

        viewReady = true;

        if (city != null) {
            view.setCityName(city.getName());
            if (city.getPhotoUrl() != null) {
                view.setPhotoUrl(city.getPhotoUrl());
            }
        }

        eventBus.register(this);

    }

    @Override
    public void unsubscribe() {
        viewReady = false;
        eventBus.unregister(this);
        subscriptions.clear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCityPhotoUrlFounded(PhotoUrlReceived message) {
        city.setPhotoUrl(message.getLink());
        if (viewReady) {
            view.setPhotoUrl(city.getPhotoUrl());
        }
    }

    @Override
    public boolean isFavourite() {
        return city.isFavourite();
    }

    @Override
    public void setFavourite() {
        city.setFavourite(true);
        cacheManager.saveCity(city);
        eventBus.post(new FavouritesChanged());
    }

    @Override
    public void unsetFavourite() {
        city.setFavourite(false);
        cacheManager.saveCity(city);
        eventBus.post(new FavouritesChanged());
    }

}
