package com.unsa.e_commerce.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unsa.e_commerce.ui.components.MyTopAppBar
import com.unsa.e_commerce.ui.forms.LoginForm

@Composable
fun LoginScreen(navController: NavController) {
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
                contentDescription = "LoginImage"
            )
            LoginForm(
                onSuccessfulLogin = {}, //Cambiar a pantalla de perfil
                onFailedLogin = {}, //Mostrar mensaje de error
            )
            //Botón para ir a registro
        }
    }
}