package co.edu.unicauca.adoptapp.data.user
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val address: String,
    val city: String,

)
