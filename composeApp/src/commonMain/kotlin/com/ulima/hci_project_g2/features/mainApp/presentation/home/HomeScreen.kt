package com.ulima.hci_project_g2.features.mainApp.presentation.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.ExercisesViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToExerciseDetail: (String, Int) -> Unit,
    onNavigateToRoutineDetail: (String) -> Unit,
    homeViewModel: HomeViewModel = koinViewModel(),
    exercisesViewModel: ExercisesViewModel
) {

    val stateExercises = exercisesViewModel.state
    val rutinas = stateExercises.rutinas

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HomeHeader(homeViewModel = homeViewModel)
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        SemanaSelector()
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        RutinaLista(
            rutinas = rutinas,
            onRoutineClick = { routineName ->
                onNavigateToRoutineDetail(routineName)
            }
        )
    }
}
