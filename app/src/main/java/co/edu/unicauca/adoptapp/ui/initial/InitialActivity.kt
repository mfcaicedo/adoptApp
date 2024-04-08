package co.edu.unicauca.adoptapp.ui.initial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.unicauca.adoptapp.R
import co.edu.unicauca.adoptapp.ui.login.LoginViewModel
import co.edu.unicauca.adoptapp.ui.theme.backgroundDark
import co.edu.unicauca.adoptapp.ui.theme.primaryLight
import kotlinx.coroutines.launch

@Composable
fun InitialActivity(viewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(Modifier.align(Alignment.Center), viewModel)
    }
}


@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
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

            Spacer(modifier = Modifier.padding(4.dp))

            Spacer(modifier = Modifier.padding(8.dp))

            Spacer(modifier = Modifier.padding(16.dp))
            InitialButton() {
                coroutineScope.launch {
                    viewModel.onLoginSelected()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // Crea un LoginViewModel simulado
    val mockViewModel = object : LoginViewModel() {
        // Sobrescribe las propiedades y funciones según sea necesario para la vista previa
    }

    // Usa el ViewModel simulado en LoginScreen
    InitialActivity(mockViewModel)
}


@Composable
fun InitialButton(onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            //backgroundColor = Color(0xFF16C2D8),
            primaryLight,
            //backgroundColor = Color(MaterialTheme.colors.primary),
            //disabledBackgroundColor = Color(0xFF73CBE6),
            //disabledBackgroundColor = Color(MaterialTheme.colors.primary),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), 
    ) {
        Text(text = "Continuar")
    }
}

fun Color(color: Color): Color {
    return color
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