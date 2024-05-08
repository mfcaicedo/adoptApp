package co.edu.unicauca.adoptapp.ui.adoptions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unicauca.adoptapp.data.adoption_request.AdoptionRequest
import co.edu.unicauca.adoptapp.data.adoption_request.AdoptionRequestDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdoptionFormViewModel (private val dao: AdoptionRequestDao) : ViewModel(){
    private val _state = MutableStateFlow(AdoptionState())
    val state: StateFlow<AdoptionState> = _state

    fun onEvent(event: AdoptionEvent){
        when(event){
            is AdoptionEvent.Register -> {
                println("Register essssss ")
                val date = state.value.date
                val description = state.value.description
                val postUserId = state.value.adoptionUserId
                val postAdoptId = state.value.adoptionPostId

                if (postUserId == 0 || postAdoptId == 0){
                    return
                }
                val adoptionRequest = AdoptionRequest(
                    date = date,
                    description = description,
                    adoptionUserId = postUserId,
                    adoptionPostId = postAdoptId
                )
                viewModelScope.launch {
                    dao.insert(adoptionRequest)
                }
                //Reset the state
                _state.update {
                    it.copy(
                        date = "",
                        description = "",
                        adoptionUserId = 0,
                        adoptionPostId = 0
                    )
                }
            }

            is AdoptionEvent.SetDate -> {
                _state.update {
                    it.copy(date = event.date)
                }
            }

            is AdoptionEvent.SetDescription -> {
                _state.update {
                    it.copy(description = event.description)
                }
            }

            is AdoptionEvent.SetPostUserId -> {
                _state.update {
                    it.copy(adoptionUserId = event.adoptionUserId)
                }
            }

            is AdoptionEvent.SetAdoptionPostId -> {
                _state.update {
                    it.copy(adoptionPostId = event.adoptionPostId)
                }
            }
            else -> {}

        }
    }


}