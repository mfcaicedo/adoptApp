package co.edu.unicauca.adoptapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.edu.unicauca.adoptapp.R
import co.edu.unicauca.adoptapp.ui.navigation.NavigationScreens
import co.edu.unicauca.adoptapp.ui.register_user.UserRegisterEvent
import co.edu.unicauca.adoptapp.ui.register_user.UserState
import co.edu.unicauca.adoptapp.ui.theme.primaryDark
import co.edu.unicauca.adoptapp.ui.theme.primaryLight
import co.edu.unicauca.adoptapp.ui.theme.secondaryLight
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    state: UserState,
    onEvent: (UserRegisterEvent) -> Unit,
    navigationController: NavController
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(Modifier.align(Alignment.Center),navigationController)
    }
}

@Composable
fun Login(modifier: Modifier, navigationController: NavController) {

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val loginSuccess: Boolean by viewModel.loginSuccess.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            Title(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(14.dp))
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPassword(Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(navigationController,loginEnable) {
                coroutineScope.launch {
                    viewModel.onLoginSelected()
                    if (loginSuccess) navigationController.navigate(NavigationScreens.Home.screen)
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Options(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(10.dp))
            InvitadoButton(navigationController) {
                coroutineScope.launch {
                    viewModel.onLoginSelected()
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            RegisterText(navigationController,Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun LoginButton(navigationController: NavController, loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = {
            onLoginSelected()
            //navigationController.navigate(NavigationScreens.Home.screen)
            },
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
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun InvitadoButton (navigationController: NavController, onClick: () -> Unit) {
    Button(
        onClick = { navigationController.navigate(NavigationScreens.Home.screen) },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = primaryLight,
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
    ) {
        Text(text = "Invitado")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Olvidaste la contraseña?",
        modifier = modifier.clickable { },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF83CBE1)

    )
}

@Composable
fun RegisterText(navigationController: NavController, modifier: Modifier) {
    Text(
        text = "Registrarse",
        modifier = modifier.clickable { navigationController.navigate(NavigationScreens.Register.screen) },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF83CBE1)
    )
}

@Composable
fun Options(modifier: Modifier) {
    Text(
        text = "O inicie con",
        modifier = modifier,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF83CBE1)
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