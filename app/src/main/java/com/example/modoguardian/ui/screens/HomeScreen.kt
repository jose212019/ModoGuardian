package com.example.modoguardian.ui.screens

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.example.modoguardian.ui.utils.obtenerWindowSizeClass

// Elige la pantalla a mostrar según el ancho del dispositivo
@Composable
fun HomeScreen() {
    val windowSizeClass = obtenerWindowSizeClass()
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> HomeScreenCompacta()
        WindowWidthSizeClass.Medium -> HomeScreenMediana()
        else -> HomeScreenExpandida()
    }
}