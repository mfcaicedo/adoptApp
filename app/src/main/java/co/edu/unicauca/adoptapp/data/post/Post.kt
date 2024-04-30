package co.edu.unicauca.adoptapp.data.post
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import co.edu.unicauca.adoptapp.data.user.User
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "post",foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE)])
data class Post(
    @PrimaryKey(autoGenerate = true)
    val postId: Int = 0,
    val title: String,
    val description: String,
    val date: LocalDateTime,
)


