package com.unsa.e_commerce.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unsa.e_commerce.data.repository.UserRepository
import com.unsa.e_commerce.navigation.Routes
import com.unsa.e_commerce.ui.components.MyTopAppBar

@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = { MyTopAppBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Picture",
                modifier = Modifier.size(120.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            
            Text(
                text = "Bienvenido a tu perfil",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Text(
                text = "ID de Usuario: ${UserRepository.currentUserId}",
                style = MaterialTheme.typography.bodyLarge
            )

            Button(
                onClick = {
                    UserRepository.logout()
                    navController.navigate(Routes.LOGIN_SCREEN) {
                        popUpTo(Routes.PROFILE_SCREEN) { inclusive = true }
                    }
                },
                modifier = Modifier.padding(top = 32.dp)
            ) {
                Text("Cerrar Sesión")
            }
        }
    }
}
