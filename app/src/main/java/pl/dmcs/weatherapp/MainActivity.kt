package pl.dmcs.weatherapp


import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.widget.LinearLayout

import android.widget.Toast
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

import kotlinx.android.synthetic.main.activity_main.*
import pl.dmcs.weatherapp.model.ForecastWeather
import pl.dmcs.weatherapp.model.Weather


class MainActivity : AppCompatActivity() {

    private val key: String = "&appid=f126704c249658d3d210e76930bccc2e"
    private val baseUrl: String = "http://api.openweathermap.org/data/2.5/"
    private val units: String = "&units=metric"
    private var city: String? = null
    private val httpHandler: HttpHandler = HttpHandler()
    private val mapper = jacksonObjectMapper()

    private fun getUrl(city: String?, typeApi: String): String? {
        return baseUrl + typeApi + city + units + key
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        button.setOnClickListener({
            city = editText.text.toString()
            CurrentWeather(getUrl(city, "forecast?q="), recyclerView).execute()
        })
    }

    private inner class CurrentWeather(val url: String?, val recyclerView: RecyclerView) : AsyncTask<Void, Void, ForecastWeather?>() {

        override fun doInBackground(vararg p0: Void?): ForecastWeather? {
            val json: String? = httpHandler.makeServiceCall(url)
            if (json != null) {
                var forecastWeather: ForecastWeather = mapper.readValue<ForecastWeather>(json)
                return forecastWeather;
            } else
                return null
        }

        override fun onPostExecute(forecastWeather: ForecastWeather?) {
            super.onPostExecute(forecastWeather)
            val items = forecastWeather?.list
            recyclerView.adapter = RecyclerViewAdapter(items)
            if (forecastWeather != null) {
                desc.setText(forecastWeather.list.get(0).weather[0].main)
                temp.setText(forecastWeather.list.get(0).main.temp.toString() + "C")
                press.setText(forecastWeather.list.get(0).main.pressure.toString() + "hPa")
                hum.setText(forecastWeather.list.get(0).main.humidity.toString() + "%")
                wind.setText(forecastWeather.list.get(0).wind.speed.toString() + "m/s")
                Toast.makeText(applicationContext, "The data for city " + city?.toUpperCase() + " is refreshed.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "City " + city?.toUpperCase() + " not found.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
