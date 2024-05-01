package co.edu.unicauca.adoptapp.ui.register_user
/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import co.edu.unicauca.adoptapp.data.user.User
import co.edu.unicauca.adoptapp.data.user.UserRepository

/**
 * ViewModel to validate and insert users in the Room database.
 */
class UserEntryViewModel(private val userRepository: UserRepository) : ViewModel() {

    /**
     * Holds current user ui state
     */
    var userUiState by mutableStateOf(UserUiState())
        private set

    /**
     * Updates the [itemUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(userDetails: UserDetails) {
        userUiState =
            UserUiState(userDetails = userDetails, isEntryValid = validateInput(userDetails))
    }

    /**
     * Inserts an [Item] in the Room database
     */
    suspend fun saveUser() {
        println("save:")
        println("saveUser ${userUiState.userDetails}")
        if (validateInput()) {
            userRepository.insertUser(userUiState.userDetails.toUser())
        }
    }

    private fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && password.isNotBlank() && email.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Item.
 */
data class UserUiState(
    val userDetails: UserDetails = UserDetails(),
    val isEntryValid: Boolean = false
)

data class UserDetails(
    val userId: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val phone: String = "",
    val address: String = ""
)

fun UserDetails.toUser(): User = User(
    userId = userId,
    name = name,
    email = email,
    password = password,
    phone = phone,
    address = address
)

/**
 * Extension function to convert [Item] to [ItemUiState]
 */

fun User.toItemUiState(isEntryValid: Boolean = false): UserUiState = UserUiState(
    userDetails = this.toUserDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [ItemDetails]
 */
fun User.toUserDetails(): UserDetails = UserDetails(
    userId = userId,
    name = name,
    email = email,
    password = password,
    phone = phone,
    address = address
)
