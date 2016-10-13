package pl.krystiankaniowski.weatherapp.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashSet;
import java.util.Set;

import pl.krystiankaniowski.weatherapp.data.cities.City;

/**
 * Created by kryst on 13.10.2016.
 */

public class CacheManager {

    private SharedPreferences preferences;

    private static final String TAG = CacheManager.class.getSimpleName();
    private static final String PREFERENCE_KEY = "cities";
    private static final String PREFERENCE_KEY_CITIES = "cities";

    private Set<City> cities;

    private static CacheManager instance;

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }
        return instance;
    }

    public static void init(Context context) {

        CacheManager manager = getInstance();

        manager.preferences = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);

        String rawCities = manager.preferences.getString(PREFERENCE_KEY_CITIES, null);
        manager.cities = (rawCities != null) ? new Gson().fromJson(rawCities, new TypeToken<Set<City>>() {}.getType()) : new HashSet<>();

    }

    private CacheManager() {
    }

    public Set<City> getFavourites() {
        return cities;
    }

    public City getCity(int cityId) {
        for (City city : cities) {
            if (city.getId() == cityId) {
                return city;
            }
        }
        return null;
    }

    public void saveCity(City city) {
        cities.add(city);
        saveCache();
    }

    public void saveCache() {

        String json = new Gson().toJson(cities);
        Log.v(TAG, json);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREFERENCE_KEY_CITIES, json);
        editor.apply();
    }

}
