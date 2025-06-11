package com.ulima.hci_project_g2.core.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ulima.hci_project_g2.core.presentation.PrimaryDarkGray

@Composable
fun CustomRadio(
    selected: Boolean,
    onClick: () -> Unit,
    radioSize: Dp = 18.dp,
    cornerRadius: Dp = 6.dp
) {
    val strokeWidth = 3.dp
    Box(
        Modifier
            .size(radioSize)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val pxSize = radioSize.toPx()
            // borde
            drawRoundRect(
                color       = PrimaryDarkGray,
                size        = Size(pxSize, pxSize),
                cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx()),
                style       = Stroke(width = strokeWidth.toPx())
            )
            if (selected) {
                // relleno interior
                val innerPx = pxSize - strokeWidth.toPx() * 2f
                drawRoundRect(
                    color        = PrimaryDarkGray,
                    topLeft      = Offset(strokeWidth.toPx(), strokeWidth.toPx()),
                    size         = Size(innerPx, innerPx),
                    cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx())
                )
            }
        }
    }
}