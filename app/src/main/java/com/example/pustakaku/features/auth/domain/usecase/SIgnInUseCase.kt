package com.example.pustakaku.features.auth.domain.usecase

import com.example.pustakaku.features.auth.data.repository.AuthRepository

class SignInUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): Result<Unit> {
        return authRepository.login(email, password)
    }
}