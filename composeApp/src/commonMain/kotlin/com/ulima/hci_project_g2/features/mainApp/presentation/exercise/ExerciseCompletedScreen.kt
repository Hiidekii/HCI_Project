package com.ulima.hci_project_g2.features.mainApp.presentation.exercise

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.core.presentation.PrimaryWhite
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import hci_project.composeapp.generated.resources.Res
import hci_project.composeapp.generated.resources.ic_trofeo

@Composable
fun ExerciseCompletedScreen(
    calories: Int,
    duration: Int,
    points: Int,
    image: DrawableResource,
    dontShowPoints: Boolean,
    onReturn: () -> Unit,
) {
    // Estado para controlar la aparición animada de la notificación
    var showNotification by remember { mutableStateOf(false) }

    // Se lanza la animación al entrar a la pantalla
    LaunchedEffect(Unit) {
        delay(300) // Un pequeño suspenso para que no aparezca instantáneamente
        if (dontShowPoints){
            showNotification = false
        }else{
            showNotification = true
        }
    }

    println(dontShowPoints)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Imagen de fondo con opacidad
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.4f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ANIMACIÓN DE ENTRADA PARA LA NOTIFICACIÓN DE PUNTOS
            AnimatedVisibility(
                visible = showNotification,
                enter = slideInVertically(initialOffsetY = { -100 }) + fadeIn()
            ) {
                PointsNotification(points = points)
            }

            Spacer(modifier = Modifier.height(36.dp))
            Spacer(modifier = Modifier.weight(1f))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Ejercicio completo",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Quemaste $calories kcal",
                    fontSize = 18.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("$duration", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text("Min", fontSize = 16.sp, color = Color.Gray)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("$points", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text("puntos", fontSize = 16.sp, color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        onReturn()
                     },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryOrange,
                        contentColor = PrimaryWhite
                    )
                ) {
                    Text("Completado", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
fun PointsNotification(points: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFFF3F3F3))
            .padding(vertical = 16.dp, horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "Recoge tus puntos",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    fontSize = 18.sp
                )
                Text(
                    "+$points puntos",
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_trofeo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
