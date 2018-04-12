package pl.dmcs.weatherapp

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text
import pl.dmcs.weatherapp.model.ForecastWeather
import pl.dmcs.weatherapp.model.List

/**
 * Created by Damian on 08.12.2017.
 */
class RecyclerViewAdapter(val list: ArrayList<List>?) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(list!![position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list!!.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(list: List) {
            val date: TextView = itemView.findViewById(R.id.date)
            val temperature: TextView = itemView.findViewById(R.id.temperature)
            val pressure: TextView = itemView.findViewById(R.id.pressure)

            date.setText(list.dt_txt)
            temperature.setText(list.main.temp.toString() + "C")
            pressure.setText(list.main.pressure.toString() + "hPa")

            //set the onclick listener for the singlt list item
            itemView.setOnClickListener({
                Log.e("ItemClicked", date.text.toString())
            })
        }

    }
}