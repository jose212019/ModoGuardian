package com.example.modoguardian.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.modoguardian.viewmodel.UsuarioViewModel

@Composable
fun RegistroScreen(viewModel: UsuarioViewModel) {
    val uiState = viewModel.uiState
    var mensajeExito by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Registro de Usuario", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        // Campo Nombre
        OutlinedTextField(
            value = uiState.nombre,
            onValueChange = { viewModel.onNombreChanged(it) },
            label = { Text("Nombre") },
            isError = uiState.errorNombre,
            modifier = Modifier.fillMaxWidth()
        )
        if (uiState.errorNombre) {
            Text(text = "El nombre no puede estar vacío", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Correo
        OutlinedTextField(
            value = uiState.correo,
            onValueChange = { viewModel.onCorreoChanged(it) },
            label = { Text("Correo electrónico") },
            isError = uiState.errorCorreo,
            modifier = Modifier.fillMaxWidth()
        )
        if (uiState.errorCorreo) {
            Text(text = "El correo no puede estar vacío", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Teléfono
        OutlinedTextField(
            value = uiState.telefono,
            onValueChange = { viewModel.onTelefonoChanged(it) },
            label = { Text("Teléfono") },
            isError = uiState.errorTelefono,
            modifier = Modifier.fillMaxWidth()
        )
        if (uiState.errorTelefono) {
            Text(text = "El teléfono no puede estar vacío", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Guardar / Validar
        Button(
            onClick = {
                mensajeExito = viewModel.validarFormulario()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Guardar Registro")
        }

        if (mensajeExito) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "¡Registro exitoso y validado!", color = MaterialTheme.colorScheme.primary)
        }
    }
}