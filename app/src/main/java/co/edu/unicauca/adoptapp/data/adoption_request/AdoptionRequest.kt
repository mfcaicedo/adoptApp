package co.edu.unicauca.adoptapp.data.adoption_request
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import co.edu.unicauca.adoptapp.data.post.Post
import co.edu.unicauca.adoptapp.data.user.User

@Entity(tableName = "adoption_request", foreignKeys = [ForeignKey(entity = User::class,
    parentColumns = ["userId"],
    childColumns = ["adoptionUserId"],
    onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Post::class,
    parentColumns = ["postId"],
    childColumns = ["adoptionPostId"],
    onDelete = ForeignKey.CASCADE)],
    indices = [androidx.room.Index("adoptionUserId"), androidx.room.Index("adoptionPostId")]
    )
data class AdoptionRequest(
    @PrimaryKey(autoGenerate = true)
    val adoptionRequestId: Int = 0,
    val date: String,
    val description: String,
    val adoptionUserId: Int,
    val adoptionPostId: Int
    )

