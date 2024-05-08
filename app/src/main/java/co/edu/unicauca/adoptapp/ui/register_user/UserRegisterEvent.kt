package co.edu.unicauca.adoptapp.ui.register_user

sealed interface UserRegisterEvent {

    data class SetUserId(val userId: Int): UserRegisterEvent
    data class SetName(val name: String): UserRegisterEvent
    data class SetEmail(val email: String): UserRegisterEvent
    data class SetPassword(val password: String): UserRegisterEvent
    data class SetPhone(val phone: String): UserRegisterEvent
    data class SetAddress(val address: String): UserRegisterEvent
    object Register: UserRegisterEvent
    object Login: UserRegisterEvent
    object isLoading: UserRegisterEvent
    object Logout: UserRegisterEvent
}
