package co.edu.unicauca.adoptapp.data.adoption_request
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import co.edu.unicauca.adoptapp.data.user.User
import java.time.LocalDateTime

@Entity(tableName = "adoption_request", foreignKeys = [ForeignKey(entity = User::class,
    parentColumns = ["userId"],
    childColumns = ["userId"],
    onDelete = ForeignKey.CASCADE), ForeignKey(entity = AdoptionRequest::class,
    parentColumns = ["postId"],
    childColumns = ["postId"],
    onDelete = ForeignKey.CASCADE)]
        )
data class AdoptionRequest(
    @PrimaryKey(autoGenerate = true)
    val adoptionRequestId: Int = 0,
    val date: String,
    val description: String,
)
