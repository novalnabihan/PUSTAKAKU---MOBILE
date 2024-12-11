package com.example.pustakaku.features.profile.data.repository

import android.util.Log
import com.example.pustakaku.features.profile.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProfileRepository(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    suspend fun getCurrentUser(): User? {
        val currentUser = firebaseAuth.currentUser
        Log.d("ProfileRepository", "Current user: $currentUser")
        if (currentUser != null) {
            val userId = currentUser.uid
            val document = firestore.collection("users").document(userId).get().await()
            Log.d("ProfileRepository", "Document exists: ${document.exists()}")
            if (document.exists()) {
                val name = document.getString("name") ?: "Unknown"
                val email = document.getString("email") ?: "No email"
                Log.d("ProfileRepository", "User Data - Name: $name, Email: $email")
                return User(uid = userId, name = name, email = email)
            }
        }
        return null
    }

    fun logout() {
        firebaseAuth.signOut()
        Log.d("ProfileRepository", "User logged out")
    }
}