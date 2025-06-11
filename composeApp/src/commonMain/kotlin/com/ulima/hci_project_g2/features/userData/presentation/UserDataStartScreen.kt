package com.ulima.hci_project_g2.features.userData.presentation

import androidx.compose.runtime.Composable
import com.ulima.hci_project_g2.features.auth.presentation.login.components.MyActionButton

@Composable
fun UserDataStartScreen(
    onNextClick: () -> Unit,
) {
    MyActionButton(
        text = "Continuar",
        enabled = true,
        isLoading = false,
        onClick = {
            onNextClick()
        }
    )
}