package pl.krystiankaniowski.weatherapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import pl.krystiankaniowski.weatherapp.data.openweathermap.OpenWeatherMapService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    Retrofit providesOpenWeatherMapServiceRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(OpenWeatherMapService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

}
