package com.ulima.hci_project_g2.features.userData.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.PrimaryBlack
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.core.presentation.PrimaryWhite
import com.ulima.hci_project_g2.core.presentation.components.UlimaFitTopBar
import com.ulima.hci_project_g2.features.auth.presentation.login.components.MyActionButton
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PesoScreen(
    onNextClick: () -> Unit,
    onReturnClick: () -> Unit,
    userDataViewModel: UserDataViewModel = koinViewModel()
) {
    var edadSeleccionada by remember { mutableStateOf(17) }

    Scaffold(
        topBar = {
            UlimaFitTopBar(
                titulo = "Evaluación",
                pasoActual = 2,
                onBackClick = { onReturnClick() },
                containerColor = PrimaryOrange
            )
        },
        containerColor = PrimaryOrange
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp, vertical = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text(
                    text = "¿Cuál es tu edad?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    color = PrimaryBlack
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryWhite,
                            contentColor = PrimaryOrange
                        )
                    ){
                        Text(
                            text = "Kg",
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryWhite,
                            contentColor = PrimaryOrange
                        )
                    ){
                        Text(
                            text = "Lb",
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                MyActionButton(
                    text = "Continuar",
                    enabled = true,
                    isLoading = false,
                    onClick = {
                        //userDataViewModel.savePesoPreferences(edadSeleccionada)
                        onNextClick()
                    }
                )
            }
        }
    }
}