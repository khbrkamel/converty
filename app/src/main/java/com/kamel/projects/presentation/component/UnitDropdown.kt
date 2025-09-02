package com.kamel.projects.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kamel.projects.domain.model.LengthUnit
import com.kamel.projects.domain.model.TemperatureUnit

@Composable
fun <T> UnitDropdown(
    selectedUnit: T,
    units: List<T>,
    onUnitSelected: (T) -> Unit,
    getDisplayName: (T) -> String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = getDisplayName(selectedUnit),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown arrow"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(200.dp)
        ) {
            units.forEach { unit ->
                DropdownMenuItem(
                    text = { 
                        Text(
                            text = getDisplayName(unit),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    onClick = {
                        onUnitSelected(unit)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun LengthUnitDropdown(
    selectedUnit: LengthUnit,
    onUnitSelected: (LengthUnit) -> Unit,
    modifier: Modifier = Modifier
) {
    UnitDropdown(
        selectedUnit = selectedUnit,
        units = LengthUnit.entries,
        onUnitSelected = onUnitSelected,
        getDisplayName = { "${it.displayName} (${it.symbol})" },
        modifier = modifier
    )
}

@Composable
fun TemperatureUnitDropdown(
    selectedUnit: TemperatureUnit,
    onUnitSelected: (TemperatureUnit) -> Unit,
    modifier: Modifier = Modifier
) {
    UnitDropdown(
        selectedUnit = selectedUnit,
        units = TemperatureUnit.entries,
        onUnitSelected = onUnitSelected,
        getDisplayName = { "${it.displayName} (${it.symbol})" },
        modifier = modifier
    )
}
