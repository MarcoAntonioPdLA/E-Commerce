package com.unsa.e_commerce.data.repositories

import com.unsa.e_commerce.data.models.User
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor() {
    private val users: MutableList<User> = mutableListOf(
        User(id = 1, username = "Usuario01", password = "12345"),
        User(id = 2, username = "Usuario02", password = "09876"),
    )

    fun findUser(username: String, password: String): User? {
        return users.find { it.username == username && it.password == password }
    }

    fun login(username: String, password: String): Boolean {
        return findUser(username, password) != null
    }

    fun getAllUsers(): List<User> {
        return users.toList()
    }

    fun register(username: String, password: String): Boolean {
        if (users.any { it.username == username }) return false
        val newId = (users.maxOfOrNull { it.id } ?: 0) + 1
        users.add(User(id = newId, username = username, password = password))
        return true
    }
}