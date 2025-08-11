package com.example.appdelivery.presentation.screens.register

data class RegisterScreenModel(
    val name: String = "",
    val lastName: String = "",
    val documentId: String = "",
    val email: String = "",
    val password: String = "",
    val cellphone: String = "",
    val message: String? = null,
    val isHide: Boolean = true,
    val isChecked: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val isEnabled: Boolean = false
){
    companion object {
        fun init() = RegisterScreenModel()
    }
}