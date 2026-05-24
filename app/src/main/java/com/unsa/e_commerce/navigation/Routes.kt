package com.unsa.e_commerce.navigation

object Routes {
    const val HOME = "home"
    const val CART = "cart"
    const val PRODUCT_DETAIL = "product/{productId}"
    fun productDetail(productId: Int) = "product/$productId"
}