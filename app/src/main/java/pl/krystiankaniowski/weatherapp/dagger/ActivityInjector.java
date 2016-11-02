package pl.krystiankaniowski.weatherapp.dagger;

import pl.krystiankaniowski.weatherapp.MainActivity;
import pl.krystiankaniowski.weatherapp.WeatherApplication;
import pl.krystiankaniowski.weatherapp.dagger.components.DaggerActivityInjectorComponent;

public class ActivityInjector {

    public static void inject(MainActivity mainActivity) {
        DaggerActivityInjectorComponent
                .builder()
                .baseComponent(WeatherApplication.getBaseComponent())
                .build()
                .inject(mainActivity);
    }

}
