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
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.isPopupLayout
import androidx.navigation.NavController
import co.edu.unicauca.adoptapp.R
import co.edu.unicauca.adoptapp.ui.navigation.NavigationScreens
import androidx.compose.runtime.*
import co.edu.unicauca.adoptapp.ui.theme.primaryDark
import co.edu.unicauca.adoptapp.ui.theme.primaryLight

@Composable
fun RegisterScreen(
    state: UserState,
    onEvent: (UserRegisterEvent) -> Unit,
    navigationController: NavController
    ) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Register(Modifier.align(Alignment.Center), state, onEvent, navigationController)
    }
}


@Composable
fun Register(
    modifier: Modifier, state: UserState,
    onEvent: (UserRegisterEvent) -> Unit,
    navigationController: NavController
) {

    var showDialog by remember { mutableStateOf(false) }
        Column(modifier = modifier) {
            Title(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(10.dp))
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(10.dp))
            //Form fields
            NameField(state, onEvent)
            Spacer(modifier = Modifier.padding(10.dp))
            NumberPhone(state, onEvent)
            Spacer(modifier = Modifier.padding(10.dp))
            Address(state, onEvent)
            Spacer(modifier = Modifier.padding(10.dp))
            EmailField(state, onEvent)
            Spacer(modifier = Modifier.padding(10.dp))
            PasswordField(state, onEvent)
            Spacer(modifier = Modifier.padding(10.dp))

            RegisterButton(true) {

                onEvent(UserRegisterEvent.Register)
                showDialog = true
                //Mostrar mensaje de registro exitoso
                //TODO: falta implementar
                //TODO: volver a la pantalla de login (FALTA)
                //navigationController.navigate(NavigationScreens.Login.screen)
                //navigationController.popBackStack()
            }
            if (showDialog) {
                SuccessDialog(
                    onDismissRequest = { showDialog = false },
                    onConfirm = {
                        showDialog = false
                        navigationController.navigate(NavigationScreens.Login.screen)
                    }
                )
            }
        }
}

@Composable
fun SuccessDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Registro exitoso!") },
        confirmButton = {
            Button(onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                backgroundColor = primaryLight,
                disabledBackgroundColor = primaryDark,
                contentColor = Color.White,
                disabledContentColor = Color.White
            )) {
                Text("OK")
            }
        }
    )
}

@Composable
fun RegisterButton(loginEnable: Boolean, onRegisterSelected: () -> Unit) {
    Button(
        onClick = { onRegisterSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = primaryLight,
            disabledBackgroundColor = primaryDark,
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Registrarse")
    }
}

@Composable
fun NameField(state: UserState, onEvent: (UserRegisterEvent) -> Unit) {
    TextField(
        value = state.name, onValueChange = { onEvent(UserRegisterEvent.SetName(it))},
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
fun NumberPhone(state: UserState, onEvent: (UserRegisterEvent) -> Unit) {
    TextField(
        value = state.phone, onValueChange = { onEvent(UserRegisterEvent.SetPhone(it)) },
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
fun Address(state: UserState, onEvent: (UserRegisterEvent) -> Unit) {
    TextField(
        value = state.address, onValueChange = { onEvent(UserRegisterEvent.SetAddress(it)) },
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
fun PasswordField(state: UserState, onEvent: (UserRegisterEvent) -> Unit) {
    TextField(
        value = state.password, onValueChange = { onEvent(UserRegisterEvent.SetPassword(it)) },
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
fun EmailField(state: UserState, onEvent: (UserRegisterEvent) -> Unit) {
    TextField(
        value = state.email, onValueChange = { onEvent(UserRegisterEvent.SetEmail(it)) },
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