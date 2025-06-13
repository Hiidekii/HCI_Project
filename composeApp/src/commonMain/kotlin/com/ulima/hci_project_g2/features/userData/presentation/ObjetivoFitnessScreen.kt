package com.ulima.hci_project_g2.features.userData.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.PrimaryBlack
import com.ulima.hci_project_g2.core.presentation.PrimaryDarkGray
import com.ulima.hci_project_g2.core.presentation.PrimaryWhite
import com.ulima.hci_project_g2.core.presentation.TertiaryGray
import com.ulima.hci_project_g2.core.presentation.components.CustomRadio
import com.ulima.hci_project_g2.core.presentation.components.MyActionButton
import com.ulima.hci_project_g2.core.presentation.components.UlimaFitTopBar
import hci_project.composeapp.generated.resources.Res
import hci_project.composeapp.generated.resources.ic_balanza
import hci_project.composeapp.generated.resources.ic_celular
import hci_project.composeapp.generated.resources.ic_heart
import hci_project.composeapp.generated.resources.ic_pesa
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ObjetivoFitnessScreen(
    onReturnClick: () -> Unit,
    onNextClick: () -> Unit
){
    var selectedObjective by remember { mutableStateOf<Int?>(null) }
    val objetivos = listOf(
        ObjetivoFitness(
            icono = Res.drawable.ic_balanza,
            nombre = "Quiero perder peso"
        ),
        ObjetivoFitness(
            icono = Res.drawable.ic_pesa,
            nombre = "Quiero ganar volumen"
        ),
        ObjetivoFitness(
            icono = Res.drawable.ic_heart,
            nombre = "Quiero ganar resistencia"
        ),
        ObjetivoFitness(
            icono = Res.drawable.ic_celular,
            nombre = "¡Probando la aplicación!"
        )
    )
    println("index: $selectedObjective")

    Scaffold(
        topBar = {
            UlimaFitTopBar(
                titulo = "Evaluación",
                pasoActual = 6,
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
                Text(
                    text = "¿Cuál es tu objetivo/meta de fitness?",
                    fontWeight = FontWeight.Black,
                    fontSize = 36.sp,
                    color = PrimaryBlack,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                )
                Spacer(modifier = Modifier.height(24.dp))
                objetivos.forEachIndexed { index, obj ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = TertiaryGray),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedObjective = index + 1 }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 18.dp, horizontal = 22.dp )

                        ) {
                            Icon(
                                painter = painterResource(obj.icono),
                                contentDescription = obj.nombre,
                                modifier = Modifier.size(24.dp),
                                tint = PrimaryDarkGray
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = obj.nombre,
                                modifier = Modifier.weight(1f),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
//                            RadioButton(
//                                selected = selectedObjective == index + 1,
//                                onClick = { selectedObjective = index + 1 }
//                            )
                            CustomRadio(
                                selected = selectedObjective == index + 1,
                                onClick  = { selectedObjective = index + 1 }
                            )

                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
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

data class ObjetivoFitness(
    val nombre: String,
    val icono: DrawableResource
)