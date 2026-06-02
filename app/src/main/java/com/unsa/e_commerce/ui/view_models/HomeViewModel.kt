package com.unsa.e_commerce.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.unsa.e_commerce.data.models.Product
import com.unsa.e_commerce.data.repositories.ProductRepository

class HomeViewModel: ViewModel() {
    private val repository: ProductRepository = ProductRepository
    var products by mutableStateOf(emptyList<Product>())
        private set

    init {
        loadProducts()
    }

    private fun loadProducts() {
        products = repository.getAllProducts()
    }
}