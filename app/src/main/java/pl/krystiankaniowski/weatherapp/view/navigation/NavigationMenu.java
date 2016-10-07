package pl.krystiankaniowski.weatherapp.view.navigation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

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

    List<NavigationItem> items;

    UniversalRecyclerAdapter<NavigationItem> adapter;

    public NavigationMenu(View rootView) {

        ButterKnife.bind(this, rootView);

        items = new ArrayList<>();

        items.add(new NavigationItem("First position"));
        items.add(new NavigationItem("Second position"));
        items.add(new NavigationItem("Third position"));

        adapter = new UniversalRecyclerAdapter.Builder<>(NavigationItem.class)
                .registerDelegatedAdapter(ViewElemenetType.NAVIGATION_ITEM.ordinal(), new DelegatedNavigationAdapter())
                .build();

        adapter.setData(items);

        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setAdapter(adapter);

    }

}
