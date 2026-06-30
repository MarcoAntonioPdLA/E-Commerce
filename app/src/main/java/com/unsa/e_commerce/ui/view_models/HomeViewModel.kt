package com.unsa.e_commerce.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.e_commerce.data.models.Product
import com.unsa.e_commerce.data.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    var products by mutableStateOf(emptyList<Product>())
        private set

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                products = repository.getAllProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}