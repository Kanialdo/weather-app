package pl.krystiankaniowski.weatherapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import pl.krystiankaniowski.weatherapp.dagger.scope.ApplicationScope;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @ApplicationScope
    OkHttpClient providesOkHTTP() {
        return new OkHttpClient();
    }

    @Provides
    @ApplicationScope
    Retrofit providesRetrofit(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

}
