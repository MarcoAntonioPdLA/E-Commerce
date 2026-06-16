package com.unsa.e_commerce.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unsa.e_commerce.ui.components.MainNavigationBar
import com.unsa.e_commerce.ui.screens.CartScreen
import com.unsa.e_commerce.ui.screens.CheckoutScreen
import com.unsa.e_commerce.ui.screens.HomeScreen
import com.unsa.e_commerce.ui.screens.LoginScreen
import com.unsa.e_commerce.ui.screens.ProfileScreen
import com.unsa.e_commerce.ui.screens.RegisterScreen
import com.unsa.e_commerce.ui.screens.ProductDetailScreen
import com.unsa.e_commerce.ui.view_models.CartViewModel
import com.unsa.e_commerce.ui.view_models.UserViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentRoute: String? = navController.currentBackStackEntryAsState().value?.destination?.route
    
    // Scoping ViewModels to the NavHost or Activity as needed.
    // UserViewModel is often global, CartViewModel is shared here.
    val userViewModel: UserViewModel = hiltViewModel()
    val cartViewModel: CartViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            MainNavigationBar(
                currentRoute = currentRoute,
                userViewModel = userViewModel,
                navController = navController
            )
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
                    cartViewModel = cartViewModel
                )
            }
            composable(Routes.CART_SCREEN) {
                CartScreen(
                    navController = navController,
                    cartViewModel = cartViewModel
                )
            }
            composable(Routes.CHECKOUT_SCREEN) {
                CheckoutScreen(
                    navController = navController,
                    cartViewModel = cartViewModel
                )
            }
            composable(Routes.LOGIN_SCREEN) {
                LoginScreen(
                    navController = navController,
                    userViewModel = userViewModel,
                    onLoginSuccess = {
                        navController.navigate(Routes.PROFILE_SCREEN) {
                            popUpTo(Routes.LOGIN_SCREEN) { inclusive = true }
                        }
                    }
                )
            }
            composable(Routes.REGISTER_SCREEN) {
                RegisterScreen(navController = navController)
            }
            composable(Routes.PROFILE_SCREEN) {
                ProfileScreen(
                    navController = navController,
                    userViewModel = userViewModel
                )
            }
            composable(
                Routes.PRODUCT_DETAIL_SCREEN,
                arguments = listOf(
                    navArgument("productId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                ProductDetailScreen(
                    productId = productId, 
                    navController = navController,
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}