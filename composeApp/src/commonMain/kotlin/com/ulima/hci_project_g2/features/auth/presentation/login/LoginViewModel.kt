package com.ulima.hci_project_g2.features.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulima.hci_project_g2.features.auth.data.UsuarioRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val preferences: DataStore<Preferences>
): ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun togglePasswordVisibility() {
        state = state.copy(
            isPasswordVisible = !state.isPasswordVisible
        )
    }

    fun login(onSuccess: () -> Unit){
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)

            val isValid = usuarioRepository.validarLogin(
                usuario = state.code.value,
                contrasena = state.password.value
            )

            if (isValid) {
                val name = usuarioRepository.getName(
                    usuario = state.code.value,
                    contrasena = state.password.value
                )
                preferences.edit { dataStore ->
                    val nameKey = stringPreferencesKey("nombre")
                    dataStore[nameKey] = name
                }
                preferences.edit { dataStore ->
                    val loggedInKey = booleanPreferencesKey("logged")
                    dataStore[loggedInKey] = true
                }
                onSuccess()
            } else {
                state = state.copy(errorMessage = "Credenciales inválidas")
            }

            state = state.copy(isLoggingIn = false,)
        }
    }

    fun clearError(){
        state = state.copy(errorMessage = null)
    }
}