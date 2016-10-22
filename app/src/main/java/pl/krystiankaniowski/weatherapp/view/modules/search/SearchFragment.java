package pl.krystiankaniowski.weatherapp.view.modules.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SearchFragment extends BaseFragment implements SearchContract.View {

    private static final String TAG = SearchFragment.class.getSimpleName();

    @BindView(R.id.fragment_search_et_input)
    EditText searchInput;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private SearchContract.Presenter presenter;

    private UniversalRecyclerAdapter<ViewElement> adapter;

    private CompositeSubscription subscriptions = new CompositeSubscription();

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

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
        searchInput.requestFocus();

        subscriptions.add(
                RxTextView.textChanges(searchInput)
                        .debounce(1, TimeUnit.SECONDS, Schedulers.io())
                        .map(CharSequence::toString)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(text -> {
                            if (text.length() > 0) {
                                presenter.requestMatchingCities(getContext(), text);
                            } else {
                                setMessageView();
                            }
                        })
        );

    }

    @Override
    public void onPause() {
        subscriptions.clear();
        presenter.unsubscribe();
        KeyboardUtils.hideKeyboard(searchInput);
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
