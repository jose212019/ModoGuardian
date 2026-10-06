package com.example.modoguardian.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.modoguardian.R
import com.example.modoguardian.model.LoginErrores
import com.example.modoguardian.model.LoginUiState
import com.example.modoguardian.navigation.Screen
import com.example.modoguardian.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val errores by viewModel.errores.collectAsState()

    // Calcula el ancho de la pantalla para cumplir la exigencia de 2 layouts de la pauta
    val configuration = LocalConfiguration.current
    val esCompacta = configuration.screenWidthDp < 600

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (esCompacta) {
                // Layout Compacto (Celular Vertical) - Todo en Columna
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ImagenLogo()
                    Spacer(modifier = Modifier.height(32.dp))
                    FormularioLogin(viewModel, uiState, errores, navController)
                }
            } else {
                // Layout Expandido (Horizontal) - Logo y Formulario en Fila (Row)
                Card(modifier = Modifier.padding(32.dp)) {
                    Row(
                        modifier = Modifier.padding(32.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ImagenLogo()
                        Spacer(modifier = Modifier.width(32.dp))
                        Column(modifier = Modifier.width(300.dp)) {
                            FormularioLogin(viewModel, uiState, errores, navController)
                        }
                    }
                }
            }
        }
    }
}

// Componente para la imagen institucional
@Composable
fun ImagenLogo() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo Modo Guardian",
        modifier = Modifier.size(150.dp),
        contentScale = ContentScale.Fit
    )
}

// Formulario reutilizable para ambos layouts (Vertical y Horizontal)
@Composable
fun FormularioLogin(
    viewModel: LoginViewModel,
    uiState: LoginUiState,
    errores: LoginErrores,
    navController: NavController
) {
    // Campo de Email
    OutlinedTextField(
        value = uiState.email,
        onValueChange = { viewModel.onEmailChange(it) },
        label = { Text("Correo electrónico") },
        isError = errores.loginError != null,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Campo de Contraseña con PasswordVisualTransformation
    OutlinedTextField(
        value = uiState.contrasena,
        onValueChange = { viewModel.onPasswordChange(it) },
        label = { Text("Contraseña") },
        visualTransformation = PasswordVisualTransformation(),
        isError = errores.loginError != null,
        supportingText = {
            if (errores.loginError != null) {
                Text(text = errores.loginError!!, color = MaterialTheme.colorScheme.error)
            }
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Botón de Inicio de Sesión que valida y navega por rol
    Button(
        onClick = {
            if (viewModel.validarFormulario()) {
                when (viewModel.uiState.value.rolAsignado) {
                    "Admin" -> navController.navigate(Screen.HomeAdmin.route)
                    "Supervisor" -> navController.navigate(Screen.HomeSupervisor.route)
                    "Operador" -> navController.navigate(Screen.HomeOperador.route)
                }
            }
        },
        enabled = uiState.isLoginEnabled, // Habilitado solo si tiene @ y 6 caracteres
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Iniciar Sesión")
    }
}