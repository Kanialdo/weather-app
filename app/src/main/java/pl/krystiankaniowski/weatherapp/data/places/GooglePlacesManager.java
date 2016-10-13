package pl.krystiankaniowski.weatherapp.data.places;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import pl.krystiankaniowski.weatherapp.eventbus.PhotoUrlReceived;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GooglePlacesManager {

    private static final String TAG = GooglePlacesManager.class.getSimpleName();

    private final GooglePlacesService service;

    public interface LinkObserver {

        void receiveLink(String link);

    }

    public GooglePlacesManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GooglePlacesService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        service = retrofit.create(GooglePlacesService.class);

    }

    public void findPlaces(final int cityId, final float lon, final float log) {

        Log.d(TAG, "findPlaces() called with: cityId = [" + cityId + "], lon = [" + lon + "], log = [" + log + "]");

        service.findPlaces(lon + ", " + log, 500, GooglePlacesService.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Data>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public final void onNext(Data data) {
                        Log.d(TAG, "onNext() called with: data = [" + data + "]");

                        Log.v(TAG, "status: " + data.getStatus());
                        String pattern = "https://maps.googleapis.com/maps/api/place/photo?photoreference=$1s?maxwidth=$2dkey=$3s";
                        Log.v(TAG, "results: " + data.getResults().length);
                        String ref = data.getResults()[0].getPlaces()[0].getReference();
                        Log.v(TAG, "ref: " + data.getResults()[0].getPlaces()[0].getReference());

                        String link = "https://maps.googleapis.com/maps/api/place/photo?photoreference=" + ref + "&maxwidth=1000&key=" + GooglePlacesService.API_KEY;

                        Log.d(TAG, link);

                        EventBus.getDefault().post(new PhotoUrlReceived(cityId, link));

                    }
                });

    }

}
