package pl.krystiankaniowski.weatherapp.data.cities

import pl.krystiankaniowski.weatherapp.adapter.ViewElementType
import pl.krystiankaniowski.weatherapp.adapter.ViewElement

/**
 * Created by kryst on 09.10.2016.
 */

data class City(val id: Int, val name: String, val log: Double, val lag: Double, val countryCode: String) : ViewElement {

    override fun getViewType(): Int = ViewElementType.NAVIGATION_CITY_ITEM.ordinal

}
