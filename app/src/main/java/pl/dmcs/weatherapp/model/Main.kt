package pl.dmcs.weatherapp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by Damian on 16.12.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Main(val temp: Double, val pressure: Int, val humidity: Int, val temp_min: Double, val temp_max: Double) {
}