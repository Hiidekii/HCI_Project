package com.ulima.hci_project_g2.features.mainApp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulima.hci_project_g2.features.auth.presentation.login.LoginState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val preferences: DataStore<Preferences>
) : ViewModel() {

    init {
        getName()
    }

    var state by mutableStateOf(HomeState())
        private set

    fun getName(){
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
}