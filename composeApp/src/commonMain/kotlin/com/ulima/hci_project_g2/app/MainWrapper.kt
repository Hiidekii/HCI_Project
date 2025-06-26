package com.ulima.hci_project_g2.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ulima.hci_project_g2.features.mainApp.presentation.components.UlimaFitBottomBar
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.allExercise.ExercisesScreen
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.ExercisesViewModel
import com.ulima.hci_project_g2.features.mainApp.presentation.home.HomeScreen
import com.ulima.hci_project_g2.features.mainApp.presentation.profile.ProfileScreen

@Composable
fun MainWrapperScreen(
    navController: NavController,
    exercisesViewModel: ExercisesViewModel
) {
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
                },
                exercisesViewModel = exercisesViewModel
            )

            1 -> {
                ExercisesScreen(
                    paddingValues = paddingValues,
                    onExerciseClick = { exerciseName ->
                        navController.navigate("allExerciseDetail/$exerciseName")
                    }
                )
            }

            2 -> ProfileScreen(
                onLogOutClick = {
                    navController.navigate(Route.AuthGraph) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onNextClick = { navController.navigate(Route.Leaderboard) },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}