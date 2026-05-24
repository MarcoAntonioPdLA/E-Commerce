package com.unsa.e_commerce.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.unsa.e_commerce.data.Product

@Composable
fun ProductList(products: List<Product>, quantities: Map<Int, Int>, onProductQuantityChange: (Int, Int) -> Unit, onProductClick: (Product) -> Unit) {
    LazyColumn {
        items(products) { product ->
            ProductCard(
                onClick = { onProductClick(product) },
                product = product,
                quantity = quantities[product.id] ?: 0,
                onQuantityChange = { newQuantity -> onProductQuantityChange(product.id, newQuantity) }
            )
        }
    }
}