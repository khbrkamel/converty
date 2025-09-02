package com.kamel.projects.domain.usecase

import com.kamel.projects.domain.model.ConversionResult
import com.kamel.projects.domain.model.TemperatureUnit
import com.kamel.projects.domain.repository.ConversionRepository
import javax.inject.Inject

class ConvertTemperatureUseCase @Inject constructor(
    private val repository: ConversionRepository
) {
    operator fun invoke(value: Double, fromUnit: TemperatureUnit, toUnit: TemperatureUnit): ConversionResult {
        return repository.convertTemperature(value, fromUnit, toUnit)
    }
}
