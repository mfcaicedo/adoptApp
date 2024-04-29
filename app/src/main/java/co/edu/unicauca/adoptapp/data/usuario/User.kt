package co.edu.unicauca.adoptapp.data.usuario
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val address: String,
    val city: String,
)
