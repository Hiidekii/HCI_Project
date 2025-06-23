package com.ulima.hci_project_g2.features.mainApp.presentation.exercise

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ulima.hci_project_g2.features.mainApp.data.ExerciseRepository
import com.ulima.hci_project_g2.features.mainApp.presentation.home.HomeState

class ExercisesViewModel(
    private val exerciseRepository: ExerciseRepository
): ViewModel() {

    var state by mutableStateOf(ExerciseState())
        private set

    init {
        getAllExercises()
    }

    private fun getAllExercises() {
        val exercises = exerciseRepository.obtenerEjercicios()
        state = state.copy(
            exercises = exercises
        )
    }
}