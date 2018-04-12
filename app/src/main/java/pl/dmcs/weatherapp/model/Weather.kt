package pl.dmcs.weatherapp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by Damian on 08.12.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Weather(val main: String){

}