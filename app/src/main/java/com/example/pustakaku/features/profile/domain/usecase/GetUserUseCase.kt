package com.example.pustakaku.features.profile.domain.usecase

import com.example.pustakaku.features.profile.data.repository.ProfileRepository
import com.example.pustakaku.features.profile.domain.model.User
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val repository: ProfileRepository) {
    operator fun invoke(userId: String): Flow<User> {
        return repository.getUser(userId)
    }
}