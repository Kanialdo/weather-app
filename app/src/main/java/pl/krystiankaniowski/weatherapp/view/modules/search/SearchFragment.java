package pl.krystiankaniowski.weatherapp.view.modules.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import pl.krystiankaniowski.weatherapp.MainActivity;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.eventbus.WeatherMessage;
import pl.krystiankaniowski.weatherapp.view.WeatherDetailsFragment;
import pl.krystiankaniowski.weatherapp.view.base.BaseFragment;

public class SearchFragment extends BaseFragment implements SearchContract.View {

    private static final String TAG = SearchFragment.class.getSimpleName();

    @BindView(R.id.fragment_search_et_input)
    EditText searchInput;

    private SearchContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new SearchPresenter(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(presenter);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(presenter);
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @OnClick(R.id.fragment_search_b_search)
    void search() {
        presenter.requestWeather(searchInput.getText().toString());
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onWeatherResponse(WeatherMessage event) {
        ((MainActivity) getActivity()).switchContent(WeatherDetailsFragment.newInstance(event.getCity(), event.getTemperature()));
    }

    @Override
    public void onWeatherRequestError() {

    }

    @Override
    protected String getDebugTag() {
        return TAG;
    }

}
