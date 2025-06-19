package com.ulima.hci_project_g2.features.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileChart() {
    val puntos = listOf(65, 80, 70, 75, 95)
    val dias = listOf("Lun", "Mar", "Mierc", "Juev", "Sab")
    val valoresY = listOf(100, 90, 80, 70, 60)
    val chartMaxHeight = 140.dp

    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF4F4F4))
            .padding(16.dp)
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("\uD83C\uDFC6 Puntos diarios", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(
                "Semanal",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE5E5E5))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Gráfico principal
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(chartMaxHeight + 56.dp) // altura extra para flotante + nombres
        ) {
            val fullHeight = maxHeight

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Bottom
            ) {
                // Eje Y
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(30.dp)
                        .padding(bottom = 24.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ) {
                    valoresY.forEach { valor ->
                        Text(text = valor.toString(), fontSize = 12.sp, color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Barras + etiquetas flotantes encima
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {
                    puntos.forEachIndexed { index, valor ->
                        val normalizedHeight = ((valor - 60) / 40f).coerceIn(0f, 1f) * chartMaxHeight.value

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                            modifier = Modifier
                                .fillMaxHeight()
                                .clickable { selectedIndex = if (selectedIndex == index) null else index }
                        ) {
                            // Etiqueta flotante centrada encima de la barra
                            if (selectedIndex == index) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.Black)
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = valor.toString(),
                                        fontSize = 12.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                            } else {
                                Spacer(modifier = Modifier.height(24.dp))
                            }

                            Box(
                                modifier = Modifier
                                    .height(normalizedHeight.dp)
                                    .width(12.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(if (index == selectedIndex) Color.Black else Color.LightGray)
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = dias[index], fontSize = 12.sp, color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}
