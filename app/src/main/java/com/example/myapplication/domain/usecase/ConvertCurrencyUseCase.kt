package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.ConversionResult
import com.example.myapplication.domain.model.Currency
import com.example.myapplication.domain.repository.CurrencyRepository
import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    suspend operator fun invoke(
        amount: Double,
        fromCurrency: Currency,
        toCurrency: Currency
    ): Result<ConversionResult> {
        return currencyRepository.convertCurrency(amount, fromCurrency, toCurrency)
    }
}
