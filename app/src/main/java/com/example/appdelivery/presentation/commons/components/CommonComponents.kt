package com.example.appdelivery.presentation.commons.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonButton(
    title: String,
    isEnabled: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        ),
        enabled = isEnabled
    ) {
        if (!isLoading) {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        } else {
            CircularProgressIndicator(color = Color.White)
        }
    }
}

@Composable
fun CommonOutlinedInput(
    value: String,
    title: String,
    icon: ImageVector? = null,
    isPassword: Boolean = false,
    isHide: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    onIconPressed: (() -> Unit)? = null
) {
    val transformation = if (isPassword && isHide) {
        PasswordVisualTransformation()
    } else {
        visualTransformation
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(title) },
        shape = RoundedCornerShape(12.dp),
        trailingIcon = {
            when {
                isPassword -> {
                    IconButton(onClick = { onIconPressed?.invoke() }) {
                        Icon(
                            imageVector = if (isHide) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
                icon != null -> {
                    IconButton(onClick = { onIconPressed?.invoke() }) {
                        Icon(imageVector = icon, contentDescription = null)
                    }
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else keyboardType),
        visualTransformation = transformation,
        singleLine = true
    )
}
