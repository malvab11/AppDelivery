package com.example.appdelivery.data.models

data class MenuModel(
    val id: String = "",
    val nombre: String = "",
    val descripcion: String = "",
    val precio: Double = 0.0,
    val imagenUrl: String = "",
    val ingredientes: List<IngredienteUsado> = emptyList(),
    val activo: Boolean = true
)

data class IngredienteUsado (
    val ingredienteId: String,
    val cantidad: Double
)
