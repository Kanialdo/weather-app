package pl.krystiankaniowski.weatherapp.view.modules.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pl.krystiankaniowski.weatherapp.MainActivity;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.adapter.OnClickListener;
import pl.krystiankaniowski.weatherapp.adapter.UniversalRecyclerAdapter;
import pl.krystiankaniowski.weatherapp.adapter.ViewElement;
import pl.krystiankaniowski.weatherapp.adapter.ViewElementType;
import pl.krystiankaniowski.weatherapp.adapter.utils.DelegatedInfoAdapter;
import pl.krystiankaniowski.weatherapp.adapter.utils.DelegatedSearchingAdapter;
import pl.krystiankaniowski.weatherapp.data.cities.City;
import pl.krystiankaniowski.weatherapp.view.base.BaseFragment;
import pl.krystiankaniowski.weatherapp.view.modules.search.adapter.DelegatedNavigationCityAdapter;
import pl.krystiankaniowski.weatherapp.view.modules.weather.WeatherDetailsFragment;

public class SearchFragment extends BaseFragment implements SearchContract.View {

    private static final String TAG = SearchFragment.class.getSimpleName();

    @BindView(R.id.fragment_search_et_input)
    EditText searchInput;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private SearchContract.Presenter presenter;

    private UniversalRecyclerAdapter<ViewElement> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new SearchPresenter(this);

        adapter = new UniversalRecyclerAdapter.Builder<>()
                .registerDelegatedAdapter(ViewElementType.NAVIGATION_CITY_ITEM.ordinal(), new DelegatedNavigationCityAdapter(
                        new OnClickListener<City>() {
                            @Override
                            public void onClick(City city) {
                                ((MainActivity) getActivity()).switchContent(WeatherDetailsFragment.newInstance(city.getId()));
                            }
                        }))
                .registerDelegatedAdapter(ViewElementType.GENERAL_MESSAGE.ordinal(), new DelegatedInfoAdapter())
                .registerDelegatedAdapter(ViewElementType.GENERAL_SEARCHING.ordinal(), new DelegatedSearchingAdapter())
                .build();

    }

    @Override
    protected void onCreateFragmentView(View view) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        List<ViewElement> data = new ArrayList<>();
        data.add(new DelegatedInfoAdapter.InfoItem("enter query", "", android.R.drawable.ic_menu_search));
        adapter.setData(data);
        adapter.notifyDataSetChanged();

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

        adapter.setData(matchingCities);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void setErrorView() {

        List<ViewElement> data = new ArrayList<>();
        data.add(new DelegatedInfoAdapter.InfoItem("error", "error", android.R.drawable.stat_notify_error));
        adapter.setData(data);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void setLoadingView() {

        List<ViewElement> data = new ArrayList<>();
        data.add(new DelegatedSearchingAdapter.SearchItem(""));
        adapter.setData(data);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected String getDebugTag() {
        return TAG;
    }

}
