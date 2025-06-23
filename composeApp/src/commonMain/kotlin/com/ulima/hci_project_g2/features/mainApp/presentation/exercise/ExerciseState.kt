package com.ulima.hci_project_g2.features.mainApp.presentation.exercise

import com.ulima.hci_project_g2.features.mainApp.domain.Exercise
import com.ulima.hci_project_g2.features.mainApp.domain.Rutina

data class ExerciseState(
    val exercises: List<Exercise> = emptyList(),
    val rutinas: List<Rutina> = emptyList(),
    val ejerciciosRutina: List<Exercise> = emptyList(),
    val selectedExercise: Exercise? = null
)