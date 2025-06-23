package com.ulima.hci_project_g2.features.mainApp.domain

data class RutinasSemana (
    val numSemana: Int,
    val rutinasDia: List<RutinaDia>
)

data class RutinaDia (
    val duracion: String,
    val calorias: String,
    val ejercicios: List<Exercise>
)