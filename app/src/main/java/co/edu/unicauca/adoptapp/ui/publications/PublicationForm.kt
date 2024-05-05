package co.edu.unicauca.adoptapp.ui.publications

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
import androidx.navigation.NavController
import co.edu.unicauca.adoptapp.R
import co.edu.unicauca.adoptapp.ui.register_user.UserRegisterEvent
import co.edu.unicauca.adoptapp.ui.register_user.UserState
import co.edu.unicauca.adoptapp.ui.theme.primaryDark
import co.edu.unicauca.adoptapp.ui.theme.primaryLight
import co.edu.unicauca.adoptapp.ui.theme.secondaryDark
import co.edu.unicauca.adoptapp.ui.theme.secondaryLight

import kotlinx.coroutines.launch

@Composable
fun PublicationForm(
    userId: String?,
    state: PostState,
    onEvent: (PostEvent) -> Unit,
    navigationController: NavController
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        PublicationForm(Modifier.align(Alignment.Center),userId, state, onEvent, navigationController)
    }
}

@Composable
fun PublicationForm(
    modifier: Modifier,
    userId: String?,
    state: PostState,
    onEvent: (PostEvent) -> Unit,
    navigationController: NavController
) {
    //seteo el id del usuario
    onEvent(PostEvent.SetPostUserId(Integer.parseInt(userId) ?: 0))
    Column(modifier = modifier) {
        Title(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        HeaderImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(10.dp))
        //Fields
       // TitleField(state, onEvent)
        PetNameField(state, onEvent)
        Spacer(modifier = Modifier.padding(10.dp))
        PetAgeField(state, onEvent)
        Spacer(modifier = Modifier.padding(10.dp))
        PetBreedField(state, onEvent)
        Spacer(modifier = Modifier.padding(10.dp))
        PetDescriptionField(state, onEvent)
        Spacer(modifier = Modifier.padding(10.dp))
        PetColorField(state, onEvent)
        Spacer(modifier = Modifier.padding(10.dp))
        PetSexField(state, onEvent)
        Spacer(modifier = Modifier.padding(10.dp))
        ImageButton(state, onEvent)
        Spacer(modifier = Modifier.padding(10.dp))
        PublicationButton(true) {
            onEvent(PostEvent.Register)
            //navigationController.navigate("home")
        }
    }
}

@Composable
fun ImageButton(state: PostState, onEvent: (PostEvent) -> Unit) {
    Button(
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = secondaryLight,
            disabledBackgroundColor = secondaryDark,
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
    ) {
        Text(text = "Seleccionar Imagen")
    }
}

@Composable
fun PublicationButton(loginEnable: Boolean, onEventRegister: () -> Unit) {
    Button(
        onClick = { onEventRegister() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = primaryLight,
            disabledBackgroundColor  = primaryDark,
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Publicar")
    }
}
@Composable
fun TitleField(state: PostState, onEvent: (PostEvent) -> Unit) {
    TextField(
        value = state.title, onValueChange = { onEvent(PostEvent.SetTitle(it)) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Título") },
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
fun PetAgeField(state: PostState, onEvent: (PostEvent) -> Unit) {
    TextField(
        value = state.petAge, onValueChange = { onEvent(PostEvent.SetPetAge(it)) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Edad mascota") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
fun PetSexField(state: PostState, onEvent: (PostEvent) -> Unit) {
    TextField(
        value = state.petSex, onValueChange = { onEvent(PostEvent.SetPetSex(it)) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Sexo de la mascota") },
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
fun PetNameField(state: PostState, onEvent: (PostEvent) -> Unit) {
    TextField(
        value = state.petName, onValueChange = { onEvent(PostEvent.SetPetName(it)) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Nombre mascota") },
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
fun PetDescriptionField (state: PostState, onEvent: (PostEvent) -> Unit) {
    TextField(
        value = state.petDescription, onValueChange = { onEvent(PostEvent.SetPetDescription(it)) },
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
fun PetBreedField(state: PostState, onEvent: (PostEvent) -> Unit) {
    TextField(
        value = state.petBreed, onValueChange = { onEvent(PostEvent.SetPetBreed(it)) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Raza de la mascota") },
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
fun PetColorField(state: PostState, onEvent: (PostEvent) -> Unit) {
    TextField(
        value = state.petColor, onValueChange = { onEvent(PostEvent.SetPetColor(it)) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Color de la mascota") },
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