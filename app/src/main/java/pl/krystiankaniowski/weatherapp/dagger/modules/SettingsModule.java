package pl.krystiankaniowski.weatherapp.dagger.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kryst on 30.10.2016.
 */

@Module
public class SettingsModule {

    @Provides
    public SharedPreferences providesDefaultSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

}
