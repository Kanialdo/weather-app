package pl.krystiankaniowski.weatherapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import pl.krystiankaniowski.weatherapp.eventbus.WeatherMessage;
import pl.krystiankaniowski.weatherapp.view.SearchFragment;
import pl.krystiankaniowski.weatherapp.view.WeatherDetailsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchContent(new SearchFragment());

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void switchContent(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_container, fragment);
        transaction.commit();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WeatherMessage event) {
        switchContent(WeatherDetailsFragment.newInstance(event.getCity(), event.getTemperature()));
    }

}
