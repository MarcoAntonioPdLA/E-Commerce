package com.unsa.e_commerce.navigation

import androidx.compose.foundation.layout.fillMaxSize
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
import com.unsa.e_commerce.data.ProductRepository
import com.unsa.e_commerce.ui.components.MyNavigationBar
import com.unsa.e_commerce.ui.components.MyTopAppBar
import com.unsa.e_commerce.ui.screens.CartScreen
import com.unsa.e_commerce.ui.screens.HomeScreen
import com.unsa.e_commerce.ui.screens.ProductDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentRoute: String? = navController.currentBackStackEntryAsState().value?.destination?.route
    val showBottomBar: Boolean = currentRoute in listOf(Routes.HOME, Routes.CART)

    var productsQuantities by remember { mutableStateOf(mapOf<Int, Int>()) }

    Scaffold(
        bottomBar = {
            if(showBottomBar) {
                MyNavigationBar(currentRoute = currentRoute, navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME) {
                HomeScreen(
                    navController = navController,
                    productsQuantities = productsQuantities,
                    onProductQuantityChange = { productId, newQuantity ->
                        productsQuantities = productsQuantities + (productId to newQuantity)
                    }
                )
            }
            composable(Routes.CART) {
                CartScreen(
                    navController = navController,
                    productsQuantities = productsQuantities
                )
            }
            composable(
                Routes.PRODUCT_DETAIL,
                arguments = listOf(
                    navArgument("productId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                val product = ProductRepository.products.find { it.id == productId }
                if (product != null) {
                    ProductDetailScreen(product = product, navController = navController)
                }
            }
        }
    }
}