package com.unsa.e_commerce.data.repositories

import com.unsa.e_commerce.data.models.User
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object UserRepository {
    private val users: List<User> = listOf(
        User(id = 1, username = "Usuario01", password = "12345"),
        User(id = 2, username = "Usuario02", password = "09876"),
    )

    fun findUser(username: String, password: String): User? {
        return users.find { it.username == username && it.password == password }
    }
}