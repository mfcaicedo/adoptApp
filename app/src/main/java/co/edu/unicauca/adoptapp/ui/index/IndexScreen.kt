
package co.edu.unicauca.adoptapp.ui.index

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navArgument
import co.edu.unicauca.adoptapp.R
import co.edu.unicauca.adoptapp.ui.navigation.MyTopBar
import co.edu.unicauca.adoptapp.ui.navigation.NavigationScreens
import co.edu.unicauca.adoptapp.ui.theme.AdoptAppTheme
import co.edu.unicauca.adoptapp.ui.theme.primaryLight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.*

@Composable
fun SearchBar(
    modifier: Modifier = Modifier

) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp)
            .height(50.dp)
            .padding(end = 2.dp)
    )
}

@Composable
fun CardElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.clickable { onClick() },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
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
                Column (
                    horizontalAlignment = Alignment.End,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width(455.dp)
            ) {
                Image(
                    painter = painterResource(drawable),
                    contentDescription = null,
                    contentScale = ContentScale.None,
                    modifier = Modifier.size(200.dp)
                )

                Column(
                    modifier = Modifier.padding(all = 5.dp),
                ) {

                    Text(
                        text = stringResource(text),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Pitbull",
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.lorem_ipsum),
                        modifier = Modifier.padding()
                    )
                }
            }
        }
    }
}

@Composable
fun ButtonCategories() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /*TODO*/ },
        ) {
            Text(text = "Todos")
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                primaryLight
            ),
        ) {
            Text(text = "Perros")
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                primaryLight
            ),
        ) {
            Text(text = "Gatos")
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                primaryLight
            ),
        ) {
            Text(text = "Otros")
        }
    }
}


val imageList = listOf(
    R.drawable.promo1,
    R.drawable.promo2,
    R.drawable.promo3,
    R.drawable.promo4,
    R.drawable.promo5,
)

@Composable
fun CarouselScreen() {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(3000) // Espera 3 segundos antes de desplazarse a la siguiente imagen
            val nextItem = (scrollState.firstVisibleItemIndex + 1) % (imageList.size - 1)
            coroutineScope.launch {
                scrollState.animateScrollToItem(nextItem)
            }
        }
    }
    LazyRow(
        state = scrollState,
        modifier = Modifier.padding(bottom = 5.dp),
        ) {
        items(imageList) { image ->
            Image(
                painter = painterResource(id = image),
                contentDescription = "Carousel Image",
                modifier = Modifier
                    .width(200.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .padding(4.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun MenuButton() {
    IconButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(2.dp),
        ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.scrim,
            modifier = Modifier.size(34.dp)
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun IndexScreen(
    navigationController: NavController,
    snackbarHostState: SnackbarHostState,
    context: Context,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
println("------usuario id: "+ NavigationScreens.Home.retrieveUserId())
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            MyTopBar(
                isDarkTheme = false,
                onMenuClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                onSwitchToggle = { }
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            IndexContent(navigationController)
        }
    }
}

@Composable
fun IndexContent(navigationController: NavController){
    Column(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 0.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ButtonCategories()
                CarouselScreen()
                for (i in 1..5) {
                    CardElement(
                        text = R.string.image_example_1,
                        drawable = R.drawable.image_example_1,
                        onClick = {
                            navigationController.navigate(NavigationScreens.DetailPost.passId(i.toString()))
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

/**
 * Preview
 */
@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    AdoptAppTheme {
        SearchBar()
    }
}
@Preview(showBackground = true)
@Composable
fun CardElementPreview() {
    AdoptAppTheme {
        CardElement(
            text = R.string.image_example_1,
            drawable = R.drawable.image_example_1,
            onClick = {}
        )
    }
}