package pl.krystiankaniowski.weatherapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import pl.krystiankaniowski.weatherapp.dagger.scope.ApplicationScope;
import pl.krystiankaniowski.weatherapp.data.WeatherDataManager;
import pl.krystiankaniowski.weatherapp.settings.CacheManager;

/**
 * Created by kryst on 31.10.2016.
 */

@Module
public class ManagersModule {

    @Provides
    @ApplicationScope
    public WeatherDataManager providesWeatherDataManager() {
        return new WeatherDataManager();
    }

    @Provides
    @ApplicationScope
    public CacheManager providesCacheManager() {
        return CacheManager.getInstance();
    }

}
