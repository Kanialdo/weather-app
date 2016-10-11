package pl.krystiankaniowski.weatherapp.view.modules.search.adapter;

import android.support.annotation.NonNull;
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

        @BindView(R.id.list_item_city_result_ll_root)
        LinearLayout rootView;

        @BindView(R.id.list_item_city_result_tv_name)
        TextView tvName;

        @BindView(R.id.list_item_city_result_tv_location)
        TextView tvLocation;

        public NavigationCityViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.list_item_city_result);
            rootView.setClickable(true);
        }

        @Override
        public void bind(City city) {
            tvName.setText(city.getName() + ", " + city.getCountryCode());
            tvLocation.setText(city.getLog() + ", " + city.getLag());
        }

        public void bind(final City city, final OnClickListener<City> listener) {
            bind(city);
            rootView.setOnClickListener(view -> listener.onClick(city));
        }

    }

}
