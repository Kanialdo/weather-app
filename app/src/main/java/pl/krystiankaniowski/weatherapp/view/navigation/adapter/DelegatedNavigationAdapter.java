package pl.krystiankaniowski.weatherapp.view.navigation.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.adapter.OnClickListener;
import pl.krystiankaniowski.weatherapp.adapter.UniversalDelegatedAdapter;
import pl.krystiankaniowski.weatherapp.adapter.UniversalViewHolder;

public class DelegatedNavigationAdapter implements UniversalDelegatedAdapter<NavigationItem> {

    private OnClickListener<NavigationItem> listener;

    public DelegatedNavigationAdapter(@NonNull OnClickListener<NavigationItem> listener) {
        this.listener = listener;
    }

    @Override
    public UniversalViewHolder<NavigationItem> onCreateViewHolder(ViewGroup viewGroup) {
        return new NavigationViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(UniversalViewHolder<NavigationItem> viewHolder, NavigationItem item) {
        ((NavigationViewHolder) viewHolder).bind(item, listener);
    }

    @Override
    public boolean isForViewType(@NonNull NavigationItem item) {
        return true;
    }

    static class NavigationViewHolder extends UniversalViewHolder<NavigationItem> {

        @BindView(R.id.list_item_navigation_tv_name)
        TextView tvName;

        public NavigationViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.list_item_navigation);
            itemView.setClickable(true);
        }

        @Override
        public void bind(NavigationItem navigationItem) {
            tvName.setText(navigationItem.getName());
        }

        public void bind(final NavigationItem item, final OnClickListener<NavigationItem> listener) {
            bind(item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(item);
                }
            });
        }

    }

}
