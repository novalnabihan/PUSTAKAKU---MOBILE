package com.example.pustakaku.features.profile.domain.usecase

import com.example.pustakaku.features.profile.data.repository.ProfileRepository
import com.example.pustakaku.features.profile.domain.model.User

class GetUserUseCase(private val repository: ProfileRepository) {

    suspend fun execute(): User? {
        return repository.getCurrentUser()
    }
}