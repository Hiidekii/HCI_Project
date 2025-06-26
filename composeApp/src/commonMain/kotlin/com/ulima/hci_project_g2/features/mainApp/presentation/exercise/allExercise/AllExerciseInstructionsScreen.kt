package com.ulima.hci_project_g2.features.mainApp.presentation.exercise.allExercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ulima.hci_project_g2.features.mainApp.presentation.components.ExerciseTopBar
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.ExercisesViewModel
import org.jetbrains.compose.resources.painterResource

@Composable
fun AllExerciseIntructionsScreen(
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

    Scaffold(
        topBar = {
            if (exercise != null) {
                ExerciseTopBar(onBackClick = onBackClick, title = exercise.name)
            }
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TARJETA BLANCA
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 400.dp) // aquí se agranda el área blanca
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                if (exercise != null) {
                    Image(
                        painter = painterResource(exercise.gif),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (exercise != null) {
                    Text(
                        text = exercise.sets,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                if (exercise != null) {
                    Text(
                        text = "${exercise.rewardPoints} Total",
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
            ) {
                if (exercise != null) {
                    Text(
                        text = "\uD83D\uDCAA Cómo usar la ${exercise.name}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                exercise?.instructions?.forEachIndexed { index, step ->
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Text(
                            "${index + 1}",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = step,
                            color = Color.White,
                            fontSize = 16.sp,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onNextClick,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryOrange,
                    contentColor = PrimaryWhite
                )
            ) {
                Text(
                    "Completar",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}