package com.unsa.e_commerce.ui.view_models

import androidx.lifecycle.ViewModel
import com.unsa.e_commerce.data.repositories.UserRepository
import com.unsa.e_commerce.data.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    val currentUserId: Int?
        get() = sessionManager.currentUser?.id

    val isLoggedIn: Boolean
        get() = sessionManager.isUserLoggedIn()

    fun login(username: String, password: String): Boolean {
        val user = userRepository.findUser(username, password)
        if (user != null) {
            sessionManager.startSession(user)
            return true
        }
        return false
    }

    fun logout() {
        sessionManager.endSession()
    }

    fun register(username: String, password: String): Boolean {
        return userRepository.register(username, password)
    }
}
