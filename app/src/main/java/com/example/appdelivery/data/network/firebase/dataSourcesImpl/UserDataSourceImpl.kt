package com.example.appdelivery.data.network.firebase.dataSourcesImpl

import com.example.appdelivery.data.models.UserModel
import com.example.appdelivery.data.network.firebase.dataSources.UserDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Implementación de [UserDataSource] que interactúa con Firebase Firestore
 * para realizar operaciones CRUD sobre la colección "users".
 *
 * @property firebaseStore instancia de [FirebaseFirestore] inyectada con Hilt.
 */
class UserDataSourceImpl @Inject constructor(
    private val firebaseStore: FirebaseFirestore
) : UserDataSource {

    // Referencia a la colección "users" en Firestore
    private val userCollection = firebaseStore.collection("users")

    /**
     * Crea un usuario en la colección "users".
     *
     * @param user objeto [UserModel] a guardar.
     * @return [Result] con éxito si se guardó correctamente o fallo con excepción.
     */
    override suspend fun createUser(user: UserModel): Result<Unit> {
        return try {
            userCollection
                .document(user.uid)
                .set(user)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Actualiza un usuario existente en Firestore.
     *
     * @param user objeto [UserModel] con los datos a actualizar.
     * @return [Result] con éxito si se actualizó correctamente o fallo con excepción.
     */
    override suspend fun updateUser(user: UserModel): Result<Unit> {
        return try {
            userCollection
                .document(user.uid)
                .update(user.toMap())
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Elimina un usuario por su UID.
     *
     * @param uid identificador único del usuario.
     * @return [Result] con éxito si se eliminó correctamente o fallo con excepción.
     */
    override suspend fun deleteUser(uid: String): Result<Unit> {
        return try {
            userCollection
                .document(uid)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtiene un usuario por su UID.
     *
     * @param uid identificador único del usuario.
     * @return [Result] con un [UserModel] si existe o null si no se encontró.
     */
    override suspend fun getUser(uid: String): Result<UserModel?> {
        return try {
            val snapshot = userCollection
                .document(uid)
                .get()
                .await()

            val user = snapshot.data?.let { UserModel.fromMap(it) }
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
