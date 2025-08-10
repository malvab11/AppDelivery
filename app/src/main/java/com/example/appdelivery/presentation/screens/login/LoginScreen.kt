package com.example.appdelivery.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdelivery.R

// Paleta de colores personalizados
private val SmashRed = Color(0xFFFF0000)
private val SmashBlack = Color(0xFF000000)
private val SmashGray = Color(0xFF757575)

/**
 * Pantalla principal de inicio de sesión.
 *
 * Conecta los valores del [LoginViewModel] con la UI mediante LiveData
 * y muestra los elementos visuales: imagen, campos de entrada, botones y acciones.
 *
 * @param modifier Modificador opcional para personalizar el layout.
 * @param padding Padding proveniente de Scaffold o layouts superiores.
 * @param viewModel ViewModel encargado de la lógica y el estado de la pantalla.
 * @param onRegisterPressed Navigación a pantalla de Registro (Modificar en AppNavGraph)
 */
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    viewModel: LoginViewModel,
    onRegisterPressed: () -> Unit
) {
    // Observamos el estado expuesto por el ViewModel
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val isHide by viewModel.isHide.observeAsState(true)
    val isEnabled by viewModel.isEnabled.observeAsState(false)
    val isLoading by viewModel.isLoading.observeAsState(false)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen superior
        LoginImage(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            imageRes = R.drawable.smash_boys_logo
        )

        // Contenido principal
        LoginContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            email = email,
            password = password,
            isHide = isHide,
            isEnabled = isEnabled,
            isLoading = isLoading,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onVisibility = viewModel::onVisibilityClick,
            onLoginPressed = viewModel::onLoginPressed,
            onRegisterPressed = onRegisterPressed,
        )
    }
}

/**
 * Imagen superior con fondo rojo y esquinas redondeadas en la parte inferior.
 */
@Composable
private fun LoginImage(modifier: Modifier, imageRes: Int) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(SmashRed),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(1f)
        )
    }
}

/**
 * Contenido principal del formulario de login.
 *
 * Contiene:
 * - Títulos de bienvenida.
 * - Campo de email.
 * - Campo de contraseña con visibilidad alternable.
 * - Botón para recuperar contraseña.
 * - Botón de inicio de sesión con estado de carga.
 * - Botón de inicio de sesión con Google.
 * - Enlace para registrarse.
 */
@Composable
private fun LoginContent(
    modifier: Modifier,
    email: String,
    password: String,
    isHide: Boolean,
    isEnabled: Boolean,
    isLoading: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onVisibility: () -> Unit,
    onLoginPressed: () -> Unit,
    onRegisterPressed: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título principal
        Text(
            text = "Hola Burguer L❤️vers",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = SmashBlack
        )

        // Subtítulo
        Text(
            text = "Ingresa tus datos para iniciar sesión",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            color = SmashGray
        )

        // Campo de email
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Correo Electrónico") },
            shape = RoundedCornerShape(12.dp),
            trailingIcon = { Icon(Icons.Default.AlternateEmail, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )

        // Campo de contraseña con icono de visibilidad
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Contraseña") },
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                IconButton(onClick = onVisibility) {
                    Icon(
                        imageVector = if (isHide) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (isHide) "Mostrar contraseña" else "Ocultar contraseña"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (isHide) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = true
        )

        // Opción para recuperar contraseña
        Text(
            text = "Recuperar Contraseña",
            fontSize = 14.sp,
            color = SmashRed,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { /* TODO: Navegar a recuperar contraseña */ }
        )

        // Botón principal de login
        Button(
            onClick = onLoginPressed,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SmashRed,
                contentColor = Color.White
            ),
            enabled = isEnabled
        ) {
            if (!isLoading) {
                Text("Ingresar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            } else {
                CircularProgressIndicator(color = Color.White)
            }
        }

        // Botón de Google
        GoogleSignInButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* TODO: Iniciar sesión con Google */ }
        )

        // Enlace de registro
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¿No tienes una cuenta?", fontSize = 14.sp, color = SmashGray)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Regístrate",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                color = SmashRed,
                modifier = Modifier.clickable { onRegisterPressed() }
            )
        }
    }
}

/**
 * Botón personalizado para iniciar sesión con Google.
 *
 * @param modifier Modificador opcional para personalizar el layout.
 * @param onClick Acción que se ejecuta al presionar el botón.
 */
@Composable
private fun GoogleSignInButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = SmashBlack
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_google_logo),
            contentDescription = "Google Logo",
            modifier = Modifier
                .size(20.dp)
                .padding(end = 8.dp)
        )
        Text("Acceder con Google", fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}
