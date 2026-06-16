package com.unsa.e_commerce.data.analytics

import android.util.Log
import com.unsa.e_commerce.data.session.SessionManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductVisitLogger @Inject constructor(
    private val sessionManager: SessionManager
) {

    private val visitCounts = mutableMapOf<Int, Int>()

    fun logVisit(productId: Int) {
        val currentCount = visitCounts.getOrDefault(productId, 0)
        visitCounts[productId] = currentCount + 1
        
        val userName = sessionManager.currentUser?.username ?: "Anónimo"
        Log.d("ProductVisitLogger", "Usuario $userName visitó producto $productId. Total visitas: ${visitCounts[productId]}")
    }

    fun getMostVisitedProductIds(): List<Int> {
        return visitCounts.toList()
            .sortedByDescending { it.second }
            .map { it.first }
    }
    
    fun getVisitCount(productId: Int): Int {
        return visitCounts.getOrDefault(productId, 0)
    }
}
