package pl.krystiankaniowski.weatherapp.adapter.utils;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import pl.krystiankaniowski.weatherapp.R;
import pl.krystiankaniowski.weatherapp.adapter.UniversalDelegatedAdapter;
import pl.krystiankaniowski.weatherapp.adapter.UniversalViewHolder;
import pl.krystiankaniowski.weatherapp.adapter.ViewElement;
import pl.krystiankaniowski.weatherapp.adapter.ViewElementType;

public class DelegatedInfoAdapter implements UniversalDelegatedAdapter<DelegatedInfoAdapter.InfoItem> {

    public static class InfoItem implements ViewElement {

        private String title;
        private String details;
        private int iconId;

        public InfoItem(String title, String details, int iconId) {
            this.title = title;
            this.details = details;
            this.iconId = iconId;
        }

        @Override
        public int getViewType() {
            return ViewElementType.GENERAL_MESSAGE.ordinal();
        }

    }

    @Override
    public UniversalViewHolder<InfoItem> onCreateViewHolder(ViewGroup viewGroup) {
        return new MessageViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(UniversalViewHolder<InfoItem> viewHolder, InfoItem item) {
        ((MessageViewHolder) viewHolder).bind(item);
    }

    @Override
    public boolean isForViewType(@NonNull InfoItem item) {
        return true;
    }

    static class MessageViewHolder extends UniversalViewHolder<InfoItem> {

        @BindView(R.id.list_item_message_tv_title)
        TextView tvTitle;

        @BindView(R.id.list_item_message_tv_details)
        TextView tvDetails;

        @BindView(R.id.list_item_message_iv_icon)
        ImageView ivIcon;

        public MessageViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.list_item_message);
        }

        @Override
        public void bind(InfoItem item) {
            tvTitle.setVisibility((item.title.length() > 0) ? View.VISIBLE : View.GONE);
            tvTitle.setText(item.title);
            tvDetails.setVisibility((item.details.length() > 0) ? View.VISIBLE : View.GONE);
            tvDetails.setText(item.details);
            ivIcon.setVisibility((item.iconId > 0) ? View.VISIBLE : View.GONE);
            ivIcon.setImageResource(item.iconId);
        }

    }

}
