package com.example.appdelivery.data.models

data class PedidosModel(
    val id: String = "",
    val clienteId: String = "",
    val sedeId: String = "",
    val sedeNombre: String = "",
    val items: List<PedidoItem> = emptyList(),
    val total: Double = 0.0,
    val estado: String = "", //Recepcionado | En Preparación | En Ruta | Pedido Listo para Recoger o En lugar de Recepcion
    val tipo: String = "", //En salon o Delivery
    val fecha: Long = System.currentTimeMillis()
)

data class PedidoItem(
    val menuItemId: String,
    val cantidad: Int
)
