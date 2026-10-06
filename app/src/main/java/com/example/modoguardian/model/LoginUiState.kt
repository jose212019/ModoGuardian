package com.example.modoguardian.model

data class LoginUiState(
    val email: String = "",
    val contrasena: String = "",
    val isLoading: Boolean = false,
    val isLoginEnabled: Boolean = false,
    val rolAsignado: String = ""
)