package co.edu.unicauca.adoptapp.data.adoption_request

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface AdoptionRequestDao {

    @Query("SELECT * FROM adoption_request ORDER BY adoptionRequestId ASC")
    fun getAllAdoptionRequests(): Flow<List<AdoptionRequest>>

    @Query("SELECT * FROM adoption_request WHERE adoptionRequestId = :id")
    fun getAdoptionRequest(id: Int): Flow<AdoptionRequest>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(adoptionRequest: AdoptionRequest)

    @Update
    suspend fun update(adoptionRequest: AdoptionRequest)

    @Delete
    suspend fun delete(adoptionRequest: AdoptionRequest)





}