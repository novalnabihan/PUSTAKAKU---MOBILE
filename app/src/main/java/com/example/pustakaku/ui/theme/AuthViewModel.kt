package com.example.pustakaku.ui.theme

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf("")

    // Mutable states for registration fields and status
    val registrationEmail = mutableStateOf("")
    val registrationPassword = mutableStateOf("")
    val registrationConfirmPassword = mutableStateOf("")
    val registrationName = mutableStateOf("")
    val registrationPhone = mutableStateOf("")

    // Status flags
    val isLoginSuccessful = mutableStateOf(false)
    val isRegistrationSuccessful = mutableStateOf(false)

    fun login(navController: NavController) {
        isLoading.value = true
        errorMessage.value = ""

        viewModelScope.launch {
            // Simulasi proses login
            delay(1000)
            if (email.value == "dummy@example.com" && password.value == "password") {
                isLoginSuccessful.value = true
                errorMessage.value = ""
                navController.navigate("Home")
            } else {
                isLoginSuccessful.value = false
                errorMessage.value = "Invalid email or password"
            }
            isLoading.value = false
        }
    }

    fun register(navController: NavController) {
        isLoading.value = true
        errorMessage.value = ""

        viewModelScope.launch {
            // Simulate a network request
            delay(1000)
            if (registrationPassword.value == registrationConfirmPassword.value) {
                // Assume registration was successful
                isRegistrationSuccessful.value = true
                errorMessage.value = ""
                navController.navigate("Home")
            } else {
                isRegistrationSuccessful.value = false
                errorMessage.value = "Password and confirm password do not match"
            }
            isLoading.value = false
        }
    }

    // Function to reset login state
    fun resetLoginState() {
        isLoginSuccessful.value = false
        email.value = ""
        password.value = ""
        errorMessage.value = ""
    }

    // Function to reset registration state
    fun resetRegistrationState() {
        isRegistrationSuccessful.value = false
        registrationEmail.value = ""
        registrationPassword.value = ""
        registrationConfirmPassword.value = ""
        registrationName.value = ""
        registrationPhone.value = ""
        errorMessage.value = ""
    }
}

