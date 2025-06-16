package com.ulima.hci_project_g2.features.userData.presentation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserDataViewModel(
    private val preferences: DataStore<Preferences>
) : ViewModel() {

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

    fun savePesoPreferences(peso: Int, unidad: String) {
        viewModelScope.launch {
            preferences.edit { dataStore ->
                val pesoKey = stringPreferencesKey("peso")
                dataStore[pesoKey] = "$peso $unidad"
            }
        }
    }

    //    fun getPesoPreferences() {
    //        viewModelScope.launch {
    //            preferences.data.collect { dataStore ->
    //                val pesoKey = stringPreferencesKey("peso")
    //                val peso = dataStore[pesoKey]
    //                println("Peso: $peso")
    //            }
    //        }
    //    }

    fun saveAlturaPreferences(altura: Int) {
        viewModelScope.launch {
            preferences.edit { dataStore ->
                val alturaKey = stringPreferencesKey("altura")
                dataStore[alturaKey] = "$altura cm"
            }
        }
    }

    //    fun getAlturaPreferences(){
    //        viewModelScope.launch {
    //            preferences.data.collect{ dataStore ->
    //                val alturaKey = stringPreferencesKey("altura")
    //                val altura = dataStore[alturaKey]
    //                println(altura)
    //            }
    //        }
    //    }
    fun saveGeneroPreferences(genero: Int) {
        viewModelScope.launch {
            preferences.edit { dataStore ->
                val generoKey = intPreferencesKey("genero")
                dataStore[generoKey] = genero
            }
        }
    }

    //    fun getGeneroPreferences() {
    //        viewModelScope.launch {
    //            preferences.data.collect { dataStore ->
    //                val generoKey = intPreferencesKey("genero")
    //                val genero = dataStore[generoKey]
    //                println("Genero: $genero")
    //            }
    //        }
    //    }

    fun saveFisicoPreferences(condicionFisica: Int) {
        viewModelScope.launch {
            preferences.edit { dataStore ->
                val fisicoKey = intPreferencesKey("condicionFisica")
                dataStore[fisicoKey] = condicionFisica
            }
        }
    }

    //    fun getFisicoPreferences() {
    //        viewModelScope.launch {
    //            preferences.data.collect { dataStore ->
    //                val fisicoKey = intPreferencesKey("CondicionFisica")
    //                val CondicionFisica = dataStore[fisicoKey]
    //                println("CondicionFisica: $CondicionFisica")
    //            }
    //        }
    //    }
    fun saveObjetivoPreferences(objetivo: Int) {
        viewModelScope.launch {
            preferences.edit { dataStore ->
                val objetivoKey = intPreferencesKey("objetivo")
                dataStore[objetivoKey] = objetivo
            }
        }
    }

//                fun getObjetivoPreferences() {
//                    viewModelScope.launch {
//                        preferences.data.collect { dataStore ->
//                            val objetivoKey = intPreferencesKey("objetivo")
//                            val objetivo = dataStore[objetivoKey]
//                            println("ObjetivoFitness: $objetivo")
//                        }
//                    }
//                }

    fun markIntroInfoFinished() {
        viewModelScope.launch {
            preferences.edit { dataStore ->
                val completeInfoKey = booleanPreferencesKey("introCompleta")
                dataStore[completeInfoKey] = true
            }
        }
    }
}

