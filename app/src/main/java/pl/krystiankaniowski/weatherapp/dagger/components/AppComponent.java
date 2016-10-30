package pl.krystiankaniowski.weatherapp.dagger.components;

import dagger.Component;
import pl.krystiankaniowski.weatherapp.dagger.modules.WeatherModule;
import pl.krystiankaniowski.weatherapp.view.modules.weather.WeatherPresenter;

@Component(modules = {WeatherModule.class})
public interface AppComponent {

    void inject(WeatherPresenter weatherPresenter);

}
