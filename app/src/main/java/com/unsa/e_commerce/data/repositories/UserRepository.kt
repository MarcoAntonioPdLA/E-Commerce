package com.unsa.e_commerce.data.repositories

import com.unsa.e_commerce.data.models.User
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object UserRepository {
    var currentUserId by mutableStateOf<Int?>(null)
    val isLoggedIn: Boolean get() = currentUserId != null

    private val users: List<User> = listOf(
        User(id = 1, username = "Usuario01", password = "12345"),
        User(id = 2, username = "Usuario02", password = "09876"),
    )

    fun getAllUsers(): List<User> {
        return users
    }

    fun login(username: String, password: String): Boolean {
        val user = users.find { it.username == username && it.password == password }
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