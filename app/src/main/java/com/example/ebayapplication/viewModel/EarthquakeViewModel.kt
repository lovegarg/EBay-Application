package com.example.ebayapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ebayapplication.model.EarthquakeModel
import com.example.ebayapplication.network.EarthquakeApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author lgarg on 12/21/23.
 */
class EarthquakeViewModel: ViewModel() {


    val data = MutableLiveData<List<EarthquakeModel>>()
    val getData: LiveData<List<EarthquakeModel>> get() = data

    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.geonames.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EarthquakeApiService::class.java)

    fun fetchData() {
        viewModelScope.launch {
            try {
                Log.e("Test", "EarthquakeViewModel no 1")
                val result = retrofit.fetchData()
                Log.e("Test", "EarthquakeViewModel no 2")
                data.postValue(result)
            } catch (ex: Exception) {
                Log.e("Test", "EarthquakeViewModel exception $ex")
            }
        }
    }
}