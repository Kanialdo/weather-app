package pl.krystiankaniowski.weatherapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import pl.krystiankaniowski.weatherapp.settings.CacheManager;

@Module
public class LocalModule {

    @Provides
    public CacheManager providesCacheManager() {
        return CacheManager.getInstance();
    }


}
