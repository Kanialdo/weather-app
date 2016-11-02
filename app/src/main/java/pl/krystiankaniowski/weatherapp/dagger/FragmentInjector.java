package pl.krystiankaniowski.weatherapp.dagger;

import pl.krystiankaniowski.weatherapp.WeatherApplication;
import pl.krystiankaniowski.weatherapp.dagger.components.DaggerFragmentInjectorComponent;
import pl.krystiankaniowski.weatherapp.view.modules.weather.WeatherDetailsFragment;

public class FragmentInjector {

    public static void inject(WeatherDetailsFragment weatherDetailsFragment) {
        DaggerFragmentInjectorComponent
                .builder()
                .baseComponent(WeatherApplication.getBaseComponent())
                .build()
                .inject(weatherDetailsFragment);
    }

}
