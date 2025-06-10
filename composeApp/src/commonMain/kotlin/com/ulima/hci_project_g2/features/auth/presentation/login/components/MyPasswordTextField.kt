package com.ulima.hci_project_g2.features.auth.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.core.presentation.PrimaryWhite
import hci_project.composeapp.generated.resources.Res
import hci_project.composeapp.generated.resources.eye_closed
import hci_project.composeapp.generated.resources.eye_opened
import hci_project.composeapp.generated.resources.lock
import org.jetbrains.compose.resources.painterResource

@Composable
fun MyPasswordTextField(
    state: MutableState<String>,
    isPasswordVisible: Boolean,
    onTogglePasswordVisibilityClick: () -> Unit,
    hint: String,
    title: String?,
    modifier: Modifier = Modifier,
) {
    var isFocused by remember {
        mutableStateOf(false)
    }

    val visualTransformation = if (isPasswordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (title != null) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        BasicTextField(
            value = state.value,
            onValueChange = {
                if (!it.contains("\n")) {
                    state.value = it
                }
            },
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            cursorBrush = SolidColor(PrimaryOrange),
            textStyle = TextStyle(
                color = if (isFocused){
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onBackground
                }
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (isFocused) {
                        PrimaryOrange.copy(
                            alpha = 0.05f
                        )
                    } else {
                        PrimaryWhite
                    }
                )
                .border(
                    width = 1.dp,
                    color = if (isFocused) {
                        PrimaryOrange
                    } else {
                        Color.Gray
                    },
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 12.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            minLines = 1,
            maxLines = 1,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.lock),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (state.value.isEmpty()) {
                            Text(
                                text = hint,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                    alpha = 0.4f
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        innerTextField()
                    }
                    IconButton(onClick = onTogglePasswordVisibilityClick) {
                        Icon(
                            painter = if (!isPasswordVisible) {
                                painterResource(Res.drawable.eye_opened)
                            } else {
                                painterResource(Res.drawable.eye_closed)
                            },
                            contentDescription = if (isPasswordVisible) {
                                "Show password"
                            } else {
                                "Hide password"
                            },
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        )
    }
}
