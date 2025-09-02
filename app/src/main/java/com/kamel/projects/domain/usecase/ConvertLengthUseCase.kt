package com.kamel.projects.domain.usecase

import com.kamel.projects.domain.model.ConversionResult
import com.kamel.projects.domain.model.LengthUnit
import com.kamel.projects.domain.repository.ConversionRepository
import javax.inject.Inject

class ConvertLengthUseCase @Inject constructor(
    private val repository: ConversionRepository
) {
    operator fun invoke(value: Double, fromUnit: LengthUnit, toUnit: LengthUnit): ConversionResult {
        return repository.convertLength(value, fromUnit, toUnit)
    }
}
