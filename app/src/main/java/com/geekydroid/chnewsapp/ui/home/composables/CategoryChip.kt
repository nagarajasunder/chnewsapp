package com.geekydroid.chnewsapp.ui.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.InputChip
import androidx.tv.material3.InputChipDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.geekydroid.chnewsapp.core.Constants.categories
import com.geekydroid.chnewsapp.core.Constants.countryList


@Composable
fun CountryChipRow(modifier: Modifier = Modifier, selectedCountry:String, onCountryChange:(String) -> Unit) {

    LazyRow(
        modifier = modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(countryList.size) { index ->
            SelectableChip(
                isSelected = countryList[index] == selectedCountry,
                chipText = countryList[index]
            ) {
                onCountryChange(countryList[index])
            }
        }
    }
}

@Composable
fun CategoryChipRow(modifier: Modifier = Modifier, selectedCategory:String, onCategoryChange:(String) -> Unit) {

    LazyRow(
        modifier = modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories.size) { index ->
            SelectableChip(
                isSelected = categories[index] == selectedCategory,
                chipText = categories[index]
            ) {
                onCategoryChange(categories[index])
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SelectableChip(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    chipText: String,
    onClick: () -> Unit
) {

    InputChip(
        modifier = modifier,
        selected = isSelected,
        trailingIcon = {
            if (isSelected) {
                Icon(Icons.Default.Check, contentDescription = null)
            }
        },
        onClick = onClick,
        shape = InputChipDefaults.shape(hasAvatar = false, shape = RoundedCornerShape(16.dp)),
    ) {
        Text(text = chipText, style = MaterialTheme.typography.titleLarge)
    }
}

