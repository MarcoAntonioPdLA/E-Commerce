package com.unsa.e_commerce.data.analytics

import android.util.Log
import com.unsa.e_commerce.data.session.SessionManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartLogger @Inject constructor(
    private val sessionManager: SessionManager
) {
    fun logAddToCart(productId: Int, quantity: Int) {
        val userName = sessionManager.currentUser?.username ?: "Anónimo"
        Log.d("CartLogger", "Usuario $userName agregó $quantity del producto $productId al carrito")
    }

    fun logRemoveFromCart(productId: Int) {
        val userName = sessionManager.currentUser?.username ?: "Anónimo"
        Log.d("CartLogger", "Usuario $userName eliminó producto $productId del carrito")
    }

    fun logClearCart() {
        val userName = sessionManager.currentUser?.username ?: "Anónimo"
        Log.d("CartLogger", "Usuario $userName vació el carrito")
    }

    fun logPurchase(totalAmount: Double, itemCount: Int) {
        val userName = sessionManager.currentUser?.username ?: "Anónimo"
        Log.d("CartLogger", "Usuario $userName realizó una compra por S/. %.2f ($itemCount productos)".format(totalAmount))
    }
}
