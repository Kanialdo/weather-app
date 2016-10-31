package pl.krystiankaniowski.weatherapp.dagger.components;

import dagger.Component;
import pl.krystiankaniowski.weatherapp.MainActivity;
import pl.krystiankaniowski.weatherapp.dagger.scope.ActivityScope;

@ActivityScope
@Component(dependencies = BaseComponent.class)
public interface ActivityInjectorComponent {

    void inject(MainActivity activity);

}