package com.ulima.hci_project_g2.core.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.DarkBlue
import com.ulima.hci_project_g2.core.presentation.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UlimaFitTopBar(
    titulo: String,
    pasoActual: Int,
    onBackClick: () -> Unit,
){
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Regresar"
                )
            }
        },
        title = {
            Text(
                text = titulo,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        },
        actions = {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LightBlue
                ),
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "$pasoActual de 6",
                    color = DarkBlue,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}