package com.unsa.e_commerce.data.analytics

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionLogger @Inject constructor() {

    fun logLogin(userId: Int, username: String) {
        Log.d("SessionLogger", "Evento: Inicio de sesión | Usuario ID: $userId | Nombre: $username")
    }

    fun logLogout(userId: Int?, username: String?) {
        Log.d("SessionLogger", "Evento: Cierre de sesión | Usuario ID: ${userId ?: "N/A"} | Nombre: ${username ?: "Anónimo"}")
    }
}
