package pl.krystiankaniowski.weatherapp.view.modules.weather;

import pl.krystiankaniowski.weatherapp.data.openweathermap.model.Weather;
import pl.krystiankaniowski.weatherapp.mvp.BasePresenter;
import pl.krystiankaniowski.weatherapp.mvp.BaseView;

/**
 * Created by kryst on 08.10.2016.
 */

public interface WeatherContract {

    interface View extends BaseView<Presenter> {

        void setWeatherDetails(Weather weather);

        void setError(String error);

    }

    interface Presenter extends BasePresenter {

        void requestWeather(int cityId);

    }

}
