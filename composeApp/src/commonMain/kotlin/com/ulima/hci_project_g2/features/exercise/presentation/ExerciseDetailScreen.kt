package com.ulima.hci_project_g2.features.exercise.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.core.presentation.PrimaryWhite
import com.ulima.hci_project_g2.features.exercise.domain.Exercise
import com.ulima.hci_project_g2.features.exercise.presentation.components.ExerciseTopBar
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(
    exercise: Exercise,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(exercise.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )
                )
        )
    }
    Scaffold(
        topBar = {
            ExerciseTopBar(
                onBackClick = onBackClick,
                title = ""
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 36.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Badge de puntos totales
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 22.dp, vertical = 14.dp)
            ) {
                Text(
                    text = "${exercise.rewardPoints} Total",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Título del ejercicio
            Text(
                text = exercise.name,
                color = Color.White,
                fontSize = 45.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )
            Spacer(modifier = Modifier.height(70.dp))

            // Fila de estadísticas: tiempo, calorías, sets
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tiempo
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${exercise.duration}min",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(9.dp))
                    Text(
                        text = "Tiempo",
                        color = Color.LightGray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                // Separador
                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .height(40.dp)
                        .width(1.dp)
                )
                // Calorías
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${exercise.calories}kcal",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(9.dp))
                    Text(
                        text = "Calorías",
                        color = Color.LightGray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                // Separador
                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .height(40.dp)
                        .width(1.dp)
                )
                // Sets
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${exercise.sets}",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(9.dp))
                    Text(
                        text = "Sets",
                        color = Color.LightGray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { onNextClick() },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryOrange,
                    contentColor = PrimaryWhite
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Empezar",
                        fontSize = 23.sp,
                        modifier = Modifier.padding(horizontal = 105.dp, vertical = 9.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}



