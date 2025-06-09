package com.ulima.hci_project_g2.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.components.MyTextField
import hci_project.composeapp.generated.resources.Res
import hci_project.composeapp.generated.resources.img_fulllogo_orange
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
private fun LoginScreen(
    loginViewModel: LoginViewModel = koinViewModel()
) {
    val state = loginViewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .padding(bottom = 32.dp)
            .padding(top = 16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Hola a todos!",
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Te ayudaremos a sugerirte tu línea de investigación",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(32.dp))
        Icon(
            painter = painterResource(Res.drawable.img_fulllogo_orange),
            contentDescription = "logo MatchAcademico",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(32.dp))
        MyTextField(
            state = state.code,
            startIcon = painterResource(Res.drawable.email),
            endIcon = if (state.isCodeValid) {
                painterResource(Res.drawable.check)
            } else null,
            hint = "Usuario",
            title = "Usuario",
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Text,
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyPasswordTextField(
            state = state.password,
            isPasswordVisible = state.isPasswordVisible,
            onTogglePasswordVisibilityClick = {
                onAction(LoginAction.OnTogglePasswordVisibility)
            },
            hint = "Contraseña",
            title = "Contraseña",
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(32.dp))
        MyActionButton(
            text = "Iniciar sesión",
            isLoading = state.isLoggingIn,
            enabled = state.canLogin && !state.isLoggingIn,
            onClick = {
                onAction(LoginAction.OnLoginClick)
            }
        )

    }
}