package com.unsa.e_commerce.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unsa.e_commerce.data.analytics.ProductVisitLogger
import com.unsa.e_commerce.data.models.Product
import com.unsa.e_commerce.data.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val visitLogger: ProductVisitLogger
) : ViewModel() {
    var product by mutableStateOf<Product?>(null)
        private set

    fun loadProduct(productId: Int) {
        product = repository.getProductById(productId)
        visitLogger.logVisit(productId)
    }
}