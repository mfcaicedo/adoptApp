package co.edu.unicauca.adoptapp.data.post
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import co.edu.unicauca.adoptapp.data.user.User
import java.time.LocalDateTime
import java.util.Date


@Entity(tableName = "post", foreignKeys = [ForeignKey(
    entity = User::class,
    parentColumns = ["userId"],  // Cambiar a "userId"
    childColumns = ["postUserId"],
    onDelete = ForeignKey.CASCADE
)],
    indices = [androidx.room.Index("postUserId")]
)
data class Post(
    @PrimaryKey(autoGenerate = true)
    val postId: Int = 0,
    val title: String,
    val date: String,
    val petName: String,
    val petAge: Int,
    val petBreed: String,
    val petDescription: String,
    val petColor: String,
    val petSex: String,
    val imageId: String,
    val postUserId: Int, //llave foranea
)


