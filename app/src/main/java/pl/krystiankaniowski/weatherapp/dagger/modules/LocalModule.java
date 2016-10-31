package pl.krystiankaniowski.weatherapp.dagger.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;
import pl.krystiankaniowski.weatherapp.dagger.scope.ApplicationScope;

@Module
public class LocalModule {

    @Provides
    @ApplicationScope
    public SharedPreferences providesDefaultSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

}
