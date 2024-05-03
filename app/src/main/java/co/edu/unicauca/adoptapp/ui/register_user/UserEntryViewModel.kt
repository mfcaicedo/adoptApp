package co.edu.unicauca.adoptapp.ui.register_user
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unicauca.adoptapp.data.user.User
import co.edu.unicauca.adoptapp.data.user.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel to validate and insert users in the Room database.
 */
class UserEntryViewModel(private val dao: UserDao) : ViewModel() {

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
    /*
    fun onLoginChanged(email: String, password: String) {
        email = email
        password = password
        loginEnable.value = isValidEmail(email)
    }
     */

    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()


    suspend fun onLoginSelected() {
        _isLoading.value = true
        viewModelScope.launch {

            val user = dao.getUserByEmailAndPassword(email.value!!, password.value!!)
            if (user != null) {
                _loginSuccess.value = true
            }
            _isLoading.value = false
        }

        //delay(4000)
    }

}


