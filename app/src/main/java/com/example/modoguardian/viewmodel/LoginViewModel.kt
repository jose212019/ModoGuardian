package com.example.modoguardian.viewmodel

import androidx.lifecycle.ViewModel
import com.example.modoguardian.model.LoginErrores
import com.example.modoguardian.model.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _errores = MutableStateFlow(LoginErrores())
    val errores: StateFlow<LoginErrores> = _errores.asStateFlow()

    fun onEmailChange(nuevoEmail: String) {
        _uiState.update { it.copy(email = nuevoEmail) }
        validarBoton()
    }

    fun onPasswordChange(nuevaContrasena: String) {
        _uiState.update { it.copy(contrasena = nuevaContrasena) }
        validarBoton()
    }

    private fun validarBoton() {
        val emailValido = _uiState.value.email.contains("@")
        val passValida = _uiState.value.contrasena.length >= 6
        _uiState.update { it.copy(isLoginEnabled = emailValido && passValida) }
    }

    fun validarFormulario(): Boolean {
        val email = _uiState.value.email
        val pass = _uiState.value.contrasena

        return when {
            email == "admin@guardian.test" && pass == "123456" -> {
                _uiState.update { it.copy(rolAsignado = "Admin") }
                _errores.update { it.copy(loginError = null) }
                true
            }
            email == "supervisor@guardian.test" && pass == "123456" -> {
                _uiState.update { it.copy(rolAsignado = "Supervisor") }
                _errores.update { it.copy(loginError = null) }
                true
            }
            email == "operador@guardian.test" && pass == "123456" -> {
                _uiState.update { it.copy(rolAsignado = "Operador") }
                _errores.update { it.copy(loginError = null) }
                true
            }
            else -> {
                _errores.update { it.copy(loginError = "Credenciales incorrectas") }
                false
            }
        }
    }
}