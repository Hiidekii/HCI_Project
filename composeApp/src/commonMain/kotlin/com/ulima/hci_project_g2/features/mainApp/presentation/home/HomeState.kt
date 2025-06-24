package com.ulima.hci_project_g2.features.mainApp.presentation.home

import com.ulima.hci_project_g2.features.auth.domain.Usuario

data class HomeState(
    val name : String = "",
    val apellido : String = "",
    val carrera : String = "",
    val puntos : Int = 0,
    val leaderboard: List<Usuario> = emptyList()
)