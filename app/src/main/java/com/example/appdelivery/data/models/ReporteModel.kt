package com.example.appdelivery.data.models

data class ReporteModel(
    val fecha: String,
    val ventasTotales: Double,
    val pedidosSala: Int,
    val pedidosDelivery: Int,
    val ganancias: Double
)