package com.ulima.hci_project_g2.features.mainApp.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulima.hci_project_g2.features.mainApp.presentation.home.HomeState
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val preferences: DataStore<Preferences>
): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getName()
    }

    fun clearAllPreferences() {
        viewModelScope.launch {
            preferences.edit { it.clear() }
        }
    }

    private fun getName(){
        viewModelScope.launch {
            preferences.data.collect{ dataStore ->
                val nameKey = stringPreferencesKey("nombre")
                val name = dataStore[nameKey]
                if (name != null){
                    state = state.copy(name = name)
                }
            }
        }
    }
}