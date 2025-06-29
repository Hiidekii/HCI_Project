package com.ulima.hci_project_g2.features.mainApp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var selectedWeek by remember { mutableStateOf(3) }
    val todasRutinas = exercisesViewModel.state.rutinas

    // 1. Define aquí los índices para cada semana (0-based)
    val weeklyOrders = listOf(
        listOf(0, 1, 2), // Semana 1
        listOf(1, 2, 0), // Semana 2
        listOf(2, 0, 1), // Semana 3
        listOf(2, 1, 0)  // Semana 4 (p. ej. reverso parcial)
    )

    // 2. Reordena según la semana seleccionada
    val rutinasOrdenadas = remember(todasRutinas, selectedWeek) {
        val order = weeklyOrders.getOrElse(selectedWeek) { weeklyOrders[0] }
        order.mapNotNull { idx -> todasRutinas.getOrNull(idx) }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HomeHeader(homeViewModel = homeViewModel)
        Spacer(modifier = Modifier.height(20.dp))

        SemanaSelector(
            semanas = listOf("SEMANA 1", "SEMANA 2", "SEMANA 3", "SEMANA 4"),
            selected = selectedWeek,
            onWeekSelected = { selectedWeek = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        RutinaLista(
            rutinas = rutinasOrdenadas,
            onRoutineClick = { onNavigateToRoutineDetail(it) }
        )
    }
}