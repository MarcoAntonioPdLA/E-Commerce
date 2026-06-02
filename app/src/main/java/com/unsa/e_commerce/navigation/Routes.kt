package com.unsa.e_commerce.navigation

object Routes {
    const val HOME_SCREEN = "home"
    const val CART_SCREEN = "cart"
    const val LOGIN_SCREEN = "login"
    const val REGISTER_SCREEN = "register"
    const val PROFILE_SCREEN = "profile"
    const val CHECKOUT_SCREEN = "checkout"
    const val PRODUCT_DETAIL_SCREEN = "product/{productId}"
    fun productDetail(productId: Int) = "product/$productId"
}