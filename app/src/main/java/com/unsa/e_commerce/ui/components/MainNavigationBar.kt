package com.unsa.e_commerce.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.unsa.e_commerce.navigation.Routes

@Composable
fun MainNavigationBar(currentRoute: String?, navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == Routes.HOME_SCREEN,
            onClick = { navController.navigate(Routes.HOME_SCREEN) },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.CART_SCREEN,
            onClick = { navController.navigate(Routes.CART_SCREEN) },
            icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Carrito") },
            label = { Text("Carrito") }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.LOGIN_SCREEN,
            onClick = { navController.navigate(Routes.LOGIN_SCREEN) },
            icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Cuenta") },
            label = { Text("Cuenta") }
        )
    }
}