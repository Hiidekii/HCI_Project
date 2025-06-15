import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ulima.hci_project_g2.core.presentation.PrimaryBlack
import com.ulima.hci_project_g2.core.presentation.PrimaryDarkGray
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.core.presentation.PrimaryWhite
import com.ulima.hci_project_g2.core.presentation.components.MyActionButton
import com.ulima.hci_project_g2.core.presentation.components.UlimaFitTopBar
import com.ulima.hci_project_g2.features.userData.presentation.UserDataViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CondicionFisicaScreen(
    onReturnClick: () -> Unit,
    onNextClick: () -> Unit,
    userDataViewModel: UserDataViewModel = koinViewModel()
) {
    var selectedCondicion by remember { mutableStateOf<Int>(3) }


    Scaffold(
        topBar = {
            UlimaFitTopBar(
                titulo = "Evaluación",
                pasoActual = 6,
                onBackClick = { onReturnClick() }
            )
        },
        containerColor = PrimaryWhite
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp, vertical = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text(
                    text = "¿Cómo calificarías tu condición física?",
                    fontWeight = FontWeight.Black,
                    fontSize = 36.sp,
                    color = PrimaryBlack,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // 1) Flags de habilitación
                    val canDecrease = selectedCondicion > 0
                    val canIncrease = selectedCondicion < 5

                    // 2) Botón “–”
                    Button(
                        onClick = { if (canDecrease) selectedCondicion-- },
                        enabled = canDecrease,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryOrange,
                            contentColor = Color.White,
                            disabledContainerColor = PrimaryOrange.copy(alpha = 0.3f),
                            disabledContentColor = Color.White.copy(alpha = 0.3f)
                        ),
                        modifier = Modifier.size(64.dp),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        Text(text = "–", fontSize = 50.sp)
                    }

                    Spacer(Modifier.width(70.dp))

                    Text(
                        text = selectedCondicion.toString(),
                        fontSize = 150.sp,
                        fontWeight = FontWeight.W900,
                        color = PrimaryBlack
                    )

                    Spacer(Modifier.width(70.dp))

                    // 3) Botón “+”
                    Button(
                        onClick = { if (canIncrease) selectedCondicion++ },
                        enabled = canIncrease,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryOrange,
                            contentColor = Color.White,
                            disabledContainerColor = PrimaryOrange.copy(alpha = 0.4f),
                            disabledContentColor = Color.White.copy(alpha = 0.4f)
                        ),
                        modifier = Modifier.size(64.dp),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        Text(text = "+", fontSize = 50.sp)
                    }
                }
                Text(
                    text = when (selectedCondicion) {
                        0 -> "Sedentario"
                        1 -> "Muy bajo"
                        2 -> "Bajo"
                        3 -> "Algo atlético"
                        4 -> "Atlético"
                        5 -> "Muy atlético"
                        else -> ""
                    },
                    fontSize = 45.sp,
                    color = PrimaryDarkGray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                MyActionButton(
                    text = "Continuar",
                    enabled = true,
                    isLoading = false,
                    onClick = {
                        userDataViewModel.saveFisicoPreferences(
                            selectedCondicion
                        )
                        onNextClick()
                    }
                )
            }
        }
    }
}
