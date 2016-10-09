package pl.krystiankaniowski.weatherapp.view.modules.search;

import android.content.Context;

import pl.krystiankaniowski.weatherapp.data.cities.CitiesManager;

/**
 * Created by kryst on 08.10.2016.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void requestMatchingCities(Context context, String city) {
        view.onCitiesResponse(new CitiesManager().findMathcingCity(context, city));
    }

    @Override
    public void start() {

    }

}
