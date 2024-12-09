package com.example.pustakaku.features.auth.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pustakaku.features.auth.domain.usecase.SignInUseCase
import com.example.pustakaku.features.auth.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    val registrationEmail = mutableStateOf("")
    val registrationPassword = mutableStateOf("")
    val registrationConfirmPassword = mutableStateOf("")
    val registrationName = mutableStateOf("")
    val registrationPhone = mutableStateOf("")

    val isLoginSuccessful = mutableStateOf(false)
    val isRegistrationSuccessful = mutableStateOf(false)

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = signInUseCase.execute(email, password)
            _isLoading.value = false
            if (result.isSuccess) {
                onSuccess()
            } else {
                _errorMessage.value = result.exceptionOrNull()?.localizedMessage ?: "Unknown error"
            }
        }
    }

    fun register(email: String, password: String, name: String, phone: String, onSuccess: () -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = signUpUseCase.execute(email, password, name, phone)
            _isLoading.value = false
            if (result.isSuccess) {
                onSuccess()
            } else {
                _errorMessage.value = result.exceptionOrNull()?.localizedMessage ?: "Unknown error"
            }
        }
    }
}