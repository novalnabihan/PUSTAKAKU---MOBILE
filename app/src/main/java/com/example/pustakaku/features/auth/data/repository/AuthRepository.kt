package com.example.pustakaku.features.auth.data.repository


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository(private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(), private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()) {

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

    suspend fun registerAndSaveUser(email: String, password: String, name: String, phone: String): Result<Unit>{
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            authResult.user?.let  { user ->
                val userData = hashMapOf(
                    "name" to name,
                    "email" to email,
                    "phone" to phone
                )
                firestore.collection("users").document(user.uid).set(userData).await()
            }
            Result.success(Unit)
        } catch (e: Exception){
            Result.failure(e)
        }
    }



}