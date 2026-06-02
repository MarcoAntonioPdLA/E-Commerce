package com.unsa.e_commerce.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unsa.e_commerce.data.models.Product
import com.unsa.e_commerce.data.repositories.ProductRepository
import com.unsa.e_commerce.navigation.Routes
import com.unsa.e_commerce.ui.components.CartProductCard
import com.unsa.e_commerce.ui.components.MyTopAppBar

@Composable
fun CartScreen(navController: NavController, productsQuantities: Map<Int, Int>) {
    val cartProducts: List<Product> = ProductRepository.getAllProducts().filter { product ->
        (productsQuantities[product.id] ?: 0) >= 1
    }
    val totalPrice: Double = calculateTotalPrice(productsQuantities)

    Scaffold(
        topBar = { MyTopAppBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartProducts) { product ->
                    CartProductCard(
                        product = product,
                        quantity = productsQuantities[product.id] ?: 0
                    )
                }
            }
            Text(
                text =  "Precio total: S/. %.2f".format(totalPrice)
            )
            Button(
                onClick = { navController.navigate(Routes.CHECKOUT_SCREEN) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                Text(text = "Comprar")
            }
        }
    }
}

fun calculateTotalPrice(productsQuantities: Map<Int, Int>): Double {
    return productsQuantities.entries.sumOf { (productId, quantity) ->
        val product = ProductRepository.getAllProducts().find { it.id == productId }
        (product?.price ?: 0.0) * quantity
    }
}