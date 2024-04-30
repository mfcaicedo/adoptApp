package co.edu.unicauca.adoptapp.data.user
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "favorite_post")
data class FavoritePost(
    @PrimaryKey(autoGenerate = true)
    val favoritePostId: Int = 0,
    val idPost: Int,
    val idUser: Int,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime,
)
