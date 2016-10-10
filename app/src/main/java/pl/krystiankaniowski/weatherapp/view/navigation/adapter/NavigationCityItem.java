package pl.krystiankaniowski.weatherapp.view.navigation.adapter;

import pl.krystiankaniowski.weatherapp.adapter.ViewElementType;
import pl.krystiankaniowski.weatherapp.adapter.ViewElement;

/**
 * Created by kryst on 08.10.2016.
 */

public class NavigationCityItem implements ViewElement {

    private String name;
    private Runnable runnable;

    public NavigationCityItem(String name, Runnable runnable) {
        this.name = name;
        this.runnable = runnable;
    }

    public String getName() { return name; }

    public Runnable getRunnable() { return runnable; }

    @Override
    public int getViewType() {
        return ViewElementType.NAVIGATION_CITY_ITEM.ordinal();
    }

}
