package com.unsa.e_commerce.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unsa.e_commerce.data.models.Product
import com.unsa.e_commerce.data.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    
    // productId to quantity
    var productsQuantities by mutableStateOf(mapOf<Int, Int>())
        private set

    fun addProduct(productId: Int) {
        val currentQuantity = productsQuantities[productId] ?: 0
        productsQuantities = productsQuantities + (productId to (currentQuantity + 1))
    }

    fun removeProduct(productId: Int) {
        val currentQuantity = productsQuantities[productId] ?: 0
        if (currentQuantity > 1) {
            productsQuantities = productsQuantities + (productId to (currentQuantity - 1))
        } else {
            productsQuantities = productsQuantities - productId
        }
    }
    
    fun updateQuantity(productId: Int, newQuantity: Int) {
        if (newQuantity <= 0) {
            productsQuantities = productsQuantities - productId
        } else {
            productsQuantities = productsQuantities + (productId to newQuantity)
        }
    }

    fun getCartItems(): List<Pair<Product, Int>> {
        return productsQuantities.mapNotNull { (id, quantity) ->
            productRepository.getProductById(id)?.let { it to quantity }
        }
    }

    fun getTotal(): Double {
        return getCartItems().sumOf { (product, quantity) -> product.price * quantity }
    }
    
    fun clearCart() {
        productsQuantities = emptyMap()
    }
}