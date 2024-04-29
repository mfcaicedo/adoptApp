package co.edu.unicauca.adoptapp.data.animal

import kotlinx.coroutines.flow.Flow
class OfflineAnimalsRepository(private val animalDao: AnimalDao): AnimalsRepository {

    override fun getAllAnimalsStream(): Flow<List<Animal>> = animalDao.getAllAnimals()

    override fun getAnimalStream(id: Int): Flow<Animal> = animalDao.getAnimal(id)

    override suspend fun insertAnimal(animal: Animal) = animalDao.insert(animal)

    override suspend fun updateAnimal(animal: Animal) = animalDao.update(animal)

    override suspend fun deleteAnimal(animal: Animal) = animalDao.delete(animal)

}