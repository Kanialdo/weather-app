package pl.krystiankaniowski.weatherapp.view.navigation;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.adapter.UniversalRecyclerAdapter;
import pl.krystiankaniowski.weatherapp.adapter.ViewElemenetType;
import pl.krystiankaniowski.weatherapp.view.navigation.adapter.DelegatedNavigationAdapter;
import pl.krystiankaniowski.weatherapp.view.navigation.adapter.NavigationItem;

public class NavigationMenu {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    UniversalRecyclerAdapter<NavigationItem> adapter;

    public NavigationMenu(View rootView) {

        ButterKnife.bind(this, rootView);

        adapter = new UniversalRecyclerAdapter.Builder<>(NavigationItem.class)
                .registerDelegatedAdapter(ViewElemenetType.NAVIGATION_ITEM.ordinal(), new DelegatedNavigationAdapter())
                .build();

    }

}
