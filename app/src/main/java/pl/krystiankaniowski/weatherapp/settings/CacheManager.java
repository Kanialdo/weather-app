package pl.krystiankaniowski.weatherapp.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pl.krystiankaniowski.weatherapp.data.cities.City;
import pl.krystiankaniowski.weatherapp.data.places.GooglePlacesManager;
import pl.krystiankaniowski.weatherapp.eventbus.PhotoUrlReceived;

/**
 * Created by kryst on 13.10.2016.
 */

public class CacheManager {

    private static final String TAG = CacheManager.class.getSimpleName();

    private static final String PREFERENCE_KEY = "cities";
    private static final String PREFERENCE_KEY_CITIES = "cities";

    private static CacheManager instance;

    private SharedPreferences preferences;

    private HashMap<Integer, City> cities;

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
            EventBus.getDefault().register(instance);
        }
        return instance;
    }

    public static void init(Context context) {

        CacheManager manager = getInstance();

        manager.preferences = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);

        String rawCities = manager.preferences.getString(PREFERENCE_KEY_CITIES, null);
        Set<City> citiesSet = (rawCities != null) ? new Gson().fromJson(rawCities, new TypeToken<Set<City>>() {}.getType()) : new HashSet<>();

        manager.cities = new HashMap<>();

        for (City city : citiesSet) {
            manager.cities.put(city.getId(), city);
        }

    }

    private CacheManager() {
    }

    public Set<City> getFavourites() {
        Set<City> cities = new HashSet<>();
        for (City city : this.cities.values()) {
            if (city.isFavourite()) {
                cities.add(city);
            }
        }
        return cities;
    }

    public City getCity(int cityId) {
        return cities.get(cityId);
    }

    public void saveCity(City city) {

        cities.put(city.getId(), city);

        if (city.getPhotoUrl() == null) {
            new GooglePlacesManager().findPlaces(city.getId(), city.getLongitude(), city.getLatitude());
        }

        saveCache();

    }

    public void saveCache() {

        Set<City> cities = new HashSet<>();
        for (City city : this.cities.values()) {
            cities.add(city);
        }

        String json = new Gson().toJson(cities);
        Log.v(TAG, json);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREFERENCE_KEY_CITIES, json);
        editor.apply();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCityPhotoUrlFounded(PhotoUrlReceived message) {
        City city = getCity(message.getCityId());
        city.setPhotoUrl(message.getLink());
        saveCity(city);
    }

}
