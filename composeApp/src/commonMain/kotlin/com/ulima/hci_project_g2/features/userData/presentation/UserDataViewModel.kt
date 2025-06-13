package com.ulima.hci_project_g2.features.userData.presentation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
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

    fun savePesoPreferences(peso: Int, unidad: String){
        viewModelScope.launch {
            preferences.edit { dataStore ->
                val pesoKey = stringPreferencesKey("peso")
                dataStore[pesoKey] = "$peso $unidad"
            }
        }
    }

    fun getPesoPreferences() {
        viewModelScope.launch {
            preferences.data.collect { dataStore ->
                val pesoKey = stringPreferencesKey("peso")
                val peso = dataStore[pesoKey]
                println("Peso: $peso")
            }
        }
    }

    fun saveAlturaPreferences(altura: Int){
        viewModelScope.launch {
            preferences.edit { dataStore ->
                val pesoKey = stringPreferencesKey("altura")
                dataStore[pesoKey] = "$altura cm"
            }
        }
    }
}