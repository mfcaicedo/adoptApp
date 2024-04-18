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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.unicauca.adoptapp.R
import co.edu.unicauca.adoptapp.ui.index.IndexContent
import co.edu.unicauca.adoptapp.ui.posts.DetailPostContent
import co.edu.unicauca.adoptapp.ui.posts.DetailPostTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdoptPetScreen(navigationController: NavController, postId: String?) {
    Scaffold(
        topBar = {
            AdoptPetTopBar(navigationController = navigationController)
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            AdoptPetContent(navigationController = navigationController)
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
                    imageVector = Icons.Default.ArrowBack,
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
fun AdoptPetContent(navigationController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.adoption_conditions_text),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row {
                Text(
                    text = stringResource(id = R.string.adoption_conditions_text),
                )
                Text(
                    text = stringResource(id = R.string.adoption_conditions_text),
                )
                Text(
                    text = stringResource(id = R.string.adoption_conditions_text),
                )
                Text(
                    text = stringResource(id = R.string.adoption_conditions_text),
                )
                Text(
                        text = stringResource(id = R.string.adoption_conditions_text),
                )

            }
        }
    }
}