package pl.krystiankaniowski.weatherapp.view.modules.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.dagger.FragmentInjector;
import pl.krystiankaniowski.weatherapp.view.base.BaseFragment;

public class WeatherDetailsFragment extends BaseFragment implements WeatherContract.View {

    private static final String TAG = WeatherDetailsFragment.class.getSimpleName();

    private static final String ARGUMENT_CITY_ID = "city_id";

    @BindView(R.id.fragment_details_tv_city_name)
    TextView cityNameField;

    @BindView(R.id.fragment_details_tv_temperature)
    TextView temperatureField;

    @BindView(R.id.fragment_details_tv_summary)
    TextView summaryField;

    @BindView(R.id.fragment_details_tv_pressure)
    TextView pressureField;

    @BindView(R.id.fragment_details_tv_humminidy)
    TextView humidityField;

    @BindView(R.id.fragment_details_iv_photo)
    ImageView photoField;

    @Inject
    Picasso picasso;

    private WeatherContract.Presenter presenter;

    private MenuItem favouriteIcon;

    public static BaseFragment newInstance(int cityId) {

        Bundle args = new Bundle();
        args.putInt(ARGUMENT_CITY_ID, cityId);

        WeatherDetailsFragment fragment = new WeatherDetailsFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        FragmentInjector.inject(this);

        new WeatherPresenter(this, getArguments().getInt(ARGUMENT_CITY_ID));

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
        presenter.requestWeather();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.weather_details, menu);
        favouriteIcon = menu.findItem(R.id.menu_weather_details_favourite);
        favouriteIcon.setIcon(presenter.isFavourite() ? R.drawable.ic_favorite : R.drawable.ic_favorite_off);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_weather_details_favourite:
                if (presenter.isFavourite()) {
                    presenter.unsetFavourite();
                } else {
                    presenter.setFavourite();
                }
                favouriteIcon.setIcon(presenter.isFavourite() ? R.drawable.ic_favorite : R.drawable.ic_favorite_off);
                break;

            case R.id.menu_weather_details_refresh:
                presenter.requestWeather();
                break;

        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void setCityName(String cityName) {
        cityNameField.setText(cityName);
    }

    @Override
    public void setPhotoUrl(String photoUrl) {
        Log.d(TAG, "setPhotoUrl() called with: photoUrl = [" + photoUrl + "]");
        picasso.load(photoUrl).placeholder(R.drawable.default_background).resize(720, 480).centerCrop().into(photoField);
    }

    @Override
    public void setTemperature(String temperature) {
        temperatureField.setText(temperature);
    }

    @Override
    public void setWeather(String weather) {
        summaryField.setText(weather);
    }

    @Override
    public void setPressure(String pressure) {
        pressureField.setText(TextUtils.expandTemplate("pressure: ^1", pressure));
    }

    @Override
    public void setHumidity(String humidity) {
        humidityField.setText(TextUtils.expandTemplate("humidity: ^1", humidity));
    }

    @Override
    public void setWind(String wind) {

    }

    @Override
    public void setErrorView(String error) {
        summaryField.setText(error);
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
