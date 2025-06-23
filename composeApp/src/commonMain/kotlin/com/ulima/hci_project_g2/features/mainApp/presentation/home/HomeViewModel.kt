package com.ulima.hci_project_g2.features.mainApp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ulima.hci_project_g2.features.mainApp.data.RutinasRepository

class HomeViewModel(
    private val rutinasRepository: RutinasRepository
): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getRutinas()
    }

    private fun getRutinas(){
        val rutinas = rutinasRepository.obtenerRutinas()
        state = state.copy(
            rutinas = rutinas
        )
    }
}