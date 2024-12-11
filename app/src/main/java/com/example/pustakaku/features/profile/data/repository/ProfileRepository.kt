package com.example.pustakaku.features.profile.data.repository

import android.util.Log
import com.example.pustakaku.features.profile.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getUser(userId: String): Flow<User>
    suspend fun logout(): Result<Unit>
}