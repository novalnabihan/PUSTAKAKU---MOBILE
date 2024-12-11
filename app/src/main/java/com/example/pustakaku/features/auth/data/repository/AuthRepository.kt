package com.example.pustakaku.features.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.tasks.await

class AuthRepository(private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(),
                     private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()) {

    suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(email: String, password: String): Result<Unit> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun registerAndSaveUser(email: String, password: String, name: String): Result<Unit>{
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            authResult.user?.let  { user ->
                val userData = hashMapOf(
                    "name" to name,
                    "email" to email
                )
                firestore.collection("users").document(user.uid).set(userData).await()
            }
            Result.success(Unit)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun getUserName(uid: String): Result<String>{
        return try {
            val document = firestore.collection("users").document(uid).get().await()
            if (document.exists()){
                val name = document.getString("name") ?: "Users"
                Result.success(name)
            } else {
                Result.failure(Exception("User not found"))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}
