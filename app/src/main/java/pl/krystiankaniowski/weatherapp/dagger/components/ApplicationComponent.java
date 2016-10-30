package pl.krystiankaniowski.weatherapp.dagger.components;

import android.app.Application;

import dagger.Component;
import pl.krystiankaniowski.weatherapp.dagger.modules.ApplicationModule;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    // Exposes Application to any component which depends on this
    Application getApplication();

}
