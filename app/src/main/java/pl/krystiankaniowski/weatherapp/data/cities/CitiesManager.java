package pl.krystiankaniowski.weatherapp.data.cities;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kryst on 09.10.2016.
 */

public class CitiesManager {

    public List<City> findMathcingCity(Context context, String pattern) {

        List<City> matchingCities = new ArrayList<>();

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("city_list.txt")));
            String line;
            while ((line = reader.readLine()) != null) {
                String name = line.split("\t")[1];
                if (name.toLowerCase().startsWith(pattern.toLowerCase())) {
                    matchingCities.add(parseLine(line));
                }
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        return matchingCities;

    }

    private City parseLine(String line) {

        String[] data = line.split("\t");

        return new City(
                Integer.valueOf(data[0]),
                data[1],
                Float.valueOf(data[2]),
                Float.valueOf(data[3]),
                data[4]
        );

    }

}
