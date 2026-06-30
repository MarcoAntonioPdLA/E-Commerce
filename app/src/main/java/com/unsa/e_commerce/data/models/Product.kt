package com.unsa.e_commerce.data.models

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val category: String
)