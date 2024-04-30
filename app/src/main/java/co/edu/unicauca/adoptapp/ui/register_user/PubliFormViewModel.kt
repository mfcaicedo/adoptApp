package co.edu.unicauca.adoptapp.ui.register_user

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay



open class PubliFormViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _typeAnimal = MutableLiveData<String>()
    val typeAnimal : LiveData<String> = _typeAnimal

    private val _raza = MutableLiveData<String>()
    val raza: LiveData<String> = _raza

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description


    private val _number = MutableLiveData<String>()
    val number: LiveData<String> = _number

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChanged(typeAnimal: String, raza:String, description:String , name: String) {
        _typeAnimal.value = typeAnimal
        _raza.value = raza
        _description.value = description
        _name.value = name
        //_loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

}