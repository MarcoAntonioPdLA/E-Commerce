package com.unsa.e_commerce.data.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val image: Int,
    val price: Double,
    var quantityOnCart: Int = 0
)