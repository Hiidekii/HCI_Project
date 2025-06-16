package com.ulima.hci_project_g2.features.mainApp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ulima.hci_project_g2.features.mainApp.components.UlimaFitBottomBar
import com.ulima.hci_project_g2.features.mainApp.home.HomeScreen

@Composable
fun MainWrapperScreen() {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {

        },
        bottomBar = {
            UlimaFitBottomBar(selectedIndex) { selectedIndex = it }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HomeScreen()
        }
    }
}