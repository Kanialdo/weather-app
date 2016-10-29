package pl.krystiankaniowski.weatherapp.view.modules.weather;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.krystiankaniowski.weatherapp.dagger.DaggerAppComponent;
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

    public WeatherPresenter(WeatherContract.View view, int cityId) {

        this.view = view;
        this.cityId = cityId;

        city = CacheManager.getInstance().getCity(cityId);

        assert city != null;

        subscriptions = new CompositeSubscription();

        view.setPresenter(this);

        DaggerAppComponent.builder().build().inject(this);

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

        EventBus.getDefault().register(this);

    }

    @Override
    public void unsubscribe() {
        viewReady = false;
        EventBus.getDefault().unregister(this);
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
        CacheManager.getInstance().saveCity(city);
        EventBus.getDefault().post(new FavouritesChanged());
    }

    @Override
    public void unsetFavourite() {
        city.setFavourite(false);
        CacheManager.getInstance().saveCity(city);
        EventBus.getDefault().post(new FavouritesChanged());
    }

}
