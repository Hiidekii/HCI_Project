package com.ulima.hci_project_g2.features.mainApp.presentation.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.ulima.hci_project_g2.core.presentation.PrimaryBlack
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import com.ulima.hci_project_g2.features.mainApp.data.RutinasRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToExerciseDetail: (String, Int) -> Unit,
    onNavigateToRoutineDetail: (String) -> Unit,
    homeViewModel: HomeViewModel = koinViewModel()
) {

    val state = homeViewModel.state
    val rutinas = state.rutinas

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HomeHeader()
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
