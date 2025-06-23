package com.ulima.hci_project_g2.features.mainApp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.features.mainApp.data.RutinasRepository
import com.ulima.hci_project_g2.features.mainApp.domain.Rutina

@Composable
fun RutinaLista(
    rutinas: List<Rutina>,
    onRoutineClick: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 8.dp,
            bottom = 100.dp // espacio para el BottomBar
        )
    ) {
        itemsIndexed(rutinas) { index, rutina ->
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    text = "DÍA ${index + 1}",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.graphicsLayer { rotationZ = -90f }
                )
                Column(
                    modifier = Modifier.width(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (index == 0) PrimaryOrange else Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        if (index == 0) {
                            Icon(Icons.Default.PlayArrow, contentDescription = "Play", tint = Color.White, modifier = Modifier.size(16.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .width(2.dp)
                            .height(180.dp)
                            .background(Color.LightGray)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                RutinaCard(
                    nombre = rutina.nombre,
                    image = rutina.imagen,
                    duracion = rutina.duracion,
                    calorias = rutina.calorias,
                    onClick = { onRoutineClick(rutina.nombre) }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}