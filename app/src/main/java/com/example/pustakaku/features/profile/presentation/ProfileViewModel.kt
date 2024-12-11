package com.example.pustakaku.features.profile.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pustakaku.features.profile.data.repository.ProfileRepository
import com.example.pustakaku.features.profile.domain.model.User
import com.example.pustakaku.features.profile.domain.usecase.GetUserUseCase
import com.example.pustakaku.features.profile.domain.usecase.LogoutUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Success(val user: User) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
    object LoggedOut : ProfileUiState()
}

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        loadUser()
    }

    private fun loadUser() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            viewModelScope.launch {
                getUserUseCase(currentUser.uid).collect { user ->
                    _uiState.value = ProfileUiState.Success(user)
                }
            }
        } else {
            _uiState.value = ProfileUiState.Error("User not logged in")
        }
    }

    fun logout() {
        viewModelScope.launch {
            val result = logoutUseCase()
            if (result.isSuccess) {
                _uiState.value = ProfileUiState.LoggedOut
            } else {
                _uiState.value = ProfileUiState.Error(result.exceptionOrNull()?.message ?: "Logout failed")
            }
        }
    }
}