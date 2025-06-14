package com.ulima.hci_project_g2.features.userData.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.PrimaryBlack
import com.ulima.hci_project_g2.core.presentation.PrimaryDarkGray
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.core.presentation.PrimaryWhite
import com.ulima.hci_project_g2.core.presentation.components.MyActionButton
import com.ulima.hci_project_g2.core.presentation.components.UlimaFitTopBar
import hci_project.composeapp.generated.resources.Res
import hci_project.composeapp.generated.resources.ic_heart
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun CondicionFisicaScreen (
    onNextClick: () -> Unit,
    onReturnClick: () -> Unit,
    userDataViewModel: UserDataViewModel = koinViewModel()
){
    val ratingRange = 1..5
    var selectedRating by remember { mutableStateOf(3) }
    val labels = mapOf(
        1 to "Muy baja", 2 to "Baja", 3 to "Algo atlético",
        4 to "Atlético", 5 to "Muy atlético"
    )

    Scaffold(
        topBar = {
            UlimaFitTopBar(
                titulo = "Evaluación",
                pasoActual = 4,
                onBackClick = { onReturnClick() }
            )
        },
        containerColor = PrimaryWhite
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "¿Cómo calificarías tu nivel de condición física?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // ⚙️ Slider curvo con thumb móvil
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()               // ocupa todo el ancho
                    .aspectRatio(1f)
                    .pointerInput(Unit) {
                        detectDragGestures { change, _ ->
                            val center = Offset(((size.width / 2).toFloat()), size.height.toFloat())
                            val touch = change.position
                            val radians = atan2(
                                (touch.y - center.y).toDouble(),
                                (touch.x - center.x).toDouble()
                            )
                            val angleDeg = (radians * 180f / PI).toFloat()
                            val startAngle = 180f
                            var relative = angleDeg - startAngle
                            if (relative < 0f) relative += 360f
                            val sweepPerStep = 90f / (ratingRange.last - ratingRange.first)
                            val newRating = (relative / sweepPerStep).roundToInt() + 1
                            selectedRating = newRating.coerceIn(ratingRange)
                        }
                    }
            ) {
                // Medidas en px
                val boxW = with(LocalDensity.current) { maxWidth.toPx() }
                val boxH = boxW                   // como es cuadrado
                val center = Offset(boxW, boxH)
                val radius = boxW / 2f
                val startAngle = 180f
                val sweepPerStep = 90f / (ratingRange.last - ratingRange.first)
                val sweep    = (selectedRating - 1) * sweepPerStep
                val angleDeg   = startAngle + sweep               // defínelo como Float o Double
                val angleRad   = angleDeg * (PI / 180.0)           // convierte grados→radianes
                val thumbX     = center.x + cos(angleRad).toFloat() * radius
                val thumbY     = center.y + sin(angleRad).toFloat() * radius

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val strokeWidth = 16.dp.toPx()
                    val arcSize     = Size(radius * 2, radius * 2)
                    val arcTopLeft  = Offset(center.x - radius, center.y - radius)
                    // Arco de fondo
                    drawArc(
                        color     = PrimaryDarkGray,
                        startAngle= startAngle,
                        sweepAngle= 90f,
                        useCenter = false,
                        style     = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                        topLeft   = arcTopLeft,
                        size      = arcSize
                    )
                    // Arco de progreso
                    drawArc(
                        color     = PrimaryBlack,
                        startAngle= startAngle,
                        sweepAngle= sweep,
                        useCenter = false,
                        style     = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                        topLeft   = arcTopLeft,
                        size      = arcSize
                    )
                    // Ticks
                    val tickLen = 16.dp.toPx()
                    ratingRange.forEach { i ->
                        // 1) Calculas el ángulo en grados
                        val angDeg = startAngle + (i - 1) * sweepPerStep
                        // 2) Lo pasas a radianes sin Math.toRadians
                        val angRad = angDeg * (PI / 180.0)
                        // 3) Calculas los puntos exterior e interior
                        val outer = center + Offset(
                            cos(angRad).toFloat() * radius,
                            sin(angRad).toFloat() * radius
                        )
                        val inner = center + Offset(
                            cos(angRad).toFloat() * (radius - tickLen),
                            sin(angRad).toFloat() * (radius - tickLen)
                        )
                        // 4) Dibujas la línea
                        drawLine(
                            color       = PrimaryDarkGray,
                            start       = inner,
                            end         = outer,
                            strokeWidth = 2.dp.toPx()
                        )
                    }
                }

                // Thumb móvil
                Card(
                    shape    = RoundedCornerShape(8.dp),
                    colors   = CardDefaults.cardColors(containerColor = PrimaryOrange),
                    modifier = Modifier
                        .size(48.dp)
                        .offset {
                            // centrar el Card en el punto (thumbX, thumbY)
                            IntOffset(
                                x = (thumbX - 24.dp.toPx()).roundToInt(),
                                y = (thumbY - 24.dp.toPx()).roundToInt()
                            )
                        }
                ) {
                    Icon(
                        painter           = painterResource(Res.drawable.ic_heart),
                        contentDescription= null,
                        tint              = PrimaryWhite,
                        modifier          = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            MyActionButton(
                text      = "Continuar",
                enabled   = true,
                isLoading = false,
                onClick   = { onNextClick() },
                modifier  = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )
        }
    }
}