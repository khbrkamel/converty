package com.kamel.projects.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyRatesResponse(
    @SerialName("date")
    val date: String,
    @SerialName("base")
    val base: String,
    @SerialName("rates")
    val rates: Map<String, Double>
)
