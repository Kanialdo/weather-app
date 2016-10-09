package pl.krystiankaniowski.weatherapp.view.modules.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.data.cities.City;
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
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        presenter.unsubscribe();
        super.onPause();
    }

    @OnClick(R.id.fragment_search_b_search)
    void search() {
        presenter.requestMatchingCities(getContext(), searchInput.getText().toString());
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCitiesResponse(List<City> matchingCities) {
        Toast.makeText(getContext(), "Matching cities: " + matchingCities.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String getDebugTag() {
        return TAG;
    }

}
