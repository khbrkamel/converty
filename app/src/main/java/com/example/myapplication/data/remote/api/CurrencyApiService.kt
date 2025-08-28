package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.dto.CurrencyRatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    
    @GET("latest")
    suspend fun getLatestRates(
        @Query("apikey") apiKey: String,
        @Query("base") baseCurrency: String = "USD"
    ): Response<CurrencyRatesResponse>
    
    companion object {
        const val BASE_URL = "https://api.currencyfreaks.com/"
        const val API_KEY = "YOUR_API_KEY_HERE" // We'll use a free service for demo
    }
}
