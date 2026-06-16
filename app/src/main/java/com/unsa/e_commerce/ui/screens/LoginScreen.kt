package com.unsa.e_commerce.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.unsa.e_commerce.navigation.Routes
import com.unsa.e_commerce.ui.components.MyTopAppBar
import com.unsa.e_commerce.ui.forms.LoginForm
import com.unsa.e_commerce.ui.view_models.LoginViewModel
import com.unsa.e_commerce.ui.view_models.UserViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    userViewModel: UserViewModel,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    Scaffold(
        topBar = { MyTopAppBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(paddingValues = innerPadding).fillMaxSize().padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "LoginImage",
                tint = MaterialTheme.colorScheme.primary
            )
            loginViewModel.errorMessage?.let { message ->
                Text(text = message, color = Color.Red)
            }
            LoginForm(
                onLogin = { username, password ->
                    loginViewModel.login(username, password) { userId ->
                        userViewModel.setUserId(userId)
                        onLoginSuccess()
                    }
                }
            )
            TextButton(
                onClick = { navController.navigate(Routes.REGISTER_SCREEN) }
            ) {
                Text("¿No tienes cuenta? Regístrate aquí")
            }
        }
    }
}