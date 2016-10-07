package pl.krystiankaniowski.weatherapp.view.modules.search;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import pl.krystiankaniowski.weatherapp.data.WeatherDataManager;
import pl.krystiankaniowski.weatherapp.eventbus.WeatherMessage;

/**
 * Created by kryst on 08.10.2016.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void requestWeather(String city) {
        new WeatherDataManager().doWeatherRequest(city);
    }

    @Override
    public void start() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WeatherMessage event) {
        view.onWeatherResponse(event);
    }

}
