package pl.krystiankaniowski.weatherapp.adapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class UniversalRecyclerAdapter<Type extends ViewElement> extends RecyclerView.Adapter<UniversalViewHolder<Type>> {

    private static final String TAG = UniversalRecyclerAdapter.class.getSimpleName();

    private SparseArrayCompat<UniversalDelegatedAdapter<Type>> delegatedAdapters = new SparseArrayCompat<>();
    private List<Type> items;

    public UniversalRecyclerAdapter(List<Type> data) {
        items = data;
    }

    protected void registerDelegatedAdapter(int typeId, UniversalDelegatedAdapter<Type> adapter) {
        Log.v(TAG, "registering " + typeId + " with " + adapter);
        delegatedAdapters.put(typeId, adapter);
    }

    @Override
    public void onBindViewHolder(UniversalViewHolder<Type> holder, int position) {

        UniversalDelegatedAdapter<Type> adapter = delegatedAdapters.get(getItemViewType(position));
        Type item = items.get(position);

        if (adapter.isForViewType(item)) {
            adapter.onBindViewHolder(holder, item);
        } else {
            Log.w(TAG, "onBindViewHolder wrong adapter assignment, trying repair");
            for (int i = 0; i < delegatedAdapters.size(); i++) {
                UniversalDelegatedAdapter<Type> tempAdapter = delegatedAdapters.get(delegatedAdapters.keyAt(i));
                if (tempAdapter.isForViewType(item)) {
                    tempAdapter.onBindViewHolder(holder, item);
                    return;
                }
            }
        }

    }

    @Override
    public UniversalViewHolder<Type> onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatedAdapters.get(viewType).onCreateViewHolder(parent);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<Type> data) {
        items = data;
    }

    public List<Type> getData() {
        return items;
    }

    public static class Builder<Type extends ViewElement> {

        private UniversalRecyclerAdapter<Type> adapter;

        public Builder(Class<Type> type) {
            adapter = new UniversalRecyclerAdapter<>(new ArrayList<Type>());
        }

        public Builder<Type> registerDelegatedAdapter(int typeId, UniversalDelegatedAdapter<Type> delegatedAdapter) {
            this.adapter.registerDelegatedAdapter(typeId, delegatedAdapter);
            return this;
        }

        public UniversalRecyclerAdapter<Type> build() {
            return adapter;
        }

    }

}
