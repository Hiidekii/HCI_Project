package com.ulima.hci_project_g2.features.mainApp.domain

import com.ulima.hci_project_g2.features.exercise.domain.Exercise
import org.jetbrains.compose.resources.DrawableResource

data class Rutina(
    val nombre: String,
    val duracion: String,
    val calorias: String,
    val imagen: DrawableResource,
    val ejercicios: List<Exercise>
)

