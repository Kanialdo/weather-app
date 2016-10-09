package pl.krystiankaniowski.weatherapp.view.modules.weather;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import pl.krystiankaniowski.weatherapp.data.WeatherDataManager;
import pl.krystiankaniowski.weatherapp.eventbus.WeatherMessage;
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

        new WeatherDataManager().doWeatherRequest(cityId);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherReceive(WeatherMessage message){
        view.setWeatherDetails(null);
    }

    @Override
    public void subscribe() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
        EventBus.getDefault().unregister(this);
    }

}
