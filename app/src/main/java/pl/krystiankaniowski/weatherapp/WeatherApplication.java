package pl.krystiankaniowski.weatherapp;

import android.app.Application;

import pl.krystiankaniowski.weatherapp.dagger.components.ApplicationComponent;
import pl.krystiankaniowski.weatherapp.dagger.components.BaseComponent;
import pl.krystiankaniowski.weatherapp.dagger.components.DaggerApplicationComponent;
import pl.krystiankaniowski.weatherapp.dagger.components.DaggerBaseComponent;
import pl.krystiankaniowski.weatherapp.dagger.modules.ApplicationModule;

public class WeatherApplication extends Application {

    private ApplicationComponent applicationComponent;
    private BaseComponent baseComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        baseComponent = DaggerBaseComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
    }

    public BaseComponent getBaseComponent() {
        return baseComponent;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
