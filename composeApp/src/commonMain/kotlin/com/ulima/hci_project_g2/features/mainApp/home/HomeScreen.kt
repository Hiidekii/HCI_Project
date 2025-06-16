package com.ulima.hci_project_g2.features.mainApp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.PrimaryBlack
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import hci_project.composeapp.generated.resources.Res
import hci_project.composeapp.generated.resources.img_introduccionRutina
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HomeHeader()
        Spacer(modifier = Modifier.height(20.dp))
        SemanaSelector()
        Spacer(modifier = Modifier.height(16.dp))
        RutinaLista()
    }
}

@Composable
fun HomeHeader() {
    val currentDate = remember {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val monthName = now.month.name.lowercase().replaceFirstChar { it.uppercase() }
        "${monthName} ${now.dayOfMonth}, ${now.year}"
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(PrimaryBlack)
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = currentDate,
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
                Text(
                    text = "Hola, Luis",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }
        }
    }
}

@Composable
fun SemanaSelector() {
    val semanas = listOf("SEMANA 1", "SEMANA 2", "SEMANA 3", "SEMANA 4")
    var selected by remember { mutableStateOf(1) }

    Column {
        Text(
            text = "Mi Rutina",
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = PrimaryBlack
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(semanas) { index, semana ->
                Text(
                    text = semana,
                    modifier = Modifier
                        .padding(end = 30.dp)
                        .clickable { selected = index },
                    fontWeight = if (selected == index) FontWeight.Bold else FontWeight.Normal,
                    color = if (selected == index) PrimaryBlack else Color.Gray,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun RutinaLista() {
    val rutinas = listOf(
        Triple("Fuerza superior 1", "https://via.placeholder.com/150", Pair("55min", "412kcal")),
        Triple("Fuerza inferior 1", "https://via.placeholder.com/150", Pair("45min", "370kcal")),
        Triple("Cardio explosivo", "https://via.placeholder.com/150", Pair("25min", "450kcal"))
    )

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        itemsIndexed(rutinas) { index, rutina ->
            Row(verticalAlignment = Alignment.Top) {
                // Texto rotado del día
                Text(
                    text = "DÍA ${index + 1}",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .graphicsLayer { rotationZ = -90f }
                        .padding(bottom = 4.dp)
                )
                
                Column(
                    modifier = Modifier
                        .width(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Círculo con ícono (alineado arriba)
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (index == 0) PrimaryOrange else Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        if (index == 0) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    // Línea que baja desde el círculo
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .width(2.dp)
                            .height(150.dp) // igual a la altura de tu RutinaCard
                            .background(Color.LightGray)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                RutinaCard(
                    nombre = rutina.first,
                    imageUrl = rutina.second,
                    duracion = rutina.third.first,
                    calorias = rutina.third.second
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun RutinaCard(
    nombre: String,
    imageUrl: String,
    duracion: String,
    calorias: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.DarkGray)
    ) {
        // Puedes usar Coil si estás en Android para cargar imageUrl
        Image(
            painter = painterResource(Res.drawable.img_introduccionRutina), // cámbialo si usas Coil
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.9f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = duracion, color = Color.White, fontWeight = FontWeight.Bold)
                Text(text = calorias, color = Color.White, fontWeight = FontWeight.Bold)
            }
            Column {
                Text(
                    text = nombre,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(text = "5 Ejercicios", color = Color.White)
            }
        }
    }
}

