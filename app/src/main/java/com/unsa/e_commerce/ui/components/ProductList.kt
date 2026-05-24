package com.unsa.e_commerce.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.unsa.e_commerce.data.Product

@Composable
fun ProductList(products: List<Product>, quantities: Map<Int, Int>, onQuantityChange: (Int, Int) -> Unit) {
    LazyColumn {
        items(products) { product ->
            ProductCard(
                product = product,
                quantity = quantities[product.id] ?: 0,
                onQuantityChange = { newQuantity -> onQuantityChange(product.id, newQuantity) }
            )
        }
    }
}