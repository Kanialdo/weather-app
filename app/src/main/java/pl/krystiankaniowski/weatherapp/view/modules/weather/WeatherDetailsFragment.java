package pl.krystiankaniowski.weatherapp.view.modules.weather;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.view.base.BaseFragment;

public class WeatherDetailsFragment extends BaseFragment {

    private static final String TAG = WeatherDetailsFragment.class.getSimpleName();

    private static final String ARGUMENT_CITY_NAME = "city_name";
    private static final String ARGUMENT_TEMPERATURE = "temperature";

    @BindView(R.id.fragment_details_tv_city_name)
    TextView cityNameField;

    @BindView(R.id.fragment_details_tv_temperature)
    TextView temperatureField;

    public static WeatherDetailsFragment newInstance(String cityName, double temperature) {

        Bundle args = new Bundle();
        args.putString(ARGUMENT_CITY_NAME, cityName);
        args.putDouble(ARGUMENT_TEMPERATURE, temperature);

        WeatherDetailsFragment fragment = new WeatherDetailsFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_details;
    }

    @Override
    protected void onCreateFragmentView(View view) {
        cityNameField.setText(getArguments().getString(ARGUMENT_CITY_NAME));
        temperatureField.setText(String.valueOf(getArguments().getDouble(ARGUMENT_TEMPERATURE)));
    }

    @Override
    protected String getDebugTag() {
        return TAG;
    }

}
