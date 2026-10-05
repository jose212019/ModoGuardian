package com.example.modoguardian.navigation

sealed class NavigationEvent {
    data class NavigateTo(val destination: Screen) : NavigationEvent()
    data object NavigateBack : NavigationEvent()
}