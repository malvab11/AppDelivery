package com.example.appdelivery.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdelivery.domain.entities.user.UserEntity
import com.example.appdelivery.domain.usecases.auth.RegisterWithEmailUseCase
import com.example.appdelivery.domain.usecases.user.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel encargado de manejar la lógica de la pantalla de registro de usuario.
 *
 * Funcionalidades principales:
 * - Gestionar y exponer el estado de la UI a través de [uiState].
 * - Validar los campos del formulario para habilitar o deshabilitar el botón de registro.
 * - Alternar visibilidad de contraseña.
 * - Marcar o desmarcar aceptación de términos y condiciones.
 * - Realizar el registro en Firebase Auth y guardar el usuario en la base de datos.
 *
 * @property registerWithEmailUseCase Caso de uso para registrar un usuario con email y contraseña en Firebase Auth.
 * @property createUserUseCase Caso de uso para guardar la información del usuario en la base de datos.
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerWithEmailUseCase: RegisterWithEmailUseCase,
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    /** Estado interno mutable de la pantalla de registro. */
    private val _uiState = MutableStateFlow(RegisterScreenModel())

    /** Estado público inmutable para observar desde la UI. */
    val uiState = _uiState.asStateFlow()

    /**
     * Actualiza un campo del estado usando la función [field] y vuelve a validar
     * para habilitar o deshabilitar el botón de registro.
     *
     * @param field Función que recibe el estado actual y devuelve el nuevo estado modificado.
     */
    fun updateField(field: (RegisterScreenModel) -> RegisterScreenModel) {
        val updatedState = field(_uiState.value)
        _uiState.value = updatedState.copy(
            isEnabled = validate(updatedState)
        )
    }

    /**
     * Registra un usuario en Firebase Auth y luego crea su registro en la base de datos.
     * - Muestra un indicador de carga mientras se realiza la operación.
     * - Actualiza el mensaje de la UI en función del resultado.
     */
    fun registerUser() {
        viewModelScope.launch {
            // Inicia carga
            _uiState.value = _uiState.value.copy(isLoading = true)
            val state = _uiState.value

            // Registro en Firebase Auth
            val response = registerWithEmailUseCase(state.email, state.password)
            if (response.isSuccess) {
                val firebaseUser = response.getOrNull()

                // Crea el objeto usuario para la base de datos
                val userEntity = UserEntity(
                    uid = firebaseUser!!.uid,
                    nombre = state.name,
                    apellidos = state.lastName,
                    correo = state.email,
                    celular = state.cellphone,
                    documentoIdentidad = state.documentId
                )

                // Guarda usuario en base de datos
                val createResponse = createUserUseCase(userEntity)
                _uiState.value = _uiState.value.copy(
                    message = if (createResponse.isSuccess) "Bienvenido ${state.name}"
                    else createResponse.exceptionOrNull()?.message ?: "Error al guardar usuario",
                    isError = createResponse.isFailure
                )
            } else {
                // Error en el registro de Firebase
                _uiState.value = _uiState.value.copy(
                    message = response.exceptionOrNull()?.message ?: "Error en el registro",
                    isError = true
                )
            }

            // Finaliza carga
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    /**
     * Alterna la visibilidad de la contraseña.
     */
    fun togglePasswordVisibility() {
        updateField { it.copy(isHide = !it.isHide) }
    }

    /**
     * Alterna el estado de aceptación de términos y condiciones.
     */
    fun toggleTermsCheck() {
        updateField { it.copy(isChecked = !it.isChecked) }
    }

    /**
     * Limpia los valores del formulario, restableciendo el estado inicial.
     */
    fun clearValues() {
        _uiState.value = RegisterScreenModel.init()
    }

    /**
     * Valida los campos del formulario para habilitar el botón de registro.
     *
     * Reglas:
     * - Email válido (contiene "@")
     * - Contraseña con mínimo 6 caracteres
     * - Nombre y apellido no vacíos
     * - Documento de identidad con mínimo 8 dígitos
     * - Celular con exactamente 9 dígitos
     * - Aceptar términos y condiciones
     *
     * @param state Estado actual del formulario.
     * @return `true` si todos los campos cumplen las condiciones, `false` en caso contrario.
     */
    private fun validate(state: RegisterScreenModel): Boolean {
        return state.email.contains("@") &&
                state.password.length >= 6 &&
                state.name.isNotEmpty() &&
                state.lastName.isNotEmpty() &&
                state.documentId.length >= 8 &&
                state.cellphone.length == 9 &&
                state.isChecked
    }
}
