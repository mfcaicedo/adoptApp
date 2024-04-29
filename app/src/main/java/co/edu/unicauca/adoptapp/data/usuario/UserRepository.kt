package co.edu.unicauca.adoptapp.data.usuario

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAllUsersSream(): Flow<List<User>>

    fun getUserStream(id: Int): Flow<User>

    suspend fun insertUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)
}