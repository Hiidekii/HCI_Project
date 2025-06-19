package com.ulima.hci_project_g2.features.mainApp.presentation.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.ulima.hci_project_g2.core.presentation.PrimaryBlack
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import com.ulima.hci_project_g2.features.mainApp.data.RutinasRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToExerciseDetail: (String, Int) -> Unit,
    onNavigateToRoutineDetail: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HomeHeader()
        Spacer(modifier = Modifier.height(20.dp))
        SemanaSelector()
        Spacer(modifier = Modifier.height(16.dp))
        RutinaLista(onRoutineClick = { routineName ->
            onNavigateToRoutineDetail(routineName)
        })
    }
}

@Composable
fun HomeHeader() {
    val currentDate = remember {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val monthName = now.month.name.lowercase().replaceFirstChar { it.uppercase() }
        "$monthName ${now.dayOfMonth}, ${now.year}"
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
                Text(text = currentDate, color = Color.LightGray, fontSize = 16.sp)
                Text(text = "Hola, Luis", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp)
            }
        }
    }
}

@Composable
fun SemanaSelector() {
    val semanas = listOf("SEMANA 1", "SEMANA 2", "SEMANA 3", "SEMANA 4")
    var selected by remember { mutableStateOf(1) }

    Column {
        Text("Mi Rutina", modifier = Modifier.padding(start = 16.dp), fontWeight = FontWeight.Bold, fontSize = 24.sp, color = PrimaryBlack)
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            itemsIndexed(semanas) { index, semana ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .clickable { selected = index }
                ) {
                    Text(
                        text = semana,
                        fontWeight = if (selected == index) FontWeight.Bold else FontWeight.Normal,
                        color = if (selected == index) PrimaryBlack else Color.Gray,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    if (selected == index) {
                        Box(
                            modifier = Modifier
                                .height(3.dp)
                                .width(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(PrimaryOrange)
                        )
                    } else {
                        Spacer(modifier = Modifier.height(3.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun RutinaLista(onRoutineClick: (String) -> Unit) {
    val rutinas = remember { RutinasRepository().obtenerRutinas() }

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

@Composable
fun RutinaCard(nombre: String, image: DrawableResource, duracion: String, calorias: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.DarkGray)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(image),
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
                Text(text = nombre, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "5 Ejercicios", color = Color.White)
            }
        }
    }
}
