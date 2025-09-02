package com.kamel.projects.domain.usecase

import com.kamel.projects.domain.model.ConversionResult
import com.kamel.projects.domain.model.Currency
import com.kamel.projects.domain.repository.CurrencyRepository
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
