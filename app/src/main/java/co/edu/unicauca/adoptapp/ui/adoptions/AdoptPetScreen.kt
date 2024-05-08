package co.edu.unicauca.adoptapp.ui.adoptions

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.unicauca.adoptapp.R
import co.edu.unicauca.adoptapp.ui.index.IndexContent
import co.edu.unicauca.adoptapp.ui.navigation.NavigationScreens
import co.edu.unicauca.adoptapp.ui.posts.DetailPostContent
import co.edu.unicauca.adoptapp.ui.posts.DetailPostTopBar
import co.edu.unicauca.adoptapp.ui.publications.PostEvent
import co.edu.unicauca.adoptapp.ui.theme.primaryDark
import co.edu.unicauca.adoptapp.ui.theme.primaryLight

@Composable
fun AdoptPetScreen(
    navigationController: NavController,
    state: AdoptionState,
    onEvent: (AdoptionEvent) -> Unit,
    postId: String?, userId: String?) {
    Scaffold(
        topBar = {
            AdoptPetTopBar(navigationController = navigationController)
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            AdoptPetContent(navigationController = navigationController,state,onEvent, postId = postId, userId = userId)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdoptPetTopBar(navigationController: NavController) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            IconButton(onClick = {
                navigationController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.description_back_icon),
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier,
                ) {
                    Text(text = stringResource(R.string.adopt_pet_text))
                }
                Column(
                    modifier = Modifier
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_adoptapp_final),
                            contentDescription = stringResource(id = R.string.app_name),
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .width(28.dp)
                                .height(24.dp)
                        )
                    }
                }
            }
        },
    )
}


@Composable
fun AdoptPetContent(
    navigationController: NavController,
    state: AdoptionState,
    onEvent: (AdoptionEvent) -> Unit,
    postId: String?,
    userId: String?
) {
    var showDialog by remember { mutableStateOf(false) }
    val anyChecked = remember { mutableStateOf(false) }
    val conditionsChecked = remember { mutableStateOf(List(conditions.size) { false }) }
    onEvent(AdoptionEvent.SetPostUserId(userId?.toInt() ?: 0))
    onEvent(AdoptionEvent.SetAdoptionPostId(postId?.toInt() ?: 0))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .padding(start = 9.dp, end = 9.dp, top = 9.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.adoption_conditions_text),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.adoption_conditions_description_text),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            ConditionsList(conditions, conditionsChecked, anyChecked)
            Button(
                onClick = {
                    onEvent(AdoptionEvent.Register)
                    showDialog = true
                          },
                enabled = anyChecked.value
            ) {
                Text("Adoptar Mascota")
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
}

@Composable
fun SuccessDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(
            text = "Solicitud de adopción registrada!",
            color = Color.Black
        ) },
        confirmButton = {
            androidx.compose.material.Button(onClick = onConfirm,
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
fun ConditionsList(
    conditions: List<Map<String, Any>>,
    conditionsChecked: MutableState<List<Boolean>>,
    anyChecked: MutableState<Boolean>
) {
    for ((index, condition) in conditions.withIndex()) {
        val conditionText = condition["conditionText"] as String
        val checked = conditionsChecked.value[index]
        ConditionAdoptPet(
            conditionText = conditionText,
            checked = checked,
            onCheckedChange = { isChecked ->
                conditionsChecked.value = conditionsChecked.value.toMutableList().also { it[index] = isChecked }
                anyChecked.value = conditionsChecked.value.any { it }
            }
        )
    }
}

@Composable
fun ConditionAdoptPet(
    conditionText: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(text = conditionText, style = MaterialTheme.typography.bodyMedium)
    }
}


@Composable
fun CheckBox(
    checked: Boolean = false,
) {
    val checkedState = remember { mutableStateOf(checked) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}

//Llegaría por base de datos
val conditions = listOf(
    mapOf(
        "conditionText" to "Sacarlo a pasear al menos 3 veces a la semana",
        "checked" to false
    ),
    mapOf(
        "conditionText" to "Darle de comer 3 veces al día",
        "checked" to false
    ),
    mapOf(
        "conditionText" to "Darle agua fresca y limpia",
        "checked" to false
    ),
    mapOf(
        "conditionText" to "Jugar con él al menos 1 hora al día",
        "checked" to false
    ),
    mapOf(
        "conditionText" to "Llevarlo al veterinario al menos 1 vez al año",
        "checked" to false
    )
)
