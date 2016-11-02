package pl.krystiankaniowski.weatherapp.dagger.components;

import dagger.Component;
import pl.krystiankaniowski.weatherapp.dagger.scope.FragmentScope;
import pl.krystiankaniowski.weatherapp.view.modules.weather.WeatherDetailsFragment;

@FragmentScope
@Component(dependencies = BaseComponent.class)
public interface FragmentInjectorComponent {

    void inject(WeatherDetailsFragment weatherDetailsFragment);

}