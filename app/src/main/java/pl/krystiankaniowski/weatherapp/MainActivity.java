package pl.krystiankaniowski.weatherapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.krystiankaniowski.weatherapp.settings.CacheManager;
import pl.krystiankaniowski.weatherapp.view.modules.search.SearchFragment;
import pl.krystiankaniowski.weatherapp.view.navigation.NavigationMenu;

public class MainActivity extends AppCompatActivity {

    private NavigationMenu menu;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        CacheManager.init(this);

        menu = new NavigationMenu(this, drawerLayout, toolbar);

        switchContent(new SearchFragment());

    }

    public void switchContent(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_container, fragment);
        transaction.commit();

    }

}
