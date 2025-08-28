package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.api.CurrencyApiService
import com.example.myapplication.domain.model.ConversionResult
import com.example.myapplication.domain.model.Currency
import com.example.myapplication.domain.repository.CurrencyRepository
import java.text.DecimalFormat
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

@Singleton
class CurrencyRepositoryImpl @Inject constructor(
    private val currencyApiService: CurrencyApiService
) : CurrencyRepository {
    
    private val decimalFormat = DecimalFormat("#.####")
    
    // For demo purposes, we'll use a fallback service with hardcoded rates
    // In a real app, you would use a proper API key from CurrencyFreaks or similar service
    private val fallbackRates = mapOf(
        "EUR" to 0.85,
        "TND" to 3.09,
        "CNY" to 7.23,
        "CAD" to 1.35,
        "NZD" to 1.67,
        "GBP" to 0.79,
        "JPY" to 149.50,
        "AUD" to 1.52,
        "CHF" to 0.89,
        "SEK" to 10.45,
        "NOK" to 10.89,
        "DKK" to 6.87,
        "PLN" to 4.05,
        "CZK" to 23.15,
        "HUF" to 360.25,
        "RUB" to 92.50,
        "INR" to 83.25,
        "KRW" to 1320.50,
        "SGD" to 1.35,
        "HKD" to 7.82,
        "THB" to 35.80,
        "MYR" to 4.68,
        "PHP" to 56.25,
        "IDR" to 15750.0,
        "VND" to 24350.0,
        "BRL" to 5.12,
        "MXN" to 17.85,
        "ARS" to 365.50,
        "CLP" to 925.0,
        "COP" to 4125.0,
        "PEN" to 3.75,
        "ZAR" to 18.95,
        "EGP" to 30.85,
        "MAD" to 10.15,
        "NGN" to 775.0,
        "KES" to 148.5,
        "GHS" to 12.35,
        "TRY" to 28.75,
        "ILS" to 3.68,
        "SAR" to 3.75,
        "AED" to 3.67,
        "QAR" to 3.64,
        "KWD" to 0.31,
        "BHD" to 0.38,
        "OMR" to 0.38,
        "JOD" to 0.71,
        "LBP" to 15000.0,
        "SYP" to 2512.0,
        "IQD" to 1310.0,
        "IRR" to 42000.0,
        "AFN" to 72.5,
        "PKR" to 285.0,
        "BDT" to 110.5,
        "LKR" to 325.0,
        "NPR" to 133.2,
        "BTN" to 83.25,
        "MMK" to 2095.0,
        "KHR" to 4125.0,
        "LAK" to 20500.0,
        "MNT" to 3450.0,
        "KZT" to 465.0,
        "UZS" to 12250.0,
        "KGS" to 89.5,
        "TJS" to 10.95,
        "TMT" to 3.50,
        "AZN" to 1.70,
        "GEL" to 2.68,
        "AMD" to 395.0,
        "BYN" to 2.58,
        "UAH" to 37.25,
        "MDL" to 18.15,
        "RON" to 4.58,
        "BGN" to 1.81,
        "HRK" to 6.95,
        "RSD" to 108.5,
        "BAM" to 1.81,
        "MKD" to 57.8,
        "ALL" to 95.5
    )
    
    override suspend fun convertCurrency(
        amount: Double,
        fromCurrency: Currency,
        toCurrency: Currency
    ): Result<ConversionResult> {
        return try {
            if (fromCurrency == toCurrency) {
                return Result.success(ConversionResult(amount, formatValue(amount)))
            }
            
            // Get exchange rates
            val ratesResult = getExchangeRates()
            if (ratesResult.isFailure) {
                return Result.failure(ratesResult.exceptionOrNull() ?: Exception("Failed to get exchange rates"))
            }
            
            val rates = ratesResult.getOrThrow()
            
            // Convert from base currency (USD) to target
            val convertedValue = when {
                fromCurrency == Currency.USD -> {
                    // From USD to other currency
                    amount * (rates[toCurrency.code] ?: 1.0)
                }
                toCurrency == Currency.USD -> {
                    // From other currency to USD
                    amount / (rates[fromCurrency.code] ?: 1.0)
                }
                else -> {
                    // From one currency to another via USD
                    val usdAmount = amount / (rates[fromCurrency.code] ?: 1.0)
                    usdAmount * (rates[toCurrency.code] ?: 1.0)
                }
            }
            
            Result.success(ConversionResult(convertedValue, formatValue(convertedValue)))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getExchangeRates(baseCurrency: Currency): Result<Map<String, Double>> {
        return try {
            // For demo purposes, we'll use fallback rates
            // In a real implementation, you would call the API:
            // val response = currencyApiService.getLatestRates(CurrencyApiService.API_KEY, baseCurrency.code)
            // if (response.isSuccessful && response.body() != null) {
            //     Result.success(response.body()!!.rates)
            // } else {
            //     Result.failure(Exception("API call failed: ${response.message()}"))
            // }
            
            Result.success(fallbackRates)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private fun formatValue(value: Double): String {
        return when {
            value == 0.0 -> "0"
            abs(value) >= 1000000 -> decimalFormat.format(value)
            abs(value) >= 1 -> {
                val formatted = decimalFormat.format(value)
                // Remove trailing zeros after decimal point
                if (formatted.contains('.')) {
                    formatted.trimEnd('0').trimEnd('.')
                } else {
                    formatted
                }
            }
            else -> decimalFormat.format(value)
        }
    }
}
