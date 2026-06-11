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
import com.unsa.e_commerce.data.repositories.UserRepository
import com.unsa.e_commerce.navigation.Routes
import com.unsa.e_commerce.ui.view_models.UserViewModel

@Composable
fun MainNavigationBar(
    currentRoute: String?,
    userViewModel: UserViewModel,
    navController: NavController
) {
    val productsRoutes: List<String> = listOf(Routes.HOME_SCREEN, Routes.PRODUCT_DETAIL_SCREEN)
    val cartRoutes: List<String> = listOf(Routes.CART_SCREEN, Routes.CHECKOUT_SCREEN)
    val accountRoutes: List<String> = listOf(Routes.LOGIN_SCREEN, Routes.REGISTER_SCREEN, Routes.PROFILE_SCREEN)
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute in productsRoutes,
            onClick = {
                if(currentRoute != Routes.HOME_SCREEN) {
                    navController.navigate(Routes.HOME_SCREEN)
                }
            },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") }
        )

        NavigationBarItem(
            selected = currentRoute in cartRoutes,
            onClick = {
                if(currentRoute != Routes.CART_SCREEN) {
                    navController.navigate(Routes.CART_SCREEN)
                }
            },
            icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Carrito") },
            label = { Text("Carrito") }
        )

        NavigationBarItem(
            selected = currentRoute in accountRoutes,
            onClick = {
                if (userViewModel.isLoggedIn) {
                    navController.navigate(Routes.PROFILE_SCREEN)
                } else {
                    navController.navigate(Routes.LOGIN_SCREEN)
                }
            },
            icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Cuenta") },
            label = { Text("Cuenta") }
        )
    }
}