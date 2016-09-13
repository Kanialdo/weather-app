package pl.krystiankaniowski.weatherapp.view;

import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.data.WeatherDataManager;
import pl.krystiankaniowski.weatherapp.view.base.BaseFragment;

public class SearchFragment extends BaseFragment {

    private static final String TAG = SearchFragment.class.getSimpleName();

    @BindView(R.id.fragment_search_et_input)
    EditText searchInput;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @OnClick(R.id.fragment_search_b_search)
    void search() {
        new WeatherDataManager().getWeather(searchInput.getText().toString());
    }

    @Override
    protected String getDebugTag() {
        return TAG;
    }

}
