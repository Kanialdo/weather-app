package pl.krystiankaniowski.weatherapp.adapter.utils;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.adapter.UniversalDelegatedAdapter;
import pl.krystiankaniowski.weatherapp.adapter.UniversalViewHolder;
import pl.krystiankaniowski.weatherapp.adapter.ViewElementType;
import pl.krystiankaniowski.weatherapp.adapter.ViewElement;

public class DelegatedSearchingAdapter implements UniversalDelegatedAdapter<DelegatedSearchingAdapter.SearchItem> {

    public static class SearchItem implements ViewElement {

        private String message;

        public SearchItem(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public int getViewType() {
            return ViewElementType.GENERAL_SEARCHING.ordinal();
        }

    }

    @Override
    public UniversalViewHolder<SearchItem> onCreateViewHolder(ViewGroup viewGroup) {
        return new SearchingViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(UniversalViewHolder<SearchItem> viewHolder, SearchItem item) {
        ((SearchingViewHolder) viewHolder).bind(item);
    }

    @Override
    public boolean isForViewType(@NonNull SearchItem item) {
        return true;
    }

    static class SearchingViewHolder extends UniversalViewHolder<SearchItem> {

        @BindView(R.id.list_item_searching_tv_message)
        TextView tvName;

        public SearchingViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.list_item_searching);
        }

        @Override
        public void bind(SearchItem item) {
            tvName.setVisibility((item.getMessage().length() > 0) ? View.VISIBLE : View.GONE);
            tvName.setText(item.getMessage());
        }

    }

}
