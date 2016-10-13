package pl.krystiankaniowski.weatherapp.view.modules.search;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pl.krystiankaniowski.weatherapp.MainActivity;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.adapter.UniversalRecyclerAdapter;
import pl.krystiankaniowski.weatherapp.adapter.ViewElement;
import pl.krystiankaniowski.weatherapp.adapter.ViewElementType;
import pl.krystiankaniowski.weatherapp.adapter.utils.DelegatedInfoAdapter;
import pl.krystiankaniowski.weatherapp.adapter.utils.DelegatedSearchingAdapter;
import pl.krystiankaniowski.weatherapp.data.cities.City;
import pl.krystiankaniowski.weatherapp.settings.CacheManager;
import pl.krystiankaniowski.weatherapp.view.base.BaseFragment;
import pl.krystiankaniowski.weatherapp.view.modules.search.adapter.DelegatedNavigationCityAdapter;
import pl.krystiankaniowski.weatherapp.view.modules.weather.WeatherDetailsFragment;
import pl.krystiankaniowski.weatherapp.view.utils.KeyboardUtils;

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
                .registerDelegatedAdapter(ViewElementType.RESULT_CITY_ITEM.ordinal(), new DelegatedNavigationCityAdapter(
                        city -> {
                            KeyboardUtils.hideKeyboard(searchInput);
                            CacheManager.getInstance().saveCity(city);
                            ((MainActivity) getActivity()).switchContent(WeatherDetailsFragment.newInstance(city.getId()));
                        })
                )
                .registerDelegatedAdapter(ViewElementType.GENERAL_MESSAGE.ordinal(), new DelegatedInfoAdapter())
                .registerDelegatedAdapter(ViewElementType.GENERAL_SEARCHING.ordinal(), new DelegatedSearchingAdapter())
                .build();

    }

    @Override
    protected void onCreateFragmentView(View view) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        searchInput.addTextChangedListener(new TextWatcher() {

            private long lastInput = System.currentTimeMillis();
            private Handler handler = new Handler();
            private Runnable lastRunnable;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                lastInput = System.currentTimeMillis();

                if (lastRunnable != null) {
                    handler.removeCallbacks(lastRunnable);
                }

                lastRunnable = () -> {
                    if (System.currentTimeMillis() < lastInput + 1200) {
                        if (charSequence.toString().length() > 0) {
                            presenter.requestMatchingCities(getContext(), charSequence.toString());
                        } else {
                            setMessageView();
                        }
                    }
                };

                handler.postDelayed(lastRunnable, 1000);

            }

            @Override
            public void afterTextChanged(Editable editable) { }

        });

        setMessageView();

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
        searchInput.requestFocus();
    }

    @Override
    public void onPause() {
        presenter.unsubscribe();
        super.onPause();
    }

    @OnClick(R.id.fragment_search_ib_search)
    void search() {
        presenter.requestMatchingCities(getContext(), searchInput.getText().toString());
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCitiesResponse(List<City> matchingCities) {
        if (matchingCities != null && matchingCities.size() > 0) {
            adapter.setData(matchingCities);
            adapter.notifyDataSetChanged();
        } else {
            showMessage(new DelegatedInfoAdapter.InfoItem("No items", "try something else", R.drawable.ic_error));
        }
    }

    @Override
    public void setErrorView() {
        showMessage(new DelegatedInfoAdapter.InfoItem("error", "error", R.drawable.ic_error));
    }

    @Override
    public void setLoadingView() {
        showMessage(new DelegatedSearchingAdapter.SearchItem(""));
    }

    private void setMessageView() {
        showMessage(new DelegatedInfoAdapter.InfoItem("enter query", "", R.drawable.ic_search));
    }

    private void showMessage(ViewElement item) {
        List<ViewElement> data = new ArrayList<>();
        data.add(item);
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected String getDebugTag() {
        return TAG;
    }

}
