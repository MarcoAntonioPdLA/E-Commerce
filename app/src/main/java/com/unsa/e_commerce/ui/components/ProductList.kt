package com.unsa.e_commerce.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

@Composable
fun ProductList(products: List<String>) {
    LazyColumn {
        items(products) { product ->
            ProductCard(
                name = product,
                price = 99.99
            )
        }
    }
}