package com.example.kotlinweatherapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doAsync {
            RunRequest()
            uiThread { toast("Request Performed") }

        }
    }

    private fun  RunRequest()
    {
        val cityName:String = "New York"
        val url ="https://api.openweathermap.org/data/2.5/weather?q=queens&appid=7193e3146df31181681e89836a2afc9a&units=imperial"
        val resultJson = URL(url).readText()
        Log.d("Weather Forecast", resultJson)
        val jsonObj = JSONObject(resultJson)
        val main =jsonObj.getJSONObject("main")
        val temp = main.getString("temp") + "°F"
        val minMaxTemp =main.getString("temp_min")+"F/"+ main.getString("temp_max")+"°F"
        val cityNameText = findViewById<TextView>(R.id.textView2) as TextView
        val tempText =findViewById<TextView>(R.id.textView) as TextView
        val minMaxTempText =findViewById<TextView>(R.id.textView3) as TextView
        cityNameText.text = cityName;
        tempText.text = temp
        minMaxTempText.text = minMaxTemp



    }
}