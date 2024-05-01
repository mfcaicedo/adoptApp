package co.edu.unicauca.adoptapp.data.animal
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animal")
data class Animal (
    @PrimaryKey(autoGenerate = true)
    val animalId: Int = 0,
    val name: String,
    val age: Int,
    val description: String,
)