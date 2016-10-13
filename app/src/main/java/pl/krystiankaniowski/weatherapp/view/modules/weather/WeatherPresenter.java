package pl.krystiankaniowski.weatherapp.view.modules.weather;

import pl.krystiankaniowski.weatherapp.data.WeatherDataManager;
import pl.krystiankaniowski.weatherapp.data.cities.City;
import pl.krystiankaniowski.weatherapp.data.openweathermap.model.WeatherData;
import pl.krystiankaniowski.weatherapp.data.places.GooglePlacesManager;
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

public class WeatherPresenter implements WeatherContract.Presenter {

    private WeatherContract.View view;
    private CompositeSubscription subscriptions;

    private int cityId;
    private City city;
    private boolean favourite;

    public WeatherPresenter(WeatherContract.View view, int cityId) {

        this.view = view;
        this.cityId = cityId;

        city = CacheManager.getInstance().getCity(cityId);

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
                                        view.setErrorView(e.getMessage());
                                    }

                                    @Override
                                    public final void onNext(WeatherData data) {

                                        city = new City(data.getId(), data.getCityName(), data.getCoordinates().getLongitude(), data.getCoordinates().getLatitude(), "");

                                        view.setCityName(data.getCityName());
                                        view.setHumidity(DataFormatter.formatPercentage(data.getMain().getHumidity()));
                                        view.setTemperature(DataFormatter.formatCelcius(TemperatureConverter.toCelsius(data.getMain().getTemperature())));
                                        view.setWeather(data.getWeather().get(0).getDescription());
                                        view.setPressure(DataFormatter.formatPa(data.getMain().getPressure()));
                                        new GooglePlacesManager().findPlaces(data.getCoordinates().getLatitude(), data.getCoordinates().getLongitude(), link -> view.setPhotoUrl(link));
                                    }
                                }
                        )
        );

    }

    @Override
    public void subscribe() {

        if (city != null) {
            view.setCityName(city.getName());
        }

        // EventBus.getDefault().register(this);
    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
        // EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean isFavourite(int cityId) {
        return favourite;
    }

    @Override
    public void setFavourite(int cityId) {
        favourite = true;
        CacheManager.getInstance().saveCity(city);
    }

    @Override
    public void unsetFavourite(int cityId) {
        favourite = false;
    }

}
