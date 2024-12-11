package com.example.pustakaku.features.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pustakaku.features.profile.domain.usecase.GetUserUseCase
import com.example.pustakaku.features.profile.domain.usecase.LogoutUseCase

class ProfileViewModelFactory(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(getUserUseCase, logoutUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}