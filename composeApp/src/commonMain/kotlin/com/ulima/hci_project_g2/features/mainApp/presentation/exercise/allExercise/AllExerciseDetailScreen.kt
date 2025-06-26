package com.ulima.hci_project_g2.features.mainApp.presentation.exercise.allExercise

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ulima.hci_project_g2.features.mainApp.presentation.components.ExerciseTopBar
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.ExercisesViewModel
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.InfoBlock
import org.jetbrains.compose.resources.painterResource

@Composable
fun AllExerciseDetailScreen(
    exerciseName: String,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    exercisesViewModel: ExercisesViewModel
) {
    LaunchedEffect(Unit){
        exercisesViewModel.getExerciseByName(exerciseName)
    }

    val stateExercises = exercisesViewModel.state
    val exercise = stateExercises.selectedExercise

    Box(modifier = Modifier.fillMaxSize()) {
        if (exercise != null) {
            Image(
                painter = painterResource(exercise.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black)
                    )
                )
        )
    }

    Scaffold(
        topBar = {
            ExerciseTopBar(
                onBackClick = onBackClick,
                title = "" // Puedes poner el nombre si quieres
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 36.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .border(1.dp, Color.White, RoundedCornerShape(10.dp))
                    .padding(horizontal = 22.dp, vertical = 14.dp)
            ) {
                if (exercise != null) {
                    Text(
                        text = "${exercise.rewardPoints} Total",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (exercise != null) {
                Text(
                    text = exercise.name,
                    color = Color.White,
                    fontSize = 45.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp
                )
            }

            Spacer(modifier = Modifier.height(70.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (exercise != null) {
                    InfoBlock("${exercise.duration}min", "Tiempo")
                }
                HorizontalDivider(
                    modifier = Modifier.height(40.dp).width(1.dp),
                    color = Color.White
                )
                if (exercise != null) {
                    InfoBlock("${exercise.calories}kcal", "Calorías")
                }
                HorizontalDivider(
                    modifier = Modifier.height(40.dp).width(1.dp),
                    color = Color.White
                )
                if (exercise != null) {
                    InfoBlock(exercise.sets, "Sets")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { onNextClick() },
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryOrange,
                    contentColor = PrimaryWhite
                )
            ) {
                Text("Empezar", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}