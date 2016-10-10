package pl.krystiankaniowski.weatherapp.view.modules.search;

import android.content.Context;

import java.util.List;

import pl.krystiankaniowski.weatherapp.data.cities.CitiesManager;
import pl.krystiankaniowski.weatherapp.data.cities.City;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kryst on 08.10.2016.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;
    private CompositeSubscription subscriptions;

    public SearchPresenter(SearchContract.View view) {

        this.view = view;

        subscriptions = new CompositeSubscription();

        view.setPresenter(this);

    }

    @Override
    public void requestMatchingCities(final Context context, final String city) {

        view.setLoadingView();

        subscriptions.add(
                Observable.create(new Observable.OnSubscribe<List<City>>() {
                    @Override
                    public void call(Subscriber<? super List<City>> subscriber) {
                        subscriber.onNext(new CitiesManager().findMathcingCity(context, city));
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<City>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                view.setErrorView();
                            }

                            @Override
                            public void onNext(List<City> cities) {
                                view.onCitiesResponse(cities);
                            }
                        })
        );

    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
    }

}
