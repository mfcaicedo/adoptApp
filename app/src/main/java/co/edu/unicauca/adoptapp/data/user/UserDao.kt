package co.edu.unicauca.adoptapp.data.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * from user ORDER BY name ASC")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * from user WHERE userId = :id")
    fun getUser(id: Int): Flow<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Upsert
    suspend fun upsert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)



}