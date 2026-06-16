package com.unsa.e_commerce.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unsa.e_commerce.data.repositories.UserRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    var currentUserId by mutableStateOf<Int?>(null)
        private set

    fun setUserId(id: Int?) {
        currentUserId = id
    }

    val isLoggedIn: Boolean
        get() = currentUserId != null

    fun login(username: String, password: String): Boolean {
        val user = userRepository.findUser(username, password)
        if (user != null) {
            currentUserId = user.id
            return true
        }
        return false
    }

    fun logout() {
        currentUserId = null
    }

    fun register(username: String, password: String): Boolean {
        return userRepository.register(username, password)
    }
}