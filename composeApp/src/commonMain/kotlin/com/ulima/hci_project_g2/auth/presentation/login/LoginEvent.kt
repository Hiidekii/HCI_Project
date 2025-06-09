package com.ulima.hci_project_g2.auth.presentation.login

sealed interface LoginEvent {
    data object LoginSuccess: LoginEvent
    data class LoginError(val message: String) : LoginEvent
}