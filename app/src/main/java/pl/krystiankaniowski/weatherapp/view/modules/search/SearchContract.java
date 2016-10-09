package pl.krystiankaniowski.weatherapp.view.modules.search;

import android.content.Context;

import java.util.List;

import pl.krystiankaniowski.weatherapp.data.cities.City;
import pl.krystiankaniowski.weatherapp.mvp.BasePresenter;
import pl.krystiankaniowski.weatherapp.mvp.BaseView;

/**
 * Created by kryst on 08.10.2016.
 */

public interface SearchContract {

    interface View extends BaseView<Presenter> {

        void onCitiesResponse(List<City> matchingCities);

    }

    interface Presenter extends BasePresenter {

        void requestMatchingCities(Context context, String city);

    }

}
