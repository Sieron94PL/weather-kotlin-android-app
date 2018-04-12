package pl.dmcs.weatherapp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by Damian on 16.12.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class ForecastWeather(val list: ArrayList<List>) {
}