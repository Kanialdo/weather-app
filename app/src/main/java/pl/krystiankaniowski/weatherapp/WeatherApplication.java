package pl.krystiankaniowski.weatherapp;

import android.app.Application;

import pl.krystiankaniowski.weatherapp.dagger.components.ApplicationComponent;
import pl.krystiankaniowski.weatherapp.dagger.components.BaseComponent;
import pl.krystiankaniowski.weatherapp.dagger.components.DaggerApplicationComponent;
import pl.krystiankaniowski.weatherapp.dagger.components.DaggerBaseComponent;
import pl.krystiankaniowski.weatherapp.dagger.modules.ApplicationModule;
import pl.krystiankaniowski.weatherapp.dagger.modules.LocalModule;
import pl.krystiankaniowski.weatherapp.dagger.modules.ManagersModule;
import pl.krystiankaniowski.weatherapp.dagger.modules.NetworkModule;
import pl.krystiankaniowski.weatherapp.data.openweathermap.OpenWeatherMapService;

public class WeatherApplication extends Application {

    private static ApplicationComponent applicationComponent;
    private static BaseComponent baseComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        baseComponent = DaggerBaseComponent.builder()
                .applicationComponent(applicationComponent)
                .localModule(new LocalModule())
                .networkModule(new NetworkModule(OpenWeatherMapService.BASE_URL))
                .managersModule(new ManagersModule())
                .build();

    }

    public static BaseComponent getBaseComponent() {
        return baseComponent;
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
