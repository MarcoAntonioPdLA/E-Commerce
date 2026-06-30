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
import com.unsa.e_commerce.navigation.Routes
import com.unsa.e_commerce.ui.components.CartProductCard
import com.unsa.e_commerce.ui.components.MyTopAppBar
import com.unsa.e_commerce.ui.view_models.CartViewModel

@Composable
fun CartScreen(
    navController: NavController, 
    cartViewModel: CartViewModel
) {
    val cartItems = cartViewModel.cartItems
    val totalPrice = cartViewModel.getTotal()

    Scaffold(
        topBar = { MyTopAppBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartItems) { item ->
                    CartProductCard(
                        product = item.product,
                        quantity = item.quantity,
                        onQuantityChange = { newQuantity ->
                            cartViewModel.updateQuantity(item.product, newQuantity)
                        }
                    )
                }
            }
            Text(
                text = "Precio total: S/. %.2f".format(totalPrice)
            )
            Button(
                onClick = { navController.navigate(Routes.CHECKOUT_SCREEN) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                enabled = cartItems.isNotEmpty()
            ) {
                Text(text = "Comprar")
            }
        }
    }
}