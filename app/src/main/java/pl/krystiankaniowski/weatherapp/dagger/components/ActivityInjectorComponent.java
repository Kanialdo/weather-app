package pl.krystiankaniowski.weatherapp.dagger.components;

import dagger.Component;
import pl.krystiankaniowski.weatherapp.MainActivity;
import pl.krystiankaniowski.weatherapp.dagger.modules.SettingsModule;

@Component(modules = {SettingsModule.class}, dependencies = ApplicationComponent.class)
public interface ActivityInjectorComponent {

    void inject(MainActivity activity);

}