package pl.krystiankaniowski.weatherapp.view.navigation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.adapter.OnClickListener;
import pl.krystiankaniowski.weatherapp.adapter.UniversalRecyclerAdapter;
import pl.krystiankaniowski.weatherapp.adapter.ViewElemenetType;
import pl.krystiankaniowski.weatherapp.view.navigation.adapter.DelegatedNavigationAdapter;
import pl.krystiankaniowski.weatherapp.view.navigation.adapter.NavigationItem;

public class NavigationMenu {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    List<NavigationItem> items;

    UniversalRecyclerAdapter<NavigationItem> adapter;

    public NavigationMenu(final View rootView) {

        ButterKnife.bind(this, rootView);

        items = new ArrayList<>();

        items.add(new NavigationItem("First position", new Runnable() {
            @Override
            public void run() {
                Toast.makeText(rootView.getContext(), "Clicked First position", Toast.LENGTH_SHORT).show();
            }
        }));
        items.add(new NavigationItem("Second position"));
        items.add(new NavigationItem("Third position"));

        adapter = new UniversalRecyclerAdapter.Builder<>(NavigationItem.class)
                .registerDelegatedAdapter(ViewElemenetType.NAVIGATION_ITEM.ordinal(), new DelegatedNavigationAdapter(
                        new OnClickListener<NavigationItem>() {
                            @Override
                            public void onClick(NavigationItem navigationItem) {
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
