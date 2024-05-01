package co.edu.unicauca.adoptapp.data.adoption_request

import kotlinx.coroutines.flow.Flow

interface AdoptionRequestRepository {

        fun getAllAdoptionRequestsStream(): Flow<List<AdoptionRequest>>

        fun getAdoptionRequestStream(id: Int): Flow<AdoptionRequest>

        suspend fun insertAdoptionRequest(adoptionRequest: AdoptionRequest)

        suspend fun updateAdoptionRequest(adoptionRequest: AdoptionRequest)

        suspend fun deleteAdoptionRequest(adoptionRequest: AdoptionRequest)
}