package com.example.pustakaku.features.auth.domain.usecase

import com.example.pustakaku.features.auth.data.repository.AuthRepository

class SignUpUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String, name: String): Result<Unit> {
        return authRepository.registerAndSaveUser(email, password, name)
    }
}