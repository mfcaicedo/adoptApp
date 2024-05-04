package co.edu.unicauca.adoptapp.ui.register_user

data class UserState (
    val userId: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val phone: String = "",
    val address: String = "",
    val registerEnable: Boolean = false,
    val isLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
)