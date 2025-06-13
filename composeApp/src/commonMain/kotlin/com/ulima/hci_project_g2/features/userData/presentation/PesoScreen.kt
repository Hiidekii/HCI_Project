package com.ulima.hci_project_g2.features.userData.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.core.presentation.PrimaryWhite
import com.ulima.hci_project_g2.core.presentation.components.UlimaFitTopBar
import com.ulima.hci_project_g2.core.presentation.components.MyActionButton
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.abs

@Composable
fun PesoScreen(
    onNextClick: () -> Unit,
    onReturnClick: () -> Unit,
    userDataViewModel: UserDataViewModel = koinViewModel()
) {
    var unidad by remember { mutableStateOf("") }
    var pesoSeleccionadoLb by remember { mutableStateOf(159) }
    var pesoSeleccionadoKg by remember { mutableStateOf(74) }

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
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            unidad = "kg"
                        },
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
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            unidad = "lb"
                        },
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
                when(unidad){
                    "kg" -> {
                        PesoRulerPickerKg(
                            pesoSeleccionado = pesoSeleccionadoKg,
                            onPesoSeleccionado = { pesoSeleccionadoKg = it }
                        )
                    }
                    "lb" -> {
                        PesoRulerPickerLb(
                            pesoSeleccionado = pesoSeleccionadoLb,
                            onPesoSeleccionado = { pesoSeleccionadoLb = it }
                        )
                    }
                    else -> {
                        PesoRulerPickerKg(
                            pesoSeleccionado = pesoSeleccionadoKg,
                            onPesoSeleccionado = { pesoSeleccionadoKg = it }
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                MyActionButton(
                    text = "Continuar",
                    enabled = true,
                    isLoading = false,
                    onClick = {
                        userDataViewModel.savePesoPreferences(
                            when(unidad){
                                "kg" -> {
                                    pesoSeleccionadoKg
                                }
                                "lb" -> {
                                    pesoSeleccionadoLb
                                }
                                else -> {
                                    pesoSeleccionadoKg
                                }
                            },
                            unidad
                        )
                        onNextClick()
                    }
                )
            }
        }
    }
}

@Composable
fun PesoRulerPickerLb(
    pesoSeleccionado: Int,
    onPesoSeleccionado: (Int) -> Unit,
    min: Int = 30,
    max: Int = 200
) {
    val items = (min..max).toList()
    val listState = rememberLazyListState()
    val itemWidth = 36.dp
    val density = LocalDensity.current

    // Convertir itemWidth a px para compensar offset visual
    val itemWidthPx = with(density) { itemWidth.toPx() }
    val centerOffsetPx = itemWidthPx * 3  // 3 ítems visibles aprox a cada lado

    // 🔧 Ajustar scroll inicial solo al montar
    LaunchedEffect(Unit) {
        listState.scrollToItem(
            index = pesoSeleccionado - min,
            scrollOffset = -itemWidthPx.toInt() / 2 // Centrar el ítem bien
        )
    }

    // Determinar el item más cercano al centro real
    val layoutInfo = listState.layoutInfo
    val visibleItems = layoutInfo.visibleItemsInfo
    val center = layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset / 2

    val itemCentrado = visibleItems.minByOrNull {
        abs((it.offset + it.size / 2) - center)
    }?.index

    LaunchedEffect(itemCentrado) {
        itemCentrado?.let { index ->
            if (index in items.indices) {
                val valorCorregido = (items[index] + 1).coerceIn(min, max)
                if (valorCorregido != pesoSeleccionado) {
                    onPesoSeleccionado(valorCorregido)
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$pesoSeleccionado Lbs",
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryWhite
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            LazyRow(
                state = listState,
                contentPadding = PaddingValues(horizontal = 100.dp),
                verticalAlignment = Alignment.CenterVertically,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
            ) {
                items(items.size) { index ->
                    val value = items[index]
                    val isMainMark = value % 5 == 0
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.width(itemWidth)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(if (isMainMark) 40.dp else 24.dp)
                                .width(2.dp)
                                .background(PrimaryWhite)
                        )
                        if (isMainMark) {
                            Text(
                                text = value.toString(),
                                fontSize = 14.sp,
                                color = PrimaryWhite,
                                modifier = Modifier.padding(top = 4.dp),
                                maxLines = 1
                            )
                        }
                    }
                }
            }

            // Línea fija al centro
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(60.dp)
                    .width(4.dp)
                    .background(PrimaryWhite, shape = RoundedCornerShape(2.dp))
            )
        }
    }
}

@Composable
fun PesoRulerPickerKg(
    pesoSeleccionado: Int,
    onPesoSeleccionado: (Int) -> Unit,
    min: Int = 40,
    max: Int = 150
) {
    val items = (min..max).toList()
    val listState = rememberLazyListState()
    val itemWidth = 36.dp
    val density = LocalDensity.current

    val itemWidthPx = with(density) { itemWidth.toPx() }
    val centerOffsetPx = itemWidthPx * 3

    // Posición inicial del scroll
    LaunchedEffect(Unit) {
        listState.scrollToItem(
            index = pesoSeleccionado - min,
            scrollOffset = -itemWidthPx.toInt() / 2
        )
    }

    val layoutInfo = listState.layoutInfo
    val visibleItems = layoutInfo.visibleItemsInfo
    val center = layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset / 2

    val itemCentrado = visibleItems.minByOrNull {
        abs((it.offset + it.size / 2) - center)
    }?.index

    LaunchedEffect(itemCentrado) {
        itemCentrado?.let { index ->
            if (index in items.indices) {
                val valorCorregido = (items[index] + 1).coerceIn(min, max)
                if (valorCorregido != pesoSeleccionado) {
                    onPesoSeleccionado(valorCorregido)
                }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "$pesoSeleccionado Kg",
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryWhite
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            LazyRow(
                state = listState,
                contentPadding = PaddingValues(horizontal = 100.dp),
                verticalAlignment = Alignment.CenterVertically,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
            ) {
                items(items.size) { index ->
                    val value = items[index]
                    val isMainMark = value % 5 == 0
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.width(itemWidth)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(if (isMainMark) 40.dp else 24.dp)
                                .width(2.dp)
                                .background(PrimaryWhite)
                        )
                        if (isMainMark) {
                            Text(
                                text = value.toString(),
                                fontSize = 14.sp,
                                color = PrimaryWhite,
                                modifier = Modifier.padding(top = 4.dp),
                                maxLines = 1
                            )
                        }
                    }
                }
            }

            // Indicador central
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(60.dp)
                    .width(4.dp)
                    .background(PrimaryWhite, shape = RoundedCornerShape(2.dp))
            )
        }
    }
}



