package com.ulima.hci_project_g2.features.userData.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.core.presentation.PrimaryWhite
import com.ulima.hci_project_g2.core.presentation.components.UlimaFitTopBar
import com.ulima.hci_project_g2.features.auth.presentation.login.components.MyActionButton
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.abs

@Composable
fun PesoScreen(
    onNextClick: () -> Unit,
    onReturnClick: () -> Unit,
    userDataViewModel: UserDataViewModel = koinViewModel()
) {
    var pesoSeleccionado by remember { mutableStateOf(159) }

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
                    text = "¿Cuál es tu peso?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    color = PrimaryWhite
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
                            fontSize = 26.sp,
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
                            fontSize = 26.sp,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                PesoRulerPicker(
                    pesoSeleccionado = pesoSeleccionado,
                    onPesoSeleccionado = { pesoSeleccionado = it }
                )
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

@Composable
fun PesoRulerPicker(
    pesoSeleccionado: Int,
    onPesoSeleccionado: (Int) -> Unit,
    min: Int = 30,
    max: Int = 200
) {
    val items = (min..max).toList()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        listState.scrollToItem(pesoSeleccionado - min)
    }

    val layoutInfo = listState.layoutInfo
    val center = layoutInfo.viewportEndOffset / 2
    val visibleItems = layoutInfo.visibleItemsInfo

    val itemCentrado = visibleItems.minByOrNull {
        abs((it.offset + it.size / 2) - center)
    }?.index

    LaunchedEffect(itemCentrado) {
        itemCentrado?.let { index ->
            onPesoSeleccionado(min + index)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$pesoSeleccionado Lbs",
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            LazyRow(
                state = listState,
                contentPadding = PaddingValues(horizontal = 100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(items.size) { index ->
                    val valor = min + index
                    val isMarcaPrincipal = valor % 5 == 0
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .width(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(if (isMarcaPrincipal) 40.dp else 20.dp)
                                .width(2.dp)
                                .background(Color.White)
                        )
                        if (isMarcaPrincipal) {
                            Text(
                                text = valor.toString(),
                                fontSize = 12.sp,
                                color = Color.White,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }

            // Línea guía central
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(60.dp)
                    .width(4.dp)
                    .background(Color.White, shape = RoundedCornerShape(2.dp))
            )
        }
    }
}
