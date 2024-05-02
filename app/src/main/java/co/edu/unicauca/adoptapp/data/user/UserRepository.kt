package co.edu.unicauca.adoptapp.data.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAllUsersStream(): Flow<List<User>>

    fun getUserStream(id: Int): Flow<User>

    suspend fun insertUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)
}