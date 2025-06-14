package com.ulima.hci_project_g2.features.userData.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ulima.hci_project_g2.core.presentation.PrimaryWhite
import com.ulima.hci_project_g2.core.presentation.components.MyActionButton
import com.ulima.hci_project_g2.core.presentation.components.UlimaFitTopBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GeneroScreen (
    onNextClick: () -> Unit,
    onReturnClick: () -> Unit,
    userDataViewModel: UserDataViewModel = koinViewModel()
){
    var selectedObjective by remember { mutableStateOf<String?>(null) }
    Scaffold(
        topBar = {
            UlimaFitTopBar(
                titulo = "Evaluación",
                pasoActual = 5,
                onBackClick = { onReturnClick() }
            )
        },
        containerColor = PrimaryWhite
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp, vertical = 50.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MyActionButton(
                    text = "Continuar",
                    enabled = (selectedObjective != null),
                    isLoading = false,
                    onClick = {
                        onNextClick()
                    }
                )
            }
        }
    }
}