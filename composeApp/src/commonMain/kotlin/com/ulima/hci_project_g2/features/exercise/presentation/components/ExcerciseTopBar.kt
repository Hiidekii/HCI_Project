package com.ulima.hci_project_g2.features.exercise.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ulima.hci_project_g2.core.presentation.SecondaryDarkGray
import hci_project.composeapp.generated.resources.Res
import hci_project.composeapp.generated.resources.ic_flechaatras
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseTopBar(
    onBackClick: () -> Unit,
    title: String
){
    CenterAlignedTopAppBar(
        navigationIcon = {
            Surface(
                color = SecondaryDarkGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(52.dp)            // controla el tamaño del botón
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_flechaatras),
                        contentDescription = "Regresar",
                        modifier = Modifier.size(22.dp),
                        tint = Color.White
                    )
                }
            }
        },
        title = {
            Text(
                text = title,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}