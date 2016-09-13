package pl.krystiankaniowski.weatherapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.krystiankaniowski.weatherapp.R;

public class WeatherDetailsFragment extends Fragment {

    private static final String TAG = WeatherDetailsFragment.class.getSimpleName();

    private static final String ARGUMENT_CITY_NAME = "city_name";
    private static final String ARGUMENT_TEMPERATURE = "temperature";

    @BindView(R.id.fragment_details_tv_city_name)
    TextView cityNameField;

    @BindView(R.id.fragment_details_tv_temperature)
    TextView temperatureField;

    private Unbinder bind;

    public static WeatherDetailsFragment newInstance(String cityName, double temperature) {

        Bundle args = new Bundle();
        args.putString(ARGUMENT_CITY_NAME, cityName);
        args.putDouble(ARGUMENT_TEMPERATURE, temperature);

        WeatherDetailsFragment fragment = new WeatherDetailsFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);
        bind = ButterKnife.bind(this, view);

        cityNameField.setText(getArguments().getString(ARGUMENT_CITY_NAME));
        temperatureField.setText(String.valueOf(getArguments().getDouble(ARGUMENT_TEMPERATURE)));

        return view;

    }

    @Override
    public void onDestroyView() {
        bind.unbind();
        super.onDestroyView();
    }

}
