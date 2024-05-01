package co.edu.unicauca.adoptapp.ui.register_user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.edu.unicauca.adoptapp.R
import co.edu.unicauca.adoptapp.ui.AppViewModelProvider
import kotlinx.coroutines.launch



@Composable
fun RegisterActivity(
    viewModel: UserEntryViewModel = viewModel(factory = AppViewModelProvider.Factory),
                     //viewModel: ItemEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
                     ) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Register(Modifier.align(Alignment.Center), viewModel, userDetails = viewModel.userUiState.userDetails)
    }
}


@Composable
fun Register(modifier: Modifier, viewModel: UserEntryViewModel, userDetails: UserDetails ) {

    val email = userDetails.email
    //val password: String by viewModel.password.observeAsState(initial = "")
    val password = userDetails.password
    val name = userDetails.name
    val number = userDetails.phone
    val address = userDetails.address
    //val registerEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)


    //val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    val coroutineScope = rememberCoroutineScope()


    //if (isLoading) {
        //Box(Modifier.fillMaxSize()) {
        //    CircularProgressIndicator(Modifier.align(Alignment.Center))
      //  }
    //} else {
        Column(modifier = modifier) {
            Title(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(10.dp))
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(10.dp))

            //NameField(name) { viewModel.onLoginChanged(email, password, it, number, address) }
            NameField(viewModel.userUiState.userDetails.name) {}
            Spacer(modifier = Modifier.padding(10.dp))
            NumberPhone(viewModel.userUiState.userDetails.phone) {}
            Spacer(modifier = Modifier.padding(10.dp))
            Address(viewModel.userUiState.userDetails.address) { }
            Spacer(modifier = Modifier.padding(10.dp))
            EmailField(viewModel.userUiState.userDetails.email) { }
            Spacer(modifier = Modifier.padding(10.dp))
            PasswordField(viewModel.userUiState.userDetails.password) { }
            Spacer(modifier = Modifier.padding(10.dp))
            RegisterButton(true) {
                coroutineScope.launch {
                    //viewModel.onRegisterSelected()
                    viewModel.saveUser()
                }
            }
        //}
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // Crea un LoginViewModel simulado
    val mockViewModel = object : RegisterViewModel() {
        // Sobrescribe las propiedades y funciones según sea necesario para la vista previa
    }

    // Usa el ViewModel simulado en LoginScreen
    //RegisterActivity(mockViewModel)
}


@Composable
fun RegisterButton(loginEnable: Boolean, onRegisterSelected: () -> Unit) {
    Button(
        onClick = { onRegisterSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            //backgroundColor = Color(0xFF16C2D8),
            //backgroundColor = Color(MaterialTheme.colors.primary),
            //disabledBackgroundColor = Color(0xFF73CBE6),
            //disabledBackgroundColor = Color(MaterialTheme.colors.primary),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Registrarse")
    }
}


fun Color(color: Color): Color {
    return color
}

@Composable
fun NameField(Name: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = Name, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Nombre") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun NumberPhone(password: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = password, onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Telefono") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun Address(password: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = password, onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Direccion") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = password, onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}


@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_adoptapp_final),
        contentDescription = "Header",
        modifier = modifier
    )
}

@Composable
fun Title(modifier: Modifier) {

}