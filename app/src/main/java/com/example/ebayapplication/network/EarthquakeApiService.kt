package com.example.ebayapplication.network

import com.example.ebayapplication.model.Earthquakes
import retrofit2.http.GET

/**
 * author lgarg on 12/21/23.
 */
interface EarthquakeApiService {

    @GET("earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman")
    suspend fun fetchData(): Earthquakes
}