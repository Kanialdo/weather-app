package pl.krystiankaniowski.weatherapp.dagger.components;

import android.content.SharedPreferences;

import dagger.Component;
import okhttp3.OkHttpClient;
import pl.krystiankaniowski.weatherapp.dagger.modules.LocalModule;
import pl.krystiankaniowski.weatherapp.dagger.modules.ManagersModule;
import pl.krystiankaniowski.weatherapp.dagger.modules.NetworkModule;
import pl.krystiankaniowski.weatherapp.dagger.scope.ApplicationScope;
import pl.krystiankaniowski.weatherapp.data.WeatherDataManager;
import pl.krystiankaniowski.weatherapp.settings.CacheManager;
import pl.krystiankaniowski.weatherapp.view.modules.weather.WeatherPresenter;
import retrofit2.Retrofit;

@ApplicationScope
@Component(modules = {LocalModule.class, NetworkModule.class, ManagersModule.class}, dependencies = ApplicationComponent.class)
public interface BaseComponent {

    SharedPreferences getSharedPreferences();

    OkHttpClient getOkHTTP();

    Retrofit getRetrofit();

    WeatherDataManager getWeatherDataManager();

    CacheManager getCacheManager();

    void inject(WeatherDataManager weatherDataManager);

    void inject(WeatherPresenter presenter);

}
