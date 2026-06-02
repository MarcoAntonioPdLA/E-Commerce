package com.unsa.e_commerce.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unsa.e_commerce.data.repositories.UserRepository

class LoginViewModel : ViewModel() {
    companion object {
        const val BAD_CREDENTIALS_ERROR: String = "Usuario o contraseña incorrectos."
    }
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun login(username: String, password: String, onSuccess: () -> Unit) {
        if (UserRepository.login(username, password)) {
            errorMessage = null
            onSuccess()
        } else {
            errorMessage = BAD_CREDENTIALS_ERROR
        }
    }
}