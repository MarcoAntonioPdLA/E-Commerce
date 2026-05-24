package com.unsa.e_commerce.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unsa.e_commerce.data.ProductRepository
import com.unsa.e_commerce.ui.screens.HomeScreen
import com.unsa.e_commerce.ui.screens.ProductDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(navController = navController)
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