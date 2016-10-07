package pl.krystiankaniowski.weatherapp.view.navigation.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.adapter.UniversalDelegatedAdapter;
import pl.krystiankaniowski.weatherapp.adapter.UniversalViewHolder;

public class DelegatedNavigationAdapter implements UniversalDelegatedAdapter<NavigationItem> {

    @Override
    public UniversalViewHolder<NavigationItem> onCreateViewHolder(ViewGroup viewGroup) {
        return new NavigationViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(UniversalViewHolder<NavigationItem> viewHolder, NavigationItem item) {
        ((NavigationViewHolder) viewHolder).bind(item);
    }

    @Override
    public boolean isForViewType(@NonNull NavigationItem item) {
        return true;
    }

    static class NavigationViewHolder extends UniversalViewHolder<NavigationItem> {

        public NavigationViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.navigation_menu);
        }

        @Override
        public void bind(NavigationItem navigationItem) {

        }

    }

}
