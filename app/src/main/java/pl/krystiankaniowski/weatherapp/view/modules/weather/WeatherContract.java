package pl.krystiankaniowski.weatherapp.view.modules.weather;

import pl.krystiankaniowski.weatherapp.mvp.BasePresenter;
import pl.krystiankaniowski.weatherapp.mvp.BaseView;

/**
 * Created by kryst on 08.10.2016.
 */

public interface WeatherContract {

    interface View extends BaseView<Presenter> {

        void setCityName(String cityName);

        void setTemperature(String temperature);

        void setWeather(String weather);

        void setPressure(String preassure);

        void setHumidity(String humidity);

        void setWind(String wind);

        void setError(String error);

    }

    interface Presenter extends BasePresenter {

        void requestWeather(int cityId);

    }

}
