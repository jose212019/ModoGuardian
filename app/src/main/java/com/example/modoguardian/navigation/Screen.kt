package com.example.modoguardian.navigation

sealed class Screen(val route: String) {

    data object Login : Screen("login")
    data object HomeAdmin : Screen("home_admin")
    data object HomeSupervisor : Screen("home_supervisor")
    data object HomeOperador : Screen("home_operador")

    // Rutas antiguas para que tus otras pantallas compilen sin error
    data object Home : Screen("home")
    data object Profile : Screen("profile")
}