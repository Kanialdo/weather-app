package pl.krystiankaniowski.weatherapp.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected View view;

    private Unbinder bind;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        bind = ButterKnife.bind(this, view);
        onCreateFragmentView(view);
        return view;
    }

    @Override
    public final void onDestroyView() {
        onDestroyFragmentView();
        bind.unbind();
        super.onDestroyView();
    }

    protected abstract String getDebugTag();

    protected abstract int getLayoutId();

    protected void onCreateFragmentView(View view) {}

    protected void onDestroyFragmentView() {}

}
