package com.ulima.hci_project_g2.features.mainApp.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ulima.hci_project_g2.features.mainApp.domain.Rutina

data class HomeState(
    val rutinas: List<Rutina> = emptyList(),
    val name : String = ""
)