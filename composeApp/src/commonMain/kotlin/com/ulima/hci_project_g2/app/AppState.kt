package com.ulima.hci_project_g2.app

data class AppState(
    val isLogged: Boolean = false,
    val hasCompleteIntro: Boolean = false,
    val isLoading: Boolean = false
)