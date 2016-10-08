package pl.krystiankaniowski.weatherapp.view.navigation.adapter;

import pl.krystiankaniowski.weatherapp.adapter.ViewElemenetType;
import pl.krystiankaniowski.weatherapp.adapter.ViewElement;

public class NavigationItem implements ViewElement {

    private String name;

    private Runnable runnable;

    public NavigationItem(String name) {
        this.name = name;
    }

    public NavigationItem(String name, Runnable runnable) {
        this.name = name;
        this.runnable = runnable;
    }

    public String getName() {
        return name;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    @Override
    public int getViewType() {
        return ViewElemenetType.NAVIGATION_ITEM.ordinal();
    }

}
