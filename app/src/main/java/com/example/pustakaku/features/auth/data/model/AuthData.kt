package com.example.pustakaku.features.auth.data.model

data class AuthData(
    val email: String,
    val password: String,
    val name: String? = null,
)
