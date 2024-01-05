package com.example.ebayapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ebayapplication.model.Earthquakes
import com.example.ebayapplication.network.EarthquakeApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author lgarg on 12/21/23.
 */
class EarthquakeViewModel : ViewModel() {


    val data = MutableLiveData<Earthquakes>()
    val getData: LiveData<Earthquakes> get() = data

    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.geonames.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EarthquakeApiService::class.java)

    fun fetchData() {
        viewModelScope.launch {
            try {
                val result = retrofit.fetchData()
                data.postValue(result)
            } catch (ex: Exception) {
                Log.e("Test", "EarthquakeViewModel exception $ex")
            }
        }
    }
}