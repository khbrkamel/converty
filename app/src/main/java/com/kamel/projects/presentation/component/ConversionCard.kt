package com.kamel.projects.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kamel.projects.domain.model.ConversionType
import com.kamel.projects.domain.model.Currency
import com.kamel.projects.domain.model.LengthUnit
import com.kamel.projects.domain.model.TemperatureUnit

@Composable
fun ConversionCard(
    conversionType: ConversionType,
    inputValue: String,
    outputValue: String,
    isInputValid: Boolean,
    errorMessage: String?,
    isLoading: Boolean = false,
    fromLengthUnit: LengthUnit,
    toLengthUnit: LengthUnit,
    fromTemperatureUnit: TemperatureUnit,
    toTemperatureUnit: TemperatureUnit,
    fromCurrency: Currency = Currency.USD,
    toCurrency: Currency = Currency.EUR,
    onInputValueChange: (String) -> Unit,
    onFromLengthUnitChange: (LengthUnit) -> Unit,
    onToLengthUnitChange: (LengthUnit) -> Unit,
    onFromTemperatureUnitChange: (TemperatureUnit) -> Unit,
    onToTemperatureUnitChange: (TemperatureUnit) -> Unit,
    onFromCurrencyChange: (Currency) -> Unit = {},
    onToCurrencyChange: (Currency) -> Unit = {},
    onSwapUnits: () -> Unit,
    onClearInput: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header with title and clear button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${conversionType.displayName} Converter",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                IconButton(
                    onClick = onClearInput,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear input",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // From section
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "From",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    OutlinedTextField(
                        value = inputValue,
                        onValueChange = onInputValueChange,
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Enter value") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        isError = !isInputValid,
                        supportingText = if (errorMessage != null) {
                            { Text(text = errorMessage) }
                        } else null,
                        singleLine = true
                    )
                    
                    when (conversionType) {
                        ConversionType.LENGTH -> {
                            LengthUnitDropdown(
                                selectedUnit = fromLengthUnit,
                                onUnitSelected = onFromLengthUnitChange,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        ConversionType.TEMPERATURE -> {
                            TemperatureUnitDropdown(
                                selectedUnit = fromTemperatureUnit,
                                onUnitSelected = onFromTemperatureUnitChange,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        ConversionType.CURRENCY -> {
                            CurrencyDropdown(
                                selectedCurrency = fromCurrency,
                                onCurrencySelected = onFromCurrencyChange,
                                modifier = Modifier.weight(1f),
                                label = "From"
                            )
                        }
                    }
                }
            }

            // Swap button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                FilledIconButton(
                    onClick = onSwapUnits,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.SwapVert,
                        contentDescription = "Swap units"
                    )
                }
            }

            // To section
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "To",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            } else {
                                Text(
                                    text = outputValue.ifEmpty { "0" },
                                    style = MaterialTheme.typography.headlineMedium,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    
                    when (conversionType) {
                        ConversionType.LENGTH -> {
                            LengthUnitDropdown(
                                selectedUnit = toLengthUnit,
                                onUnitSelected = onToLengthUnitChange,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        ConversionType.TEMPERATURE -> {
                            TemperatureUnitDropdown(
                                selectedUnit = toTemperatureUnit,
                                onUnitSelected = onToTemperatureUnitChange,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        ConversionType.CURRENCY -> {
                            CurrencyDropdown(
                                selectedCurrency = toCurrency,
                                onCurrencySelected = onToCurrencyChange,
                                modifier = Modifier.weight(1f),
                                label = "To"
                            )
                        }
                    }
                }
            }
        }
    }
}
