package pl.krystiankaniowski.weatherapp.view.navigation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.krystiankaniowski.weatherapp.MainActivity;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.adapter.OnClickListener;
import pl.krystiankaniowski.weatherapp.adapter.UniversalRecyclerAdapter;
import pl.krystiankaniowski.weatherapp.adapter.ViewElemenetType;
import pl.krystiankaniowski.weatherapp.adapter.ViewElement;
import pl.krystiankaniowski.weatherapp.view.WeatherDetailsFragment;
import pl.krystiankaniowski.weatherapp.view.modules.search.SearchFragment;
import pl.krystiankaniowski.weatherapp.view.navigation.adapter.DelegatedNavigationAdapter;
import pl.krystiankaniowski.weatherapp.view.navigation.adapter.DelegatedNavigationCityAdapter;
import pl.krystiankaniowski.weatherapp.view.navigation.adapter.NavigationCityItem;
import pl.krystiankaniowski.weatherapp.view.navigation.adapter.NavigationItem;

public class NavigationMenu {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    List<ViewElement> items;

    UniversalRecyclerAdapter<ViewElement> adapter;

    public NavigationMenu(final MainActivity mainActivity, final View rootView) {

        ButterKnife.bind(this, rootView);

        items = new ArrayList<>();

        items.add(new NavigationCityItem("Example city", new Runnable() {
            @Override
            public void run() {
                mainActivity.switchContent(WeatherDetailsFragment.newInstance("Lublin", 300));
            }
        }));
        items.add(new NavigationCityItem("Example city 2", new Runnable() {
            @Override
            public void run() {
                mainActivity.switchContent(WeatherDetailsFragment.newInstance("Warsaw", 300));
            }
        }));
        items.add(new NavigationItem("Add city", android.R.drawable.ic_menu_add, new Runnable() {
            @Override
            public void run() {
                mainActivity.switchContent(new SearchFragment());
            }
        }));
        items.add(new NavigationItem("Settings", android.R.drawable.ic_menu_preferences, new Runnable() {
            @Override
            public void run() {
            }
        }));
        items.add(new NavigationItem("About app", android.R.drawable.ic_menu_info_details, new Runnable() {
            @Override
            public void run() {
            }
        }));

        adapter = new UniversalRecyclerAdapter.Builder<>()
                .registerDelegatedAdapter(ViewElemenetType.NAVIGATION_ITEM.ordinal(), new DelegatedNavigationAdapter(
                        new OnClickListener<NavigationItem>() {
                            @Override
                            public void onClick(NavigationItem navigationItem) {
                                if (navigationItem.getRunnable() != null) {
                                    navigationItem.getRunnable().run();
                                }
                            }
                        }))
                .registerDelegatedAdapter(ViewElemenetType.NAVIGATION_CITY_ITEM.ordinal(), new DelegatedNavigationCityAdapter(
                        new OnClickListener<NavigationCityItem>() {
                            @Override
                            public void onClick(NavigationCityItem navigationItem) {
                                if (navigationItem.getRunnable() != null) {
                                    navigationItem.getRunnable().run();
                                }
                            }
                        }))
                .build();

        adapter.setData(items);

        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setAdapter(adapter);

    }

}
