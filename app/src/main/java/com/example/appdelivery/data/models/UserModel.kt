package com.example.appdelivery.data.models

data class UserModel(
    val uid: String = "",
    val nombre: String = "",
    val apellidos: String = "",
    val correo: String = "",
    val celular: String = "",
    val documentoIdentidad: String = "",
    val rol: String = "cliente",
    val fechaRegistro: Long = System.currentTimeMillis(),
    val esAnonimo: Boolean = false
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "uid" to uid,
            "nombre" to nombre,
            "apellidos" to apellidos,
            "correo" to correo,
            "celular" to celular,
            "documentoIdentidad" to documentoIdentidad,
            "rol" to rol,
            "fechaRegistro" to fechaRegistro,
            "esAnonimo" to esAnonimo
        )
    }
    companion object{
        fun fromMap(data: Map<String,Any>): UserModel{
            return UserModel(
                uid = data["uid"] as? String ?: "",
                nombre = data["nombre"] as? String ?: "",
                apellidos = data["apellidos"] as? String ?: "",
                correo = data["correo"] as? String ?: "",
                celular = data["celular"] as? String ?: "",
                documentoIdentidad = data["documentoIdentidad"] as? String ?: "",
                rol = data["rol"] as? String ?: "",
                fechaRegistro = data["fechaRegistro"] as? Long ?: 0L,
                esAnonimo = data["esAnonimo"] as? Boolean ?: false

            )
        }
    }
}
