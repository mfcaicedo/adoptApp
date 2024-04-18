package co.edu.unicauca.adoptapp.ui.posts

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.unicauca.adoptapp.R
import co.edu.unicauca.adoptapp.ui.adoptions.AdoptPetContent
import co.edu.unicauca.adoptapp.ui.index.SearchBar
import co.edu.unicauca.adoptapp.ui.navigation.NavigationScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailPostScreen(
    navigationController: NavController,
    postId: String?,
) {
    Scaffold(
        topBar = {
            DetailPostTopBar(navigationController = navigationController)
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            DetailPostContent(navigationController = navigationController, postId = postId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPostTopBar(navigationController: NavController) {
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
                    Text(text = stringResource(R.string.detail_post_text))
                }
                Column(
                    modifier = Modifier
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = stringResource(
                            id = R.string.description_favorite_icon
                        ))
                    }
                }
            }
        },
    )
}

@Composable
fun DetailPostContent(navigationController: NavController, postId: String?) {
    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.TopCenter,
        ){
        Column (
            modifier = Modifier
                .padding(start = 9.dp, end = 9.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                Image(
                    painter = painterResource(id = R.drawable.image_example_1),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column (
                    modifier = Modifier,
                ) {
                    Row (
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column(
                            modifier = Modifier.padding(end = 2.dp)
                        ) {
                            Text(text = "Nombre:", style = MaterialTheme.typography.titleMedium)
                        }
                        Column {
                            Text(text = "Tor")
                        }
                    }
                }
                Column (
                    modifier = Modifier,
                ) {
                    Row (
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column {
                            Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                        }
                        Column {
                            Text(text = "Popayán")
                        }
                    }
                }
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column (
                    modifier = Modifier,
                ) {
                    Row (
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column(
                            modifier = Modifier.padding(end = 2.dp)
                        ) {
                            Text(text = "Color:", style = MaterialTheme.typography.titleMedium)
                        }
                        Column {
                            Text(text = "Café oscuro")
                        }
                    }
                }
                Column (
                    modifier = Modifier,
                ) {
                    Row (
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column {
                            Text(text = "Raza:", style = MaterialTheme.typography.titleMedium)
                        }
                        Column {
                            Text(text = "Pitbull")
                        }
                    }
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
            ) {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Descripción", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = stringResource(R.string.lorem_ipsum))
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        navigationController.navigate(NavigationScreens.AdoptPet(userId = 1, postId = 1).screen)
                    },
                ) {
                    Text(text = "Adoptar")
                }
            }
            AdoptionsRequest()
            SimilarPost()
        }
    }
}

@Composable
fun AdoptionsRequest() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Solicitudes de adopción", style = MaterialTheme.typography.titleLarge)
        }
        Row {
            Column {
                for (i in 0..1) {
                    ListAdoptionsRequest()
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }

    }
}

@Composable
fun ListAdoptionsRequest() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(42.dp)
        )
        Column(
            modifier = Modifier.padding(all = 5.dp),
        ) {

            Text(
                text = "Milthon Caicedo",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(

            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null
                )
                Text(
                    text = "Popayán Cauca",
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Start
            ) {
                Column {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null
                    )
                }
                Column {
                    Text(
                        text = "Hace 2 días",
                    )
                }
            }
        }
    }
}

@Composable
fun SimilarPost() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        ) {
            Text(text = "Publicaciones similares", style = MaterialTheme.typography.titleLarge)
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Start
        ) {
            for (i in 0..2) {
                SimilarPostList()
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

@Composable
fun SimilarPostList() {
    Column(
        modifier = Modifier
            .width(150.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_example_1),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(100.dp)
                .width(150.dp)
        )
        Column(
            modifier = Modifier.padding(all = 5.dp),
        ) {
            Text(
                text = "Manchas",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
            ) {
                Text(text = "Raza:", Modifier.padding(end = 2.dp),
                    style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "Pitbull",
                )
            }
        }
    }
}

