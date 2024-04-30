package co.edu.unicauca.adoptapp.data.post
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * from post ORDER BY title ASC")
    fun getAllPosts(): Flow<List<Post>>

    @Query("SELECT * from post WHERE postId = :id")
    fun getPost(id: Int): Flow<Post>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post: Post)

    @Update
    suspend fun update(post: Post)

    @Delete
    suspend fun delete(post: Post)




}