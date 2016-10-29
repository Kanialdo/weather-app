package pl.krystiankaniowski.weatherapp.dagger;

import javax.inject.Singleton;

import dagger.Component;
import pl.krystiankaniowski.weatherapp.view.modules.weather.WeatherPresenter;

@Singleton
@Component(modules = {MainModule.class})
public interface AppComponent {

    void inject(WeatherPresenter weatherPresenter);

}
