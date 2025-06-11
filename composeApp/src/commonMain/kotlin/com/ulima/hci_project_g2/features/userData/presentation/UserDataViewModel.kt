package com.ulima.hci_project_g2.features.userData.presentation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserDataViewModel (
    private val preferences: DataStore<Preferences>
): ViewModel() {

    fun saveEdadPreferences(edad: Int) {
        viewModelScope.launch {
            preferences.edit { dataStore ->
                val edadKey = intPreferencesKey("edad")
                dataStore[edadKey] = edad
            }
        }
    }

    /*fun getEdadPreferences() {
        viewModelScope.launch {
            preferences.data.collect { dataStore ->
                val edadKey = intPreferencesKey("edad")
                val edad = dataStore[edadKey]
                println("Edad: $edad")
            }
        }
    }*/

    fun savePesoPreferences(peso: Int){
        viewModelScope.launch {
            preferences.edit { dataStore ->
                val pesoKey = intPreferencesKey("peso")
                dataStore[pesoKey] = peso
            }
        }
    }
}