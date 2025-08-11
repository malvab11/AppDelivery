package com.example.appdelivery.presentation.screens.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneIphone
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.appdelivery.presentation.commons.components.CommonButton
import com.example.appdelivery.presentation.commons.components.CommonOutlinedInput

/**
 * Pantalla de registro de usuario que permite ingresar información personal y de seguridad,
 * aceptar políticas y registrar una cuenta nueva.
 *
 * @param modifier Modificador para personalizar el layout.
 * @param viewModel ViewModel asociado que contiene el estado y la lógica.
 * @param onBackPressed Acción a ejecutar al presionar el botón de regresar.
 * @param onRegisterPressed Acción a ejecutar al presionar el botón de registrar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
    onBackPressed: () -> Unit,
    navigateToChoose: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.message) {
        uiState.message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            if (!uiState.isError) navigateToChoose()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Cuenta", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackPressed()
                        viewModel.clearValues()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = "Regresar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            InformationInputs(
                modifier = Modifier.padding(24.dp),
                name = uiState.name,
                lastName = uiState.lastName,
                documentId = uiState.documentId,
                onNameChange = { newValue ->
                    viewModel.updateField { state -> state.copy(name = newValue) }
                },
                onLastNameChange = { newValue ->
                    viewModel.updateField { state -> state.copy(lastName = newValue) }
                },
                onDocumentIdChange = { newValue ->
                    viewModel.updateField { state -> state.copy(documentId = newValue) }
                },
            )
            SecurityInputs(
                modifier = Modifier.padding(24.dp),
                email = uiState.email,
                password = uiState.password,
                cellphone = uiState.cellphone,
                isHide = uiState.isHide,
                onEmailChange = { newValue ->
                    viewModel.updateField { state -> state.copy(email = newValue) }
                },
                onPasswordChange = { newValue ->
                    viewModel.updateField { state -> state.copy(password = newValue) }
                },
                onCellPhoneChange = { newValue ->
                    viewModel.updateField { state -> state.copy(cellphone = newValue) }
                },
                onVisibility = { viewModel.togglePasswordVisibility() },
            )

            PolicyPart(
                modifier = Modifier.padding(24.dp),
                isChecked = uiState.isChecked,
                onCheckedChange = { viewModel.toggleTermsCheck() },
            )
            ButtonsPart(
                modifier = Modifier.padding(24.dp),
                isEnabled = uiState.isEnabled,
                isLoading = uiState.isLoading,
                onRegisterPressed = { viewModel.registerUser() }
            )
        }
    }
}

/**
 * Sección de entrada de datos personales: nombres, apellidos y documento de identidad.
 *
 * @param modifier Modificador para el layout.
 * @param name Valor actual del campo de nombres.
 * @param lastName Valor actual del campo de apellidos.
 * @param documentId Valor actual del campo de documento de identidad.
 * @param onNameChange Callback para cuando cambia el nombre.
 * @param onLastNameChange Callback para cuando cambia el apellido.
 * @param onDocumentIdChange Callback para cuando cambia el documento.
 */
@Composable
fun InformationInputs(
    modifier: Modifier,
    name: String,
    lastName: String,
    documentId: String,
    onNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onDocumentIdChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(text = "Información personal")
        CommonOutlinedInput(
            value = name,
            title = "Nombres",
            icon = Icons.Default.Person,
            isPassword = false,
            onValueChange = onNameChange
        )
        CommonOutlinedInput(
            value = lastName,
            title = "Apellidos",
            icon = Icons.Default.Person,
            isPassword = false,
            onValueChange = onLastNameChange
        )
        CommonOutlinedInput(
            value = documentId,
            title = "Documento Identidad",
            icon = Icons.Default.CreditCard,
            keyboardType = KeyboardType.Number,
            isPassword = false,
            onValueChange = onDocumentIdChange
        )
    }
}

/**
 * Sección de entrada de datos de seguridad: correo electrónico, contraseña y celular.
 *
 * @param modifier Modificador para el layout.
 * @param email Valor actual del campo email.
 * @param password Valor actual del campo contraseña.
 * @param cellphone Valor actual del campo celular.
 * @param isHide Estado de visibilidad de la contraseña.
 * @param onEmailChange Callback para cuando cambia el email.
 * @param onPasswordChange Callback para cuando cambia la contraseña.
 * @param onCellPhoneChange Callback para cuando cambia el celular.
 * @param onVisibility Callback para alternar visibilidad de contraseña.
 */
@Composable
fun SecurityInputs(
    modifier: Modifier,
    email: String,
    password: String,
    cellphone: String,
    isHide: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onCellPhoneChange: (String) -> Unit,
    onVisibility: () -> Unit
) {
    Column(modifier = modifier) {
        Text(text = "Seguridad")
        CommonOutlinedInput(
            value = email,
            title = "Correo Electrónico",
            icon = Icons.Default.AlternateEmail,
            isPassword = false,
            onValueChange = onEmailChange
        )
        CommonOutlinedInput(
            value = password,
            title = "Contraseña",
            isPassword = true,
            isHide = isHide,
            onValueChange = onPasswordChange,
            onIconPressed = onVisibility
        )
        CommonOutlinedInput(
            value = cellphone,
            title = "Celular",
            icon = Icons.Default.PhoneIphone,
            keyboardType = KeyboardType.Number,
            isPassword = false,
            onValueChange = onCellPhoneChange
        )
    }
}

/**
 * Componente para aceptar términos y condiciones mediante un switch.
 *
 * @param modifier Modificador para el layout.
 * @param isChecked Estado actual del switch.
 * @param onCheckedChange Callback para alternar estado del switch.
 */
@Composable
fun PolicyPart(modifier: Modifier, isChecked: Boolean, onCheckedChange: () -> Unit) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        Switch(
            checked = isChecked,
            onCheckedChange = { onCheckedChange() },
            colors = SwitchDefaults.colors(
                checkedTrackColor = Color.Red,
                checkedThumbColor = Color.White,
            )
        )
        Text("He leído y acepto los Términos y Condiciones y Políticas y Tratamiento de Datos Personales")
    }
}

/**
 * Botón para enviar el formulario de registro.
 *
 * @param isEnabled Indica si el botón está habilitado.
 * @param isLoading Indica si se muestra un indicador de carga.
 * @param modifier Modificador para el layout.
 * @param onRegisterPressed Acción al presionar el botón.
 */
@Composable
fun ButtonsPart(
    isEnabled: Boolean,
    isLoading: Boolean,
    modifier: Modifier,
    onRegisterPressed: () -> Unit
) {
    Box(modifier = modifier) {
        CommonButton(
            title = "Registrarme",
            isEnabled = isEnabled,
            isLoading = isLoading,
            onClick = onRegisterPressed
        )
    }
}
