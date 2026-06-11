package com.unsa.e_commerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unsa.e_commerce.navigation.AppNavigation
import com.unsa.e_commerce.ui.screens.HomeScreen
import com.unsa.e_commerce.ui.theme.ECommerceTheme
import com.unsa.e_commerce.ui.view_models.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val userViewModel: UserViewModel = viewModel()
            ECommerceTheme {
                AppNavigation(userViewModel)
            }
        }
    }
}