package com.example.modoguardian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modoguardian.navigation.NavigationEvent
import com.example.modoguardian.navigation.Screen
import com.example.modoguardian.ui.screens.HomeScreen
import com.example.modoguardian.ui.screens.ProfileScreen
import com.example.modoguardian.ui.screens.SettingsScreen
import com.example.modoguardian.ui.theme.ModoGuardianTheme
import com.example.modoguardian.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    // Instanciamos el ViewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Mantenemos esto de tu código original
        setContent {
            ModoGuardianTheme {
                // Controlador de navegación de Compose
                val navController = rememberNavController()

                // Escuchar los eventos de navegación que vienen del ViewModel
                LaunchedEffect(Unit) {
                    viewModel.navigationEvent.collect { event ->
                        when (event) {
                            is NavigationEvent.NavigateTo -> navController.navigate(event.destination.route)
                            is NavigationEvent.NavigateBack -> navController.popBackStack()
                        }
                    }
                }

                // Estructura visual principal
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // El NavHost contiene el mapa de todas las pantallas
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Ruta a la pantalla principal
                        composable(Screen.Home.route) {
                            HomeScreen()
                        }
                        // Ruta a la pantalla de Perfil
                        composable(Screen.Profile.route) {
                            ProfileScreen()
                        }
                        // Ruta a la pantalla de Configuración
                        composable(Screen.Settings.route) {
                            SettingsScreen()
                        }
                    }
                }
            }
        }
    }
}