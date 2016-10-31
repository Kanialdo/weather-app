package pl.krystiankaniowski.weatherapp.dagger;

import pl.krystiankaniowski.weatherapp.MainActivity;
import pl.krystiankaniowski.weatherapp.WeatherApplication;
import pl.krystiankaniowski.weatherapp.dagger.components.DaggerActivityInjectorComponent;

/**
 * Created by kryst on 30.10.2016.
 */

public class ActivityInjector {

    public static void inject(MainActivity mainActivity) {
        DaggerActivityInjectorComponent
                .builder()
                .baseComponent(WeatherApplication.getBaseComponent())
                .build()
                .inject(mainActivity);
    }

}
