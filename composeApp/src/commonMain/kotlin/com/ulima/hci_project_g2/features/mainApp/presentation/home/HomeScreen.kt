package com.ulima.hci_project_g2.features.mainApp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
