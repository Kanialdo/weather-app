package pl.krystiankaniowski.weatherapp.dagger.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private static Application application;

    public ApplicationModule(Application application) {
        ApplicationModule.application = application;
    }

    @Provides
    Application providesApplication() {
        return application;
    }

}
