package co.edu.unicauca.adoptapp.data.adoption_request

import kotlinx.coroutines.flow.Flow

class OfflineAdoptionRequestReposirory(private val adoptionRequestDao: AdoptionRequestDao): AdoptionRequestRepository {
    override fun getAllAdoptionRequestsStream(): Flow<List<AdoptionRequest>> = adoptionRequestDao.getAllAdoptionRequests()

    override fun getAdoptionRequestStream(id: Int): Flow<AdoptionRequest> = adoptionRequestDao.getAdoptionRequest(id)

    override suspend fun insertAdoptionRequest(adoptionRequest: AdoptionRequest) = adoptionRequestDao.insert(adoptionRequest)

    override suspend fun updateAdoptionRequest(adoptionRequest: AdoptionRequest) = adoptionRequestDao.update(adoptionRequest)

    override suspend fun deleteAdoptionRequest(adoptionRequest: AdoptionRequest) = adoptionRequestDao.delete(adoptionRequest)

}

