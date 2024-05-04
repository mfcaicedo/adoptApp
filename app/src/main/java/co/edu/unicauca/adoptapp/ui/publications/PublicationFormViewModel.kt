package co.edu.unicauca.adoptapp.ui.publications

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unicauca.adoptapp.data.post.Post
import co.edu.unicauca.adoptapp.data.post.PostDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class PublicationFormViewModel (private val dao: PostDao) : ViewModel() {

    private val _state = MutableStateFlow(PostState())
    val state: StateFlow<PostState> = _state

    fun onEvent(event: PostEvent) {
        when (event) {
            is PostEvent.Register -> {
                val title = state.value.title
                val date = state.value.date
                val petName = state.value.petName
                val petAge = state.value.petAge
                val petBreed = state.value.petBreed
                val petDescription = state.value.petDescription
                val petColor = state.value.petColor
                val petSex = state.value.petSex
                val imageId = state.value.imageId
                val postUserId = state.value.postUserId


                if (title.isBlank() || petName.isBlank() || petColor.isBlank() || postUserId == 0) {
                    return
                }
                val post = Post(
                    title = title,
                    date = date,
                    petName = petName,
                    petAge = petAge,
                    petBreed = petBreed,
                    petDescription = petDescription,
                    petColor = petColor,
                    petSex = petSex,
                    imageId = imageId,
                    postUserId = postUserId
                )
                viewModelScope.launch {
                    dao.insert(post)
                }
                //Reset the state
                _state.update {
                    it.copy(
                        title = "",
                        date = "",
                        petName = "",
                        petAge = 0,
                        petBreed = "",
                        petDescription = "",
                        petColor = "",
                        petSex = "",
                        imageId = "",
                        postUserId = 0,
                        registerEnable = false
                    )
                }
            }
            is PostEvent.SetTitle -> {
                _state.update {
                    it.copy(
                        title = event.title
                    )
                }
            }
            is PostEvent.SetDate -> {
                _state.update {
                    it.copy(
                        date = event.date
                    )
                }
            }
            is PostEvent.SetPetName -> {
                _state.update {
                    it.copy(
                        petName = event.petName
                    )
                }
            }
            is PostEvent.SetPetAge -> {
                _state.update {
                    it.copy(
                        petAge = event.petAge
                    )
                }
            }
            is PostEvent.SetPetBreed -> {
                _state.update {
                    it.copy(
                        petBreed = event.petBreed
                    )
                }
            }
            is PostEvent.SetPetDescription -> {
                _state.update {
                    it.copy(
                        petDescription = event.petDescription
                    )
                }
            }
            is PostEvent.SetPetColor -> {
                _state.update {
                    it.copy(
                        petColor = event.petColor
                    )
                }
            }
            is PostEvent.SetPetSex -> {
                _state.update {
                    it.copy(
                        petSex = event.petSex
                    )
                }
            }
            is PostEvent.SetImageId -> {
                _state.update {
                    it.copy(
                        imageId = event.imageId
                    )
                }
            }
            is PostEvent.SetPostUserId -> {
                _state.update {
                    it.copy(
                        postUserId = event.postUserId
                    )
                }
            }
            else -> {}
        }
    }
    private fun validateFields(name: String, email: String, password: String) {
        // val nameError = if (name.isBlank()) "Name is required" else null
       // val emailError = if (email.isBlank() || !isValidEmail(email)) "Correo electrónico inválido" else null
       // val passwordError = if (password.isBlank() || !isValidPassword(password)) "Password must be at least 6 characters" else null

        _state.update {
            it.copy(
                //nameError = nameError,
         //       emailError = emailError,
          //      passwordError = passwordError
                // Agrega más campos de error según sea necesario para otros campos
            )
        }
    }

}