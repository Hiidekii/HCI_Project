package com.ulima.hci_project_g2.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AppViewModel(
    private val preferences: DataStore<Preferences>
) : ViewModel() {

    var state by mutableStateOf(AppState())
        private set

    init {
        getStarted()
    }

    private fun getStarted() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            getIsLoggedIn()
            getCompleteIntro()
            state = state.copy(hasCompleteIntro = false)
        }
    }

    private fun getIsLoggedIn() {
        viewModelScope.launch {
            preferences.data.collect { dataStore ->
                val loggedKey = booleanPreferencesKey("logged")
                val logged = dataStore[loggedKey]
                if(logged != null) {
                    state = state.copy(
                        isLogged = logged
                    )
                }
            }
        }
    }

    private fun getCompleteIntro() {
        viewModelScope.launch {
            preferences.data.collect { dataStore ->
                val hasCompletedIntroKey = booleanPreferencesKey("introCompleta")
                val hasCompletedIntro = dataStore[hasCompletedIntroKey]
                if(hasCompletedIntro != null) {
                    state = state.copy(
                        hasCompleteIntro = hasCompletedIntro
                    )
                }
            }
        }
    }
}