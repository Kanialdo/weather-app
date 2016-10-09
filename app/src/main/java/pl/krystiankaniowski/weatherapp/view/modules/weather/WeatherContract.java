package pl.krystiankaniowski.weatherapp.view.modules.weather;

import pl.krystiankaniowski.weatherapp.eventbus.WeatherMessage;
import pl.krystiankaniowski.weatherapp.mvp.BasePresenter;
import pl.krystiankaniowski.weatherapp.mvp.BaseView;

/**
 * Created by kryst on 08.10.2016.
 */

public interface WeatherContract {

    interface View extends BaseView<Presenter> {

        void onWeatherResponse(WeatherMessage event);

        void onWeatherRequestError();

    }

    interface Presenter extends BasePresenter {

        void requestWeather(String city);

    }

}
