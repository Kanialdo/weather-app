package pl.krystiankaniowski.weatherapp.view.navigation.adapter;

import pl.krystiankaniowski.weatherapp.adapter.ViewElementType;
import pl.krystiankaniowski.weatherapp.adapter.ViewElement;

public class NavigationItem implements ViewElement {

    private String name;
    private int iconId;
    private Runnable runnable;

    public NavigationItem(String name, int iconId, Runnable runnable) {
        this.name = name;
        this.iconId = iconId;
        this.runnable = runnable;
    }

    public String getName() { return name; }

    public Runnable getRunnable() { return runnable; }

    public int getIconId() { return iconId; }

    @Override
    public int getViewType() {
        return ViewElementType.NAVIGATION_ITEM.ordinal();
    }

}
