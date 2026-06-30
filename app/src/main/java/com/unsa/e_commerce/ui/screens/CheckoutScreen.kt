package com.unsa.e_commerce.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unsa.e_commerce.navigation.Routes
import com.unsa.e_commerce.ui.components.MyTopAppBar
import com.unsa.e_commerce.ui.view_models.CartViewModel

@Composable
fun CheckoutScreen(
    navController: NavController, 
    cartViewModel: CartViewModel
) {
    val checkoutItems = cartViewModel.cartItems
    val totalPrice = cartViewModel.getTotal()

    Scaffold(
        topBar = { MyTopAppBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Resumen de Compra",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(checkoutItems) { item ->
                val product = item.product
                val quantity = item.quantity
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${product.title} (x$quantity)",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "S/. %.2f".format(product.price * quantity),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            item {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total a pagar",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "S/. %.2f".format(totalPrice),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                CheckoutInfoSection(
                    icon = Icons.Default.Home,
                    title = "Dirección de envío",
                    subtitle = "Av. Principal 123, Arequipa"
                )
            }
            item {
                CheckoutInfoSection(
                    icon = Icons.Default.ShoppingCart,
                    title = "Método de pago",
                    subtitle = "Tarjeta Visa terminada en 4567"
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Button(
                    onClick = {
                        // Finalizar compra
                        cartViewModel.completePurchase()
                        navController.navigate(Routes.HOME_SCREEN) {
                            popUpTo(Routes.HOME_SCREEN) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Confirmar y Pagar", modifier = Modifier.padding(8.dp))
                }
            }
            item {
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Volver al Carrito", modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

@Composable
fun CheckoutInfoSection(icon: ImageVector, title: String, subtitle: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text(text = subtitle, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}