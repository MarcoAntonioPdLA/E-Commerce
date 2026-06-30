package com.unsa.e_commerce.ui.view_models

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unsa.e_commerce.data.analytics.CartLogger
import com.unsa.e_commerce.data.models.Product
import com.unsa.e_commerce.data.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class CartItem(
    val product: Product,
    val quantity: Int
)

@HiltViewModel
class CartViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartLogger: CartLogger
) : ViewModel() {
    
    var cartItems by mutableStateOf<List<CartItem>>(emptyList())
        private set

    val productsQuantities by derivedStateOf {
        cartItems.associate { it.product.id to it.quantity }
    }

    fun addProduct(product: Product) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            updateQuantity(product, existingItem.quantity + 1)
        } else {
            cartItems = cartItems + CartItem(product, 1)
            cartLogger.logAddToCart(product.id, 1)
        }
    }

    fun removeProduct(product: Product) {
        val productId = product.id
        val existingItem = cartItems.find { it.product.id == productId }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                updateQuantity(product, existingItem.quantity - 1)
            } else {
                cartItems = cartItems.filter { it.product.id != productId }
                cartLogger.logRemoveFromCart(productId)
            }
        }
    }
    
    fun updateQuantity(product: Product, newQuantity: Int) {
        val productId = product.id
        if (newQuantity <= 0) {
            cartItems = cartItems.filter { it.product.id != productId }
            cartLogger.logRemoveFromCart(productId)
        } else {
            val existingItem = cartItems.find { it.product.id == productId }
            if (existingItem == null) {
                cartItems = cartItems + CartItem(product, newQuantity)
                cartLogger.logAddToCart(productId, newQuantity)
            } else {
                val oldQuantity = existingItem.quantity
                cartItems = cartItems.map {
                    if (it.product.id == productId) it.copy(quantity = newQuantity) else it
                }

                if (newQuantity > oldQuantity) {
                    cartLogger.logAddToCart(productId, newQuantity - oldQuantity)
                }
            }
        }
    }

    fun getTotal(): Double {
        return cartItems.sumOf { it.product.price * it.quantity }
    }
    
    fun clearCart() {
        cartItems = emptyList()
        cartLogger.logClearCart()
    }

    fun completePurchase() {
        val total = getTotal()
        val count = cartItems.sumOf { it.quantity }
        cartLogger.logPurchase(total, count)
        clearCart()
    }
}