package com.example.modoguardian.viewmodel

import androidx.lifecycle.ViewModel
import com.example.modoguardian.model.SecurityEvent
import com.example.modoguardian.repository.SecurityRepository

class MainViewModel : ViewModel() {

    private val repository = SecurityRepository()

    // Exponer los eventos hacia la UI
    val events: List<SecurityEvent> = repository.getSecurityEvents()
}