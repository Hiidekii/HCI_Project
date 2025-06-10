package com.ulima.hci_project_g2.features.userData.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.ulima.hci_project_g2.core.presentation.PrimaryBlack
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.core.presentation.components.UlimaFitTopBar
import com.ulima.hci_project_g2.features.auth.presentation.login.components.MyActionButton

@Composable
fun EdadScreen(
    onReturnClick: () -> Unit,
    onNextClick: () -> Unit
){
    var edadSeleccionada by remember { mutableStateOf(17) }

    Scaffold(
        topBar = {
            UlimaFitTopBar(
                titulo = "Evaluación",
                pasoActual = 1,
                onBackClick = { onReturnClick() }
            )
        },
        containerColor = Color.White
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
                EdadPickerCarousel(
                    edadSeleccionada = edadSeleccionada,
                    onEdadSeleccionada = { edadSeleccionada = it }
                )
                Spacer(modifier = Modifier.weight(1f))
                MyActionButton(
                    text = "Continuar",
                    enabled = true,
                    isLoading = false,
                    onClick = {
                        onNextClick()
                    }
                )
            }
        }
    }
}

@Composable
fun EdadPickerCarousel(
    edadSeleccionada: Int,
    onEdadSeleccionada: (Int) -> Unit,
    minEdad: Int = 15,
    maxEdad: Int = 50
) {
    val edades = (minEdad..maxEdad).toList()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        listState.scrollToItem(edadSeleccionada - minEdad)
    }

    val layoutInfo = listState.layoutInfo
    val visibleItems = layoutInfo.visibleItemsInfo
    val center = layoutInfo.viewportEndOffset / 2

    val itemEnCentro = visibleItems.minByOrNull { item ->
        kotlin.math.abs((item.offset + item.size / 2) - center)
    }?.index

    LaunchedEffect(itemEnCentro) {
        itemEnCentro?.let { onEdadSeleccionada(edades[it]) }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp), // MÁS ALTO
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(edades.size) { index ->
                val edad = edades[index]
                val isSelected = edad == edadSeleccionada
                Text(
                    text = edad.toString(),
                    fontSize = if (isSelected) 88.sp else 68.sp, // MÁS GRANDE
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) Color.Transparent else Color.Gray,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            }
        }

        // Overlay centrado más grande
        Box(
            modifier = Modifier
                .background(PrimaryOrange, shape = RoundedCornerShape(24.dp)) // más redondo
                .padding(horizontal = 42.dp, vertical = 22.dp) // MÁS ESPACIO
        ) {
            Text(
                text = edadSeleccionada.toString(),
                fontSize = 68.sp, // MÁS GRANDE
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
