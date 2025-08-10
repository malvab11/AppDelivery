package com.example.appdelivery.presentation.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel encargado de manejar el estado y la lógica
 * para la pantalla de inicio de sesión (LoginScreen).
 *
 * Utiliza LiveData para exponer los valores observables a la UI
 * y asegura que la lógica de negocio esté desacoplada de la vista.
 *
 * Funcionalidades:
 * - Manejo del texto de email y contraseña.
 * - Validación del formulario para habilitar o deshabilitar el botón.
 * - Mostrar/ocultar la contraseña.
 * - Simulación de carga al iniciar sesión.
 */
@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    // ------------------------------
    // Campos de entrada
    // ------------------------------

    /** Email ingresado por el usuario */
    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    /** Contraseña ingresada por el usuario */
    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    // ------------------------------
    // Estado de la UI
    // ------------------------------

    /** Controla si la contraseña está oculta o visible */
    private val _isHide = MutableLiveData(true)
    val isHide: LiveData<Boolean> = _isHide

    /** Determina si el botón de login debe estar habilitado */
    private val _isEnabled = MutableLiveData(false)
    val isEnabled: LiveData<Boolean> = _isEnabled

    /** Indica si se está ejecutando un proceso de carga */
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    // ------------------------------
    // Eventos de la UI
    // ------------------------------

    /**
     * Alterna el estado de visibilidad de la contraseña.
     */
    fun onVisibilityClick() {
        _isHide.value = !(_isHide.value ?: true)
    }

    /**
     * Actualiza el valor del email y valida el formulario.
     * @param newEmail Nuevo email ingresado por el usuario.
     */
    fun onEmailChange(newEmail: String) {
        _email.value = newEmail.trim()
        updateButtonState()
    }

    /**
     * Actualiza el valor de la contraseña y valida el formulario.
     * @param newPassword Nueva contraseña ingresada por el usuario.
     */
    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        updateButtonState()
    }

    /**
     * Acción que se ejecuta al presionar el botón de login.
     * Simula una llamada a servidor con un retraso de 1.5 segundos.
     */
    fun onLoginPressed() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(1500) // Simulación de carga
            Log.d("Login", "Se logeó con éxito: ${_email.value}")
            _isLoading.value = false
        }
    }

    // ------------------------------
    // Lógica interna
    // ------------------------------

    /**
     * Valida el formulario y actualiza el estado del botón de login.
     * - Email debe contener "@".
     * - Contraseña debe tener mínimo 6 caracteres.
     */
    private fun updateButtonState() {
        _isEnabled.value = _email.value?.contains("@") == true &&
                (_password.value?.length ?: 0) >= 6
    }
}
