package com.example.pustakaku.features.profile.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.pustakaku.features.profile.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ProfileRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ProfileRepository {

    override fun getUser(userId: String): Flow<User> = flow {
        val doc = firestore.collection("users").document(userId).get().await()
        if (doc.exists()) {
            val name = doc.getString("name") ?: "Unknown"
            val email = doc.getString("email") ?: "Unknown"
            emit(User(id = userId, name = name, email = email))
        } else {
            emit(User(id = userId, name = "Unknown", email = "Unknown"))
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            auth.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}