package pl.krystiankaniowski.weatherapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import pl.krystiankaniowski.weatherapp.data.WeatherDataManager;

@Module
public class WeatherModule {

    @Provides
    public WeatherDataManager providesWeatherDataManager() {
        return new WeatherDataManager();
    }

}
