package com.ulima.hci_project_g2.features.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ulima.hci_project_g2.features.auth.data.UsuarioRepository

class LoginViewModel(
    private val usuarioRepository: UsuarioRepository
): ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun togglePasswordVisibility() {
        state = state.copy(
            isPasswordVisible = !state.isPasswordVisible
        )
    }

    fun login(onSuccess: () -> Unit){
        state = state.copy(isLoggingIn = true)

        val isValid = usuarioRepository.validarLogin(
            usuario = state.code.value,
            contrasena = state.password.value
        )

        if (isValid){
            onSuccess()
        }else{
            state = state.copy(errorMessage = "Credenciales inválidas")
        }

        state = state.copy(isLoggingIn = false,)
    }

    fun clearError(){
        state = state.copy(errorMessage = null)
    }
}