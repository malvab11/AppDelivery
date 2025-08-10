package com.example.appdelivery.presentation.screens.register

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.appdelivery.presentation.commons.components.CommonButton
import com.example.appdelivery.presentation.commons.components.CommonOutlinedInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
    onBackPressed: () -> Unit,
    onRegisterPressed: () -> Unit
) {

    val name by viewModel.name.observeAsState("")
    val lastName by viewModel.lastName.observeAsState("")
    val documentId by viewModel.documentId.observeAsState("")
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val cellphone by viewModel.cellphone.observeAsState("")
    val isHide by viewModel.isHide.observeAsState(true)
    val isChecked by viewModel.isChecked.observeAsState(false)
    val isEnabled by viewModel.isEnabled.observeAsState(false)
    val isLoading by viewModel.isLoading.observeAsState(false)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Crear Cuenta", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = null
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            InformationInputs(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                name = name,
                lastName = lastName,
                documentId = documentId,
                onNameChange = { viewModel.onNameChange(name = it) },
                onLastNameChange = { viewModel.onLastNameChange(lastName = it) },
                onDocumentIdChange = { viewModel.onDocumentIdChange(documentId = it) },
            )
            SecurityInputs(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                email = email,
                password = password,
                cellphone = cellphone,
                isHide = isHide,
                onEmailChange = { viewModel.onEmailChange(email = it) },
                onPasswordChange = { viewModel.onPasswordChange(password = it) },
                onCellPhoneChange = { viewModel.onCellPhoneChage(cellphone = it) },
                onVisibility = { viewModel.onVisibilityClick() },
            )
            PolicyPart(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                isChecked = isChecked,
                onCheckedChange = { viewModel.onCheckChange() },
            )
            ButtonsPart(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                isEnabled = isEnabled, isLoading = isLoading,
                onRegisterPressed = onRegisterPressed
            )
        }
    }

}

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
        // Campo de nombres
        CommonOutlinedInput(
            value = name,
            title = "Nombres",
            icon = Icons.Default.Person,
            isPassword = false,
            onValueChange = onNameChange
        )

        // Campo de apellidos
        CommonOutlinedInput(
            value = lastName,
            title = "Apellidos",
            icon = Icons.Default.Person,
            isPassword = false,
            onValueChange = onLastNameChange
        )

        // Campo de documento identidad
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
        // Campo de email
        CommonOutlinedInput(
            value = email,
            title = "Correo Electrónico",
            icon = Icons.Default.AlternateEmail,
            isPassword = false,
            onValueChange = onEmailChange
        )
        // Campo de password
        CommonOutlinedInput(
            value = password,
            title = "Contraseña",
            isPassword = true,
            isHide = isHide,
            onValueChange = onPasswordChange,
            onIconPressed = onVisibility
        )
        // Campo de Numero de Teléfono
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
