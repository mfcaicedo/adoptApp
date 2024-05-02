package co.edu.unicauca.adoptapp.ui.register_user
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import co.edu.unicauca.adoptapp.data.user.User
import co.edu.unicauca.adoptapp.data.user.UserDao
import co.edu.unicauca.adoptapp.data.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel to validate and insert users in the Room database.
 */
class UserEntryViewModel(private val dao: UserDao) : ViewModel() {

    //var state by mutableStateOf(UserState())
    private val _state = MutableStateFlow(UserState())
    val state: StateFlow<UserState> = _state
    fun onEvent(event: UserRegisterEvent) {
        when (event) {
            is UserRegisterEvent.Register -> {
                val name = state.value.name
                val email = state.value.email
                val password = state.value.password
                val phone = state.value.phone
                val address = state.value.address
                val registerEnable = state.value.registerEnable

                if (name.isBlank() || email.isBlank() || password.isBlank()) {
                    return
                }
                val user = User(
                    name = name,
                    email = email,
                    password = password,
                    phone = phone ?: "",
                    address = address ?: ""
                )
                viewModelScope.launch {
                    dao.insert(user)
                }
                //Reset the state
                _state.update { it.copy(
                    name = "",
                    email = "",
                    password = "",
                    phone = "",
                    address = "",
                    registerEnable = false
                ) }
            }
            is UserRegisterEvent.SetName -> {
                _state.update { it.copy(
                    name = event.name
                ) }
            }
            is UserRegisterEvent.SetEmail -> {
                _state.update { it.copy(
                    email = event.email
                ) }
            }
            is UserRegisterEvent.SetPassword -> {
                _state.update { it.copy(
                    password = event.password
                ) }
            }
            is UserRegisterEvent.SetPhone -> {
                _state.update { it.copy(
                    phone = event.phone
                ) }
            }
            is UserRegisterEvent.SetAddress -> {
                _state.update { it.copy(
                    address = event.address
                ) }
            }

        }
    }
}

