package com.example.appdelivery.domain.entities.user

data class UserEntity(
    val uid: String = "",
    val nombre: String = "",
    val apellidos: String = "",
    val correo: String = "",
    val celular: String = "",
    val documentoIdentidad: String = "",
    val rol: String = "cliente",
    val fechaRegistro: Long = System.currentTimeMillis(),
    val esAnonimo: Boolean = false
)

