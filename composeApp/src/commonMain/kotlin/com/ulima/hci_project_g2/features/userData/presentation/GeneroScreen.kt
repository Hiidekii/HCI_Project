package com.ulima.hci_project_g2.features.userData.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.PrimaryBlack
import com.ulima.hci_project_g2.core.presentation.PrimaryGray
import com.ulima.hci_project_g2.core.presentation.PrimaryLightOrange
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.core.presentation.PrimaryWhite
import com.ulima.hci_project_g2.core.presentation.SecondaruOrange
import com.ulima.hci_project_g2.core.presentation.components.MyActionButton
import com.ulima.hci_project_g2.core.presentation.components.UlimaFitTopBar
import hci_project.composeapp.generated.resources.Res
import hci_project.composeapp.generated.resources.ic_close
import hci_project.composeapp.generated.resources.img_hombre
import hci_project.composeapp.generated.resources.img_mujer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GeneroScreen (
    onNextClick: () -> Unit,
    onReturnClick: () -> Unit,
    userDataViewModel: UserDataViewModel = koinViewModel()
){
    var selectedGender by remember { mutableStateOf<  String?>(null) }
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
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment   = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "¿Cuál es tu género?",
                    fontWeight = FontWeight.Black,
                    fontSize = 36.sp,
                    color = PrimaryBlack,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                )
                Spacer(modifier = Modifier.weight(1f))

                    GenderCard(
                        imageRes = Res.drawable.img_hombre,
                        label = "Hombre",
                        isSelected = selectedGender == "Hombre"
                    ) { selectedGender = "Hombre" }

                    GenderCard(
                        imageRes = Res.drawable.img_mujer,
                        label = "Mujer",
                        isSelected = selectedGender == "Mujer"
                    ) { selectedGender = "Mujer" }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        selectedGender = "noEspecificado"
                        onNextClick() },
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryLightOrange,
                        contentColor = SecondaruOrange
                    ),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {
                        Text(
                            text = "Prefiero omitirlo, ¡gracias!",
                            fontSize = 23.sp,
                            modifier = Modifier.padding(vertical = 18.dp, horizontal = 12.dp),
                        )
                       Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            painter = painterResource(Res.drawable.ic_close),
                            contentDescription = "closeIcon",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                }


                MyActionButton(
                    text = "Continuar",
                    enabled = (selectedGender != null),
                    isLoading = false,
                    onClick = {
                        userDataViewModel.saveGeneroPreferences(selectedGender!!)
                        onNextClick()
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun GenderCard(
    imageRes: DrawableResource,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val outerStroke = if (isSelected)
        BorderStroke(4.dp, PrimaryOrange.copy(alpha = 0.6f))
    else
        BorderStroke(0.dp, PrimaryWhite) // o transparente

    val innerStroke = BorderStroke(
        width  = if (isSelected) 2.dp else 2.dp,
        color  = if (isSelected) PrimaryOrange else PrimaryGray.copy(alpha = 0.3f)
    )

    val background = if (isSelected) PrimaryOrange.copy(alpha = 0.1f) else PrimaryWhite
    val cardShape  = RoundedCornerShape(18.dp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable(onClick = onClick)
            .border(outerStroke, cardShape)    // borde exterior
            .border(innerStroke, cardShape),   // borde interior
        shape  = cardShape,
        colors = CardDefaults.cardColors(containerColor = background),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Image(
            painter           = painterResource(imageRes),
            contentDescription = label,
            contentScale      = ContentScale.Crop,
            modifier          = Modifier
                .fillMaxSize()
                .clip(cardShape)
        )
    }
}

