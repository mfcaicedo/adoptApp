package co.edu.unicauca.adoptapp.data.user

import kotlinx.coroutines.flow.Flow


class OfflineUsersRepository (private val userDao: UserDao): UserRepository {
    override fun getAllUsersSream(): Flow<List<User>> = userDao.getAllUsers()

    override fun getUserStream(id: Int): Flow<User> = userDao.getUser(id)

    override suspend fun insertUser(user: User) = userDao.insert(user)

    override suspend fun updateUser(user: User) = userDao.update(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)


}