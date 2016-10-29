package pl.krystiankaniowski.weatherapp.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.krystiankaniowski.weatherapp.data.WeatherDataManager;

@Module
public class MainModule {

    @Provides
    @Singleton
    public WeatherDataManager providesWeaterDataManager() {
        return new WeatherDataManager();
    }

}
