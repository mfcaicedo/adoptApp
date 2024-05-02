package co.edu.unicauca.adoptapp.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unicauca.adoptapp.data.user.UserDao
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(private val dao: UserDao) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email)
    }

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