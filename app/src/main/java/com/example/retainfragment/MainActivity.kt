package com.example.retainfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var retainFragment = AdapterFragment()
    private var weatherAdapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            getWeatherListLogged()
            retainFragment.apply {
                supportFragmentManager
                    .beginTransaction()
                    .add(this, "retain_fragment")
                    .commit()
                savedAdapter = weatherAdapter
            }
        }
        else {
            retainFragment.apply {
                weatherAdapter =
                    (supportFragmentManager
                    .findFragmentByTag("retain_fragment") as AdapterFragment)
                    .savedAdapter
            }
        }

        findViewById<RecyclerView?>(R.id.rView).apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = weatherAdapter
        }
    }

    private fun getWeatherListLogged(){
        getAllWeatherList(
            Common.getRetrofitServices(LoggingInterceptorClient.client)
        )
        getAllWeatherList(
            Common.getRetrofitServices(HttpLoggingInterceptorClient.client)
        )
    }

    private fun getAllWeatherList(mService: RetrofitServices) {
        mService.getWeatherList().enqueue(object : Callback<DataWeather> {
            override fun onResponse(call: Call<DataWeather>, response: Response<DataWeather>) {
                val dataWeather = response.body() as DataWeather
                weatherAdapter.submitList(dataWeather.list)
            }
            override fun onFailure(call: Call<DataWeather>, t: Throwable) {
            }
        })
    }
}