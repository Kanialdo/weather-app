package pl.krystiankaniowski.weatherapp.view.navigation.adapter;

import pl.krystiankaniowski.weatherapp.adapter.ViewElemenetType;
import pl.krystiankaniowski.weatherapp.adapter.ViewElement;

public class NavigationItem implements ViewElement {

    private String name;

    public NavigationItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getViewType() {
        return ViewElemenetType.NAVIGATION_ITEM.ordinal();
    }

}
