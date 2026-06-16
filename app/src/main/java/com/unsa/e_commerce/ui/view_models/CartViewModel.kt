package com.unsa.e_commerce.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unsa.e_commerce.data.analytics.CartLogger
import com.unsa.e_commerce.data.models.Product
import com.unsa.e_commerce.data.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartLogger: CartLogger
) : ViewModel() {
    
    // productId to quantity
    var productsQuantities by mutableStateOf(mapOf<Int, Int>())
        private set

    fun addProduct(productId: Int) {
        val currentQuantity = productsQuantities[productId] ?: 0
        productsQuantities = productsQuantities + (productId to (currentQuantity + 1))
        cartLogger.logAddToCart(productId, 1)
    }

    fun removeProduct(productId: Int) {
        val currentQuantity = productsQuantities[productId] ?: 0
        if (currentQuantity > 1) {
            productsQuantities = productsQuantities + (productId to (currentQuantity - 1))
        } else {
            productsQuantities = productsQuantities - productId
        }
        cartLogger.logRemoveFromCart(productId)
    }
    
    fun updateQuantity(productId: Int, newQuantity: Int) {
        val currentQuantity = productsQuantities[productId] ?: 0
        if (newQuantity <= 0) {
            productsQuantities = productsQuantities - productId
            cartLogger.logRemoveFromCart(productId)
        } else {
            productsQuantities = productsQuantities + (productId to newQuantity)
            if (newQuantity > currentQuantity) {
                cartLogger.logAddToCart(productId, newQuantity - currentQuantity)
            }
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
        cartLogger.logClearCart()
    }

    fun completePurchase() {
        val total = getTotal()
        val count = productsQuantities.values.sum()
        cartLogger.logPurchase(total, count)
        clearCart()
    }
}