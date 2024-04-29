package co.edu.unicauca.adoptapp.data.animal

import kotlinx.coroutines.flow.Flow


interface AnimalsRepository {

    fun getAllAnimalsStream(): Flow<List<Animal>>

    fun getAnimalStream(id: Int): Flow<Animal>

    suspend fun insertAnimal(animal: Animal)

    suspend fun updateAnimal(animal: Animal)

    suspend fun deleteAnimal(animal: Animal)

}