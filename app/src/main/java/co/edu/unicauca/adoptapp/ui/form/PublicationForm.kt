package co.edu.unicauca.adoptapp.ui.form

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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.unicauca.adoptapp.R
import co.edu.unicauca.adoptapp.ui.register.RegisterViewModel

import kotlinx.coroutines.launch

@Composable
fun PublicationForm(viewModel: PubliFormViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        PublicationForm(Modifier.align(Alignment.Center), viewModel)
    }
}


@Composable
fun PublicationForm(modifier: Modifier, viewModel: PubliFormViewModel) {
    val name: String by viewModel.name.observeAsState(initial = "")
    val typeAnimal : String by viewModel.typeAnimal.observeAsState(initial = "")
    val raza : String by viewModel.raza.observeAsState(initial = "")
    val description : String by viewModel.description.observeAsState(initial = "")
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
            Spacer(modifier = Modifier.padding(10.dp))
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(10.dp))
            NameField(name) { viewModel.onLoginChanged(typeAnimal, raza, description, it) }
            Spacer(modifier = Modifier.padding(10.dp))
            TypeField(typeAnimal) { viewModel.onLoginChanged(it, raza, description, name) }
            Spacer(modifier = Modifier.padding(10.dp))
            RazaField(raza) { viewModel.onLoginChanged(typeAnimal, it, description, name) }
            Spacer(modifier = Modifier.padding(10.dp))
            DescriptionField(description) { viewModel.onLoginChanged(typeAnimal, raza, it, name) }
            Spacer(modifier = Modifier.padding(10.dp))
            ImageButton {
                coroutineScope.launch {
                    viewModel.onLoginSelected()
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            PublicationButton(loginEnable) {
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
    val mockViewModel = object : PubliFormViewModel() {
        // Sobrescribe las propiedades y funciones según sea necesario para la vista previa
    }

    // Usa el ViewModel simulado
    PublicationForm(mockViewModel)
}

@Composable
fun ImageButton(onLoginSelected: () -> Unit){
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF16C2D8),
            //backgroundColor = Color(MaterialTheme.colors.primary),
            disabledBackgroundColor = Color(0xFF73CBE6),
            //disabledBackgroundColor = Color(MaterialTheme.colors.primary),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
    ) {
        Text(text = "Seleccionar Imagen")
    }
}


@Composable
fun PublicationButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF16C2D8),
            //backgroundColor = Color(MaterialTheme.colors.primary),
            disabledBackgroundColor = Color(0xFF73CBE6),
            //disabledBackgroundColor = Color(MaterialTheme.colors.primary),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Publicar")
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
fun DescriptionField (description: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = description, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Descripción") },
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
fun RazaField(raza: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = raza, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Raza") },
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
fun TypeField(typeAnimal: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = typeAnimal, onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Tipo de animal") },
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
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_adoptapp_final),
        contentDescription = "Header",
        modifier = modifier
    )
}

@Composable
fun Title(modifier: Modifier) {
    Text(
        text = "AdoptApp",
        color = Color(0xFF636262),
        modifier = modifier
    )
}