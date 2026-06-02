package com.unsa.e_commerce.ui.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unsa.e_commerce.data.repositories.UserRepository

object RegisterMessages {
    const val PASSWORD_MISMATCH_ERROR: String = "Las contraseñas no coinciden."
    const val USER_ALREADY_REGISTERED_ERROR: String = "El nombre de usuario ya está registrado."
}

@Composable
fun RegisterForm(modifier: Modifier = Modifier, onSuccessfulRegister: () -> Unit, onFailedRegister: () -> Unit) {
    var username: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    var passwordConfirmation: String by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = { username = it },
            label = {
                Text("Nombre de usuario:")
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            label = {
                Text("Contraseña:")
            }
        )
        Button(
            onClick = {
                if(UserRepository.login(username, password)) onSuccessfulRegister()
                else onFailedRegister()
            }
        ) {
            Text("Iniciar sesión")
        }
    }
}