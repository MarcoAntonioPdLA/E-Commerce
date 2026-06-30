package com.unsa.e_commerce.data.remote

import com.unsa.e_commerce.data.models.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}