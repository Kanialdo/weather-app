package pl.krystiankaniowski.weatherapp.view.navigation.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.adapter.OnClickListener;
import pl.krystiankaniowski.weatherapp.adapter.UniversalDelegatedAdapter;
import pl.krystiankaniowski.weatherapp.adapter.UniversalViewHolder;

public class DelegatedNavigationCityAdapter implements UniversalDelegatedAdapter<NavigationCityItem> {

    private OnClickListener<NavigationCityItem> listener;

    public DelegatedNavigationCityAdapter(@NonNull OnClickListener<NavigationCityItem> listener) {
        this.listener = listener;
    }

    @Override
    public UniversalViewHolder<NavigationCityItem> onCreateViewHolder(ViewGroup viewGroup) {
        return new NavigationCityViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(UniversalViewHolder<NavigationCityItem> viewHolder, NavigationCityItem item) {
        ((NavigationCityViewHolder) viewHolder).bind(item, listener);
    }

    @Override
    public boolean isForViewType(@NonNull NavigationCityItem item) {
        return true;
    }

    static class NavigationCityViewHolder extends UniversalViewHolder<NavigationCityItem> {

        @BindView(R.id.list_item_city_navigation_ll_root)
        LinearLayout rootView;

        @BindView(R.id.list_item_city_navigation_tv_name)
        TextView tvName;

        public NavigationCityViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.list_item_city_navigation);
            rootView.setClickable(true);
        }

        @Override
        public void bind(NavigationCityItem navigationItem) {
            tvName.setText(navigationItem.getName());
        }

        public void bind(final NavigationCityItem item, final OnClickListener<NavigationCityItem> listener) {
            bind(item);
            rootView.setOnClickListener(view -> listener.onClick(item));
        }

    }

}
