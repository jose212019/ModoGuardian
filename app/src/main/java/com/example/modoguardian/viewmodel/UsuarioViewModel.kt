package com.example.modoguardian.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.modoguardian.model.UsuarioUiState

class UsuarioViewModel : ViewModel() {

    // Estado observable del formulario
    var uiState by mutableStateOf(UsuarioUiState())
        private set

    // Actualizar nombre
    fun onNombreChanged(nuevoNombre: String) {
        uiState = uiState.copy(nombre = nuevoNombre, errorNombre = nuevoNombre.isBlank())
    }

    // Actualizar correo
    fun onCorreoChanged(nuevoCorreo: String) {
        uiState = uiState.copy(correo = nuevoCorreo, errorCorreo = nuevoCorreo.isBlank())
    }

    // Actualizar teléfono
    fun onTelefonoChanged(nuevoTelefono: String) {
        uiState = uiState.copy(telefono = nuevoTelefono, errorTelefono = nuevoTelefono.isBlank())
    }

    // Función para validar al presionar un botón de guardar/enviar
    fun validarFormulario(): Boolean {
        val hayErrorNombre = uiState.nombre.isBlank()
        val hayErrorCorreo = uiState.correo.isBlank()
        val hayErrorTelefono = uiState.telefono.isBlank()

        uiState = uiState.copy(
            errorNombre = hayErrorNombre,
            errorCorreo = hayErrorCorreo,
            errorTelefono = hayErrorTelefono
        )

        return !hayErrorNombre && !hayErrorCorreo && !hayErrorTelefono
    }
}