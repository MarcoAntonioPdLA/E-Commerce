package com.unsa.e_commerce.data.session

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.unsa.e_commerce.data.analytics.SessionLogger
import com.unsa.e_commerce.data.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val sessionLogger: SessionLogger
) {
    var currentUser by mutableStateOf<User?>(null)
        private set

    fun startSession(user: User) {
        currentUser = user
        sessionLogger.logLogin(user.id, user.username)
    }

    fun endSession() {
        val user = currentUser
        sessionLogger.logLogout(user?.id, user?.username)
        currentUser = null
    }

    fun isUserLoggedIn(): Boolean {
        return currentUser != null
    }
}
