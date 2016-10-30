package pl.krystiankaniowski.weatherapp.dagger.components;

import dagger.Component;
import pl.krystiankaniowski.weatherapp.dagger.modules.LocalModule;
import pl.krystiankaniowski.weatherapp.dagger.modules.NetworkModule;
import pl.krystiankaniowski.weatherapp.dagger.modules.WeatherModule;
import pl.krystiankaniowski.weatherapp.data.WeatherDataManager;
import pl.krystiankaniowski.weatherapp.settings.CacheManager;
import retrofit2.Retrofit;

@Component(modules = {LocalModule.class, NetworkModule.class, WeatherModule.class}, dependencies = ApplicationComponent.class)
public interface BaseComponent {

    Retrofit getRetrofit();

    WeatherDataManager getWeatherDataManager();

    CacheManager getCacheManager();

}
