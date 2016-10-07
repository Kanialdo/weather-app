package pl.krystiankaniowski.weatherapp.view.navigation.adapter;

import pl.krystiankaniowski.weatherapp.adapter.ViewElemenetType;
import pl.krystiankaniowski.weatherapp.adapter.ViewElement;

public class NavigationItem implements ViewElement {

    @Override
    public int getViewType() {
        return ViewElemenetType.NAVIGATION_ITEM.ordinal();
    }

}
