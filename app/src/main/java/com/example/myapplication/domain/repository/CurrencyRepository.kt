package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.ConversionResult
import com.example.myapplication.domain.model.Currency

interface CurrencyRepository {
    suspend fun convertCurrency(
        amount: Double,
        fromCurrency: Currency,
        toCurrency: Currency
    ): Result<ConversionResult>
    
    suspend fun getExchangeRates(baseCurrency: Currency = Currency.USD): Result<Map<String, Double>>
}
