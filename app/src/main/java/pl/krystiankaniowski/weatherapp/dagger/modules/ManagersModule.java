package pl.krystiankaniowski.weatherapp.dagger.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import pl.krystiankaniowski.weatherapp.dagger.scope.ApplicationScope;
import pl.krystiankaniowski.weatherapp.data.WeatherDataManager;
import pl.krystiankaniowski.weatherapp.data.cities.CitiesManager;
import pl.krystiankaniowski.weatherapp.settings.CacheManager;

@Module
public class ManagersModule {

    @Provides
    @ApplicationScope
    public WeatherDataManager providesWeatherDataManager() {
        return new WeatherDataManager();
    }

    @Provides
    @ApplicationScope
    public CacheManager providesCacheManager(Application application) {
        CacheManager.init(application);
        return CacheManager.getInstance();
    }

    @Provides
    @ApplicationScope
    public CitiesManager providesCitiesManager() {
        return new CitiesManager();
    }

}
