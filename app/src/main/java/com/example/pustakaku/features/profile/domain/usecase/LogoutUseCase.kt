package com.example.pustakaku.features.profile.domain.usecase

import com.example.pustakaku.features.profile.data.repository.ProfileRepository

class LogoutUseCase(private val repository: ProfileRepository) {

    fun execute() {
        repository.logout()
    }
}