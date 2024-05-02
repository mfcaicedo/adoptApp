package co.edu.unicauca.adoptapp.ui.login

data class LoginState (
    val email: String = "",
    val password: String = "",
    val loginEnable: Boolean = false,
    val isLoading: Boolean = false,
    val loginSuccess: Boolean = false
)