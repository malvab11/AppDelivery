package com.example.appdelivery.data.mappers

import com.example.appdelivery.data.models.UserModel
import com.example.appdelivery.domain.entities.user.UserEntity

class UserMapper {
    companion object {
        fun toEntity(user: UserModel): UserEntity {
            return UserEntity(
                uid = user.uid,
                nombre = user.nombre,
                apellidos = user.apellidos,
                correo = user.correo,
                celular = user.celular,
                documentoIdentidad = user.documentoIdentidad,
                rol = user.rol,
                fechaRegistro = user.fechaRegistro,
                esAnonimo = user.esAnonimo
            )
        }

        fun toModel(user: UserEntity): UserModel {
            return UserModel(
                uid = user.uid,
                nombre = user.nombre,
                apellidos = user.apellidos,
                correo = user.correo,
                celular = user.celular,
                documentoIdentidad = user.documentoIdentidad,
                rol = user.rol,
                fechaRegistro = user.fechaRegistro,
                esAnonimo = user.esAnonimo
            )
        }
    }
}