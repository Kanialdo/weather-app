package pl.krystiankaniowski.weatherapp.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

public interface UniversalDelegatedAdapter<Type extends ViewElement> {

    UniversalViewHolder<Type> onCreateViewHolder(ViewGroup viewGroup);

    void onBindViewHolder(UniversalViewHolder<Type> viewHolder, Type item);

    boolean isForViewType(@NonNull Type item);

}
