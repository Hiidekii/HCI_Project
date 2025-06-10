package com.ulima.hci_project_g2.features.auth.presentation.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class LoginState(
    val code: MutableState<String> = mutableStateOf(""),
    val password: MutableState<String> = mutableStateOf(""),
    val errorMessage: String? = null,
    val isPasswordVisible: Boolean = false,
    val canLogin: Boolean = true,
    val isLoggingIn: Boolean = false,
    val isCodeValid: Boolean = false,
    val isLoading: Boolean = false
)