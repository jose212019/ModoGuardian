package com.example.modoguardian.model

data class UsuarioUiState(
    val nombre: String = "",
    val correo: String = "",
    val telefono: String = "",
    // Variables para las validaciones
    val errorNombre: Boolean = false,
    val errorCorreo: Boolean = false,
    val errorTelefono: Boolean = false
)