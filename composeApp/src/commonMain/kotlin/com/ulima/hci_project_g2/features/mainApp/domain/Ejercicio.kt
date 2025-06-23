package com.ulima.hci_project_g2.features.mainApp.domain

import org.jetbrains.compose.resources.DrawableResource

data class Exercise(
    val name: String,
    val duration: Int,
    val calories: Int,
    val sets: String,
    val muscleGroups: List<MuscleGroup>,
    val rewardPoints: Int,
    val instructions: List<String>,
    val image : DrawableResource,
    val gif : DrawableResource,
)


enum class MuscleGroup(val displayName: String) {
    CHEST("Pecho"),
    BACK("Espalda"),
    LEGS("Piernas"),
    SHOULDERS("Hombros"),
    ARMS("Brazos"),
    CORE("Core"),
    FULL_BODY("Cuerpo completo")
}