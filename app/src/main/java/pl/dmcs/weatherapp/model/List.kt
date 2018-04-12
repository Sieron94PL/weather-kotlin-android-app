package pl.dmcs.weatherapp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by Damian on 16.12.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class List(val main: Main, val wind: Wind, val dt_txt: String, val weather: ArrayList<Weather>) {
}