package pl.krystiankaniowski.weatherapp.view.navigation;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.krystiankaniowski.weatherapp.MainActivity;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.adapter.UniversalRecyclerAdapter;
import pl.krystiankaniowski.weatherapp.adapter.ViewElement;
import pl.krystiankaniowski.weatherapp.adapter.ViewElementType;
import pl.krystiankaniowski.weatherapp.data.cities.City;
import pl.krystiankaniowski.weatherapp.settings.CacheManager;
import pl.krystiankaniowski.weatherapp.settings.PreferenceActivity;
import pl.krystiankaniowski.weatherapp.view.modules.search.SearchFragment;
import pl.krystiankaniowski.weatherapp.view.modules.weather.WeatherDetailsFragment;
import pl.krystiankaniowski.weatherapp.view.navigation.adapter.DelegatedNavigationAdapter;
import pl.krystiankaniowski.weatherapp.view.navigation.adapter.DelegatedNavigationCityAdapter;
import pl.krystiankaniowski.weatherapp.view.navigation.adapter.NavigationCityItem;
import pl.krystiankaniowski.weatherapp.view.navigation.adapter.NavigationItem;

public class NavigationMenu {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<ViewElement> items;

    private UniversalRecyclerAdapter<ViewElement> adapter;

    public NavigationMenu(final MainActivity mainActivity, final DrawerLayout drawerLayout, final Toolbar toolbar) {

        ButterKnife.bind(this, drawerLayout);

        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(mainActivity, drawerLayout, toolbar, R.string.info_drawer_open, R.string.info_drawer_close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        items = new ArrayList<>();

        for (City city : CacheManager.getInstance().getFavourites()) {
            items.add(new NavigationCityItem(city.getName() + ", " + city.getCountryCode(), () -> mainActivity.switchContent(WeatherDetailsFragment.newInstance(city.getId()))));
        }

        items.add(new NavigationItem("Search city", R.drawable.ic_search, () -> mainActivity.switchContent(new SearchFragment())));
        items.add(new NavigationItem("Settings", R.drawable.ic_settings, () -> mainActivity.startActivity(new Intent(mainActivity, PreferenceActivity.class))));
        items.add(new NavigationItem("About app", R.drawable.ic_info, null));

        adapter = new UniversalRecyclerAdapter.Builder<>()
                .registerDelegatedAdapter(ViewElementType.NAVIGATION_ITEM.ordinal(), new DelegatedNavigationAdapter(
                        navigationItem -> {
                            if (navigationItem.getRunnable() != null) {
                                navigationItem.getRunnable().run();
                            }
                            drawerLayout.closeDrawers();
                        }))
                .registerDelegatedAdapter(ViewElementType.NAVIGATION_CITY_ITEM.ordinal(), new DelegatedNavigationCityAdapter(
                        navigationItem -> {
                            if (navigationItem.getRunnable() != null) {
                                navigationItem.getRunnable().run();
                            }
                            drawerLayout.closeDrawers();
                        }))
                .build();

        adapter.setData(items);

        recyclerView.setLayoutManager(new LinearLayoutManager(drawerLayout.getContext()));
        recyclerView.setAdapter(adapter);

    }

}
