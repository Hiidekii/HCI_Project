package com.ulima.hci_project_g2.features.mainApp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.PrimaryBlack
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange

@Composable
fun SemanaSelector(
    semanas: List<String>,
    selected: Int,
    onWeekSelected: (Int) -> Unit
) {
    Column {
        Text(
            "Mi Rutina",
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = PrimaryBlack
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            itemsIndexed(semanas) { index, semana ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .clickable { onWeekSelected(index) }
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
