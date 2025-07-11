package com.ulima.hci_project_g2.features.mainApp.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulima.hci_project_g2.features.auth.data.UsuarioRepository
import com.ulima.hci_project_g2.features.mainApp.presentation.home.HomeState
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val preferences: DataStore<Preferences>,
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getName()
        getApellido()
        getCodigo()
        getCarrera()
        getPuntos()
        getRanking()
    }

    fun clearAllPreferences() {
        viewModelScope.launch {
            preferences.edit { it.clear() }
        }
    }

    private fun getName() {
        viewModelScope.launch {
            preferences.data.collect { dataStore ->
                val nameKey = stringPreferencesKey("nombre")
                val name = dataStore[nameKey]
                if (name != null) {
                    state = state.copy(name = name)
                }
            }
        }
    }

    private fun getApellido() {
        viewModelScope.launch {
            preferences.data.collect { dataStore ->
                val apellidoKey = stringPreferencesKey("apellido")
                val apellido = dataStore[apellidoKey]
                if (apellido != null) {
                    state = state.copy(apellido = apellido)
                }
            }
        }
    }

    private fun getCodigo() {
        viewModelScope.launch {
            preferences.data.collect { dataStore ->
                val codigoKey = stringPreferencesKey("codigo")
                val codigo = dataStore[codigoKey]
                println("codigo get codigo: $codigo")
                if (codigo != null) {
                    state = state.copy(codigo = codigo)
                }
            }
        }
    }

    private fun getCarrera() {
        viewModelScope.launch {
            preferences.data.collect { dataStore ->
                val carreraKey = stringPreferencesKey("carrera")
                val carrera = dataStore[carreraKey]
                if (carrera != null) {
                    state = state.copy(carrera = carrera)
                }
            }
        }
    }

    private fun getPuntos() {
        viewModelScope.launch {
            preferences.data.collect { dataStore ->
                val puntosKey = intPreferencesKey("puntos")
                val puntos = dataStore[puntosKey]
                if (puntos != null) {
                    state = state.copy(puntos = puntos)
                }
            }
        }
    }


    private fun getRanking() {
        viewModelScope.launch {
            val leaderboard = usuarioRepository.obtenerRanking()
            state = state.copy(
                leaderboard = leaderboard.sortedByDescending { it.puntos }
            )
        }
    }

    fun getPuesto(): String {
        val lista = state.leaderboard
        val codigo = state.codigo
        println("codigo get puesto: $codigo")
        val puesto = lista.indexOfFirst {
            it.usuario.trim().lowercase() == codigo.trim().lowercase()
        }
        return (puesto+1).toString()
    }
}