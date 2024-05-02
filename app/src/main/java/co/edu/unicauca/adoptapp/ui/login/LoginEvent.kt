package co.edu.unicauca.adoptapp.ui.login


interface LoginEvent {

    data class SetEmail(val email: String): LoginEvent
    data class SetPassword(val password: String): LoginEvent
    object Login: LoginEvent

}