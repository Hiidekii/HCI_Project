package com.ulima.hci_project_g2.features.auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ulima.hci_project_g2.features.auth.presentation.login.components.MyActionButton
import com.ulima.hci_project_g2.features.auth.presentation.login.components.MyPasswordTextField
import com.ulima.hci_project_g2.features.auth.presentation.login.components.MyTextField
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.core.presentation.PrimaryWhite
import hci_project.composeapp.generated.resources.Res
import hci_project.composeapp.generated.resources.check
import hci_project.composeapp.generated.resources.email
import hci_project.composeapp.generated.resources.img_login_background
import hci_project.composeapp.generated.resources.img_ulima_logo
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = koinViewModel(),
    onLoginClick: () -> Unit
) {

    val state = loginViewModel.state

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryWhite)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Altura de la sección superior con imagen
        ) {
            Image(
                painter = painterResource(Res.drawable.img_login_background),
                contentDescription = "Fondo Login",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().alpha(0.4f)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.White)
                        )
                    )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .padding(bottom = 32.dp)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.img_ulima_logo),
                contentDescription = "logo MatchAcademico",
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
                    loginViewModel.togglePasswordVisibility()
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
                    loginViewModel.login {
                        onLoginClick()
                    }
                }
            )
        }

        if (state.errorMessage != null) {
            BasicAlertDialog(
                onDismissRequest = {
                    loginViewModel.clearError()
                },
                content = {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.elevatedCardElevation(8.dp),
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = PrimaryWhite
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(24.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "Error de inicio de sesión",
                                style = MaterialTheme.typography.titleMedium,
                                color = PrimaryOrange
                            )

                            Text(
                                text = state.errorMessage,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )

                            Button(
                                onClick = {
                                    loginViewModel.clearError()
                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = PrimaryWhite,
                                    containerColor = PrimaryOrange
                                )
                            ) {
                                Text(text = "Aceptar")
                            }
                        }
                    }
                }
            )
        }
    }
}