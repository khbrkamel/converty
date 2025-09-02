package com.kamel.projects.domain.repository

import com.kamel.projects.domain.model.ConversionResult
import com.kamel.projects.domain.model.Currency

interface CurrencyRepository {
    suspend fun convertCurrency(
        amount: Double,
        fromCurrency: Currency,
        toCurrency: Currency
    ): Result<ConversionResult>
    
    suspend fun getExchangeRates(baseCurrency: Currency = Currency.USD): Result<Map<String, Double>>
}
