package pl.krystiankaniowski.weatherapp.view.modules.search.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.adapter.OnClickListener;
import pl.krystiankaniowski.weatherapp.adapter.UniversalDelegatedAdapter;
import pl.krystiankaniowski.weatherapp.adapter.UniversalViewHolder;
import pl.krystiankaniowski.weatherapp.data.cities.City;

public class DelegatedNavigationCityAdapter implements UniversalDelegatedAdapter<City> {

    private OnClickListener<City> listener;

    public DelegatedNavigationCityAdapter(@NonNull OnClickListener<City> listener) {
        this.listener = listener;
    }

    @Override
    public UniversalViewHolder<City> onCreateViewHolder(ViewGroup viewGroup) {
        return new NavigationCityViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(UniversalViewHolder<City> viewHolder, City item) {
        ((NavigationCityViewHolder) viewHolder).bind(item, listener);
    }

    @Override
    public boolean isForViewType(@NonNull City item) {
        return true;
    }

    static class NavigationCityViewHolder extends UniversalViewHolder<City> {

        @BindView(R.id.list_item_navigation_city_ll_root)
        LinearLayout rootView;

        @BindView(R.id.list_item_navigation_city_tv_name)
        TextView tvName;

        public NavigationCityViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.list_item_navigation_city);
            rootView.setClickable(true);
        }

        @Override
        public void bind(City navigationItem) {
            tvName.setText(navigationItem.getName());
        }

        public void bind(final City item, final OnClickListener<City> listener) {
            bind(item);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(item);
                }
            });
        }

    }

}
