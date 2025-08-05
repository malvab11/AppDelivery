package com.example.appdelivery.data.models

import java.time.LocalDate

data class UserModel(
    val id: String = "",
    val nombre: String = "",
    val correo: String = "",
    val rol : String = "Cliente",
    val sedeId: String = "",
    val sedeNombre: String = "",
    val fechaRegistro: Long = System.currentTimeMillis(),
    val esAnonimo: Boolean = false
)
