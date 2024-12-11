package com.example.pustakaku.features.profile.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pustakaku.features.profile.data.repository.ProfileRepository
import com.example.pustakaku.features.profile.domain.model.User
import com.example.pustakaku.features.profile.domain.usecase.GetUserUseCase
import com.example.pustakaku.features.profile.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState

    fun loadUser() {
        if (_userState.value == null) {
            viewModelScope.launch {
                Log.d("ProfileViewModel", "Loading user...")
                val user = getUserUseCase.execute()// Memanggil data dari Firestore
                Log.d("ProfileViewModel", "User loaded: $user")
                _userState.value = user
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            Log.d("ProfileViewModel", "Logout dipanggil")
            logoutUseCase.execute()
            Log.d("ProfileViewModel", "Logout selesai")
        }
    }
}