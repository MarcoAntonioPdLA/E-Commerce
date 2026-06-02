package com.unsa.e_commerce.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unsa.e_commerce.data.repository.ProductRepository
import com.unsa.e_commerce.ui.components.MainNavigationBar
import com.unsa.e_commerce.ui.screens.CartScreen
import com.unsa.e_commerce.ui.screens.CheckoutScreen
import com.unsa.e_commerce.ui.screens.HomeScreen
import com.unsa.e_commerce.ui.screens.LoginScreen
import com.unsa.e_commerce.ui.screens.ProfileScreen
import com.unsa.e_commerce.ui.screens.RegisterScreen
import com.unsa.e_commerce.ui.screens.ProductDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentRoute: String? = navController.currentBackStackEntryAsState().value?.destination?.route
    
    // Agregamos las nuevas rutas para que la barra inferior sea visible en ellas
    val showBottomBar: Boolean = currentRoute in listOf(
        Routes.HOME_SCREEN, 
        Routes.CART_SCREEN, 
        Routes.LOGIN_SCREEN,
        Routes.REGISTER_SCREEN,
        Routes.PROFILE_SCREEN,
        Routes.CHECKOUT_SCREEN
    )

    var productsQuantities by remember { mutableStateOf(mapOf<Int, Int>()) }

    Scaffold(
        bottomBar = {
            if(showBottomBar) {
                MainNavigationBar(currentRoute = currentRoute, navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME_SCREEN,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME_SCREEN) {
                HomeScreen(
                    navController = navController,
                    productsQuantities = productsQuantities,
                    onProductQuantityChange = { productId, newQuantity ->
                        productsQuantities = productsQuantities + (productId to newQuantity)
                    }
                )
            }
            composable(Routes.CART_SCREEN) {
                CartScreen(
                    navController = navController,
                    productsQuantities = productsQuantities
                )
            }
            composable(Routes.CHECKOUT_SCREEN) {
                CheckoutScreen(
                    navController = navController,
                    productsQuantities = productsQuantities
                )
            }
            composable(Routes.LOGIN_SCREEN) {
                LoginScreen(navController = navController)
            }
            composable(Routes.REGISTER_SCREEN) {
                RegisterScreen(navController = navController)
            }
            composable(Routes.PROFILE_SCREEN) {
                ProfileScreen(navController = navController)
            }
            composable(
                Routes.PRODUCT_DETAIL_SCREEN,
                arguments = listOf(
                    navArgument("productId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                val product = ProductRepository.getProductById(productId)
                if (product != null) {
                    ProductDetailScreen(product = product, navController = navController)
                }
            }
        }
    }
}