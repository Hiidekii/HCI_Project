package com.ulima.hci_project_g2.features.mainApp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ulima.hci_project_g2.app.Route
import com.ulima.hci_project_g2.features.mainApp.presentation.components.UlimaFitBottomBar
import com.ulima.hci_project_g2.features.mainApp.presentation.home.HomeScreen
import com.ulima.hci_project_g2.features.profile.presentation.ProfileScreen

@Composable
fun MainWrapperScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            UlimaFitBottomBar(selectedIndex) { newIndex ->
                selectedIndex = newIndex
            }
        }
    ) { paddingValues ->
        when (selectedIndex) {
            0 -> HomeScreen(
                modifier = Modifier.padding(paddingValues),
                onNavigateToExerciseDetail = { routineName, exerciseIndex ->
                    navController.navigate("exerciseDetail/$routineName/$exerciseIndex")
                },
                onNavigateToRoutineDetail = { routineName ->
                    navController.navigate("routineDetail/$routineName")
                }
            )

            1 -> {
                navController.navigate("routineDetail/Fuerza superior 1")
            }

            2 -> ProfileScreen(
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}