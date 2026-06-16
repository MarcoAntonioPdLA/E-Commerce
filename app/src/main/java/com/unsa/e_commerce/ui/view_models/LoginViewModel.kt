package com.unsa.e_commerce.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unsa.e_commerce.data.repositories.UserRepository
import com.unsa.e_commerce.data.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    companion object {
        const val BAD_CREDENTIALS_ERROR: String = "Usuario o contraseña incorrectos."
    }
    
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun login(username: String, password: String, onLoginSuccess: (Int) -> Unit) {
        val user = userRepository.findUser(username, password)
        if (user != null) {
            sessionManager.startSession(user)
            errorMessage = null
            onLoginSuccess(user.id)
        } else {
            errorMessage = BAD_CREDENTIALS_ERROR
        }
    }
}