package pl.krystiankaniowski.weatherapp.view.modules.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.data.openweathermap.model.Weather;
import pl.krystiankaniowski.weatherapp.view.base.BaseFragment;

public class WeatherDetailsFragment extends BaseFragment implements WeatherContract.View {

    private static final String TAG = WeatherDetailsFragment.class.getSimpleName();

    private static final String ARGUMENT_CITY_ID = "city_id";

    @BindView(R.id.fragment_details_tv_city_name)
    TextView cityNameField;

    @BindView(R.id.fragment_details_tv_temperature)
    TextView temperatureField;

    private WeatherContract.Presenter presenter;

    public static Fragment newInstance(int cityId) {

        Bundle args = new Bundle();
        args.putInt(ARGUMENT_CITY_ID, cityId);

        WeatherDetailsFragment fragment = new WeatherDetailsFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new WeatherPresenter(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
        presenter.requestWeather(getArguments().getInt(ARGUMENT_CITY_ID));
    }

    @Override
    public void onPause() {
        presenter.unsubscribe();
        super.onPause();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_details;
    }

    @Override
    protected void onCreateFragmentView(View view) {
        cityNameField.setText(String.valueOf(getArguments().getInt(ARGUMENT_CITY_ID)));
    }

    @Override
    public void setWeatherDetails(Weather weather) {
        temperatureField.setText("received details");
    }

    @Override
    public void setError(String error) {

    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected String getDebugTag() {
        return TAG;
    }

}
