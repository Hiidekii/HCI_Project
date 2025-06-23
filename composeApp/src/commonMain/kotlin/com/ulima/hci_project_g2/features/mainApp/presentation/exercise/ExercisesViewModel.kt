package com.ulima.hci_project_g2.features.mainApp.presentation.exercise

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulima.hci_project_g2.features.mainApp.data.ExerciseRepository
import com.ulima.hci_project_g2.features.mainApp.data.RutinasRepository
import com.ulima.hci_project_g2.features.mainApp.presentation.home.HomeState
import kotlinx.coroutines.launch

class ExercisesViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val rutinasRepository: RutinasRepository
): ViewModel() {

    var state by mutableStateOf(ExerciseState())
        private set

    init {
        getRutinas()
        getAllExercises()
    }

    private fun getAllExercises() {
        val exercises = exerciseRepository.obtenerEjercicios()
        state = state.copy(
            exercises = exercises
        )
    }

    private fun getRutinas(){
        viewModelScope.launch {
            val rutinas = rutinasRepository.obtenerRutinas()
            state = state.copy(
                rutinas = rutinas
            )
        }
    }

    fun getRoutineExercises(routineName: String){
        viewModelScope.launch {
            val routineExercises = rutinasRepository.obtenerEjerciciosPorRutina(routineName)
            state = state.copy(
                ejerciciosRutina = routineExercises
            )
        }
    }

    fun getExerciseByIndex(index: Int){
        viewModelScope.launch {
            val routineExercises = state.ejerciciosRutina
            state = state.copy(
                selectedExercise = routineExercises[index]
            )
        }
    }
}