package pl.krystiankaniowski.weatherapp.view.utils;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import pl.krystiankaniowski.weatherapp.view.base.BaseFragment;

public class FragmentSwitcher {

    public static void showPrimaryFragment(AppCompatActivity activity, int containerId, BaseFragment fragment) {
        FragmentSwitcher.clearBackstack(activity);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    public static void showInnerFragment(AppCompatActivity activity, int containerId, BaseFragment fragment) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(fragment.toString());
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    public static void clearBackstack(AppCompatActivity activity) {
        final FragmentManager fragmentManager = activity.getSupportFragmentManager();
        while (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStackImmediate();
        }
    }

}
