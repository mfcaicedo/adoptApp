package co.edu.unicauca.adoptapp.data.animal
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {


    @Query("SELECT * from animals ORDER BY name ASC")
    fun getAllAnimals(): Flow<List<Animal>>

    @Query("SELECT * from animals WHERE id = :id")
    fun getAnimal(id: Int): Flow<Animal>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(animal: Animal)

    @Update
    suspend fun update(animal: Animal)

    @Delete
    suspend fun delete(animal: Animal)
}