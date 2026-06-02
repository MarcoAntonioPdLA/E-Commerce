package com.unsa.e_commerce.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unsa.e_commerce.data.models.User
import com.unsa.e_commerce.data.repositories.UserRepository

class ProfileViewModel: ViewModel() {
    private val repository: UserRepository = UserRepository
    var users by mutableStateOf(emptyList<User>())
        private set

    init {
        loadUsers()
    }

    private fun loadUsers() {
        users = repository.getAllUsers()
    }
}