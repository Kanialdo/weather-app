package pl.krystiankaniowski.weatherapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class UniversalViewHolder<Type extends ViewElement> extends RecyclerView.ViewHolder {

    public UniversalViewHolder(ViewGroup viewGroup, int layoutId) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false));
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind(Type type);

}
