package com.unsa.e_commerce.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unsa.e_commerce.data.repositories.UserRepository

class UserViewModel: ViewModel() {
    var currentUserId by mutableStateOf<Int?>(null)
        private set

    val isLoggedIn: Boolean
        get() = currentUserId != null

    fun login(username: String, password: String): Boolean {
        val user = UserRepository.findUser(username, password)
        if (user != null) {
            currentUserId = user.id
            return true
        }
        return false
    }

    fun logout() {
        currentUserId = null
    }
}