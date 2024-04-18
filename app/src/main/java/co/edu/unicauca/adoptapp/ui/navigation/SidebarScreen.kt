package co.edu.unicauca.adoptapp.ui.navigation

import android.annotation.SuppressLint
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.adoptapp.R
import co.edu.unicauca.adoptapp.ui.adoptions.AdoptPetScreen
import co.edu.unicauca.adoptapp.ui.adoptions.AdoptionsScreen
import co.edu.unicauca.adoptapp.ui.form.PubliFormViewModel
import co.edu.unicauca.adoptapp.ui.form.PublicationForm
import co.edu.unicauca.adoptapp.ui.index.IndexScreen
import co.edu.unicauca.adoptapp.ui.index.SearchBar
import co.edu.unicauca.adoptapp.ui.initial.InitialActivity
import co.edu.unicauca.adoptapp.ui.initial.InitialViewModel
import co.edu.unicauca.adoptapp.ui.login.LoginScreen
import co.edu.unicauca.adoptapp.ui.login.LoginViewModel
import co.edu.unicauca.adoptapp.ui.posts.DetailPostScreen
import co.edu.unicauca.adoptapp.ui.posts.MyPostsScreen
import co.edu.unicauca.adoptapp.ui.register.RegisterActivity
import co.edu.unicauca.adoptapp.ui.register.RegisterViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LearnNavDrawer() {
    val navigationController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current.applicationContext
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen || drawerState.isClosed,
        drawerContent = {
            MyDrawerContent(
                onItemSelected = {route ->
                    scope.launch {
                        drawerState.close()
                    }
                    navigationController.navigate(route)
                    //snackbarHostState.currentSnackbarData?.dismiss() //Cierra ventanas emergentes
                },
                onBackPress = {
                    if (drawerState.isOpen) {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                },
            )
        },
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
        ) {
            NavHost(navController = navigationController,

                startDestination = NavigationScreens.Initial.screen) {

                composable(NavigationScreens.Initial.screen) {
                    InitialActivity(InitialViewModel(),navigationController)
                }

                composable(NavigationScreens.Register.screen) {
                    RegisterActivity(RegisterViewModel())
                }

                composable(NavigationScreens.Publications.screen) {
                    PublicationForm(PubliFormViewModel())
                }

                composable(NavigationScreens.Login.screen) {
                    LoginScreen(LoginViewModel(),navigationController)
                }

                composable(NavigationScreens.Home.screen) {
                    IndexScreen(navigationController, snackbarHostState, context, scope, drawerState)
                }
                composable(NavigationScreens.MyAdoptions(1).screen) {
                    AdoptionsScreen()
                }

                composable(NavigationScreens.AdoptPet(userId = 1, postId = 1).screen) {
                        backStackEntry ->
                    AdoptPetScreen(navigationController = navigationController, backStackEntry.arguments?.getString("userId"))
                }
                composable(NavigationScreens.MyPosts(1).screen) {
                    MyPostsScreen(1)

                }
                composable(NavigationScreens.DetailPost.screen) { backStackEntry ->
                    DetailPostScreen(navigationController = navigationController, postId = backStackEntry.arguments?.getString("postId"))
                }
                composable(NavigationScreens.Profile(1).screen) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Mi perfil")
                    }
                }
                composable(NavigationScreens.Favorites(1).screen) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Favoritos")
                    }
                }
                composable(NavigationScreens.Categories(1).screen) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Categorías")
                    }
                }
                composable(NavigationScreens.MoreServices(1).screen) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Mas servicios")
                    }
                }
                composable(NavigationScreens.AboutUs(1).screen) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Sobre nosotros")
                    }
                }
                composable(NavigationScreens.Settings.screen) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Configuración")
                    }
                }
            }
        }

        LaunchedEffect(snackbarHostState.currentSnackbarData?.visuals?.duration) {
            delay(2000)
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    isDarkTheme: Boolean,
    onMenuClick: () -> Unit,
    onSwitchToggle: (Boolean) -> Unit

) {
    val colors = MaterialTheme.colorScheme
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            IconButton(onClick = {
                onMenuClick()
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.description_menu_icon),
                    tint = colors.scrim,
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SearchBar(
                    modifier = Modifier.padding(5.dp)
                )
            }
        },
    )
}

data class MenuItem(val title: String, val icon: ImageVector, val route: String)

@Composable
fun MyDrawerContent(
    modifier: Modifier = Modifier,
    onItemSelected: (route: String) -> Unit,
    onBackPress: () -> Unit,
) {
    val menu = listOf(
        MenuItem(
            title = stringResource(R.string.item_home),
            icon = Icons.Default.Home,
            route = NavigationScreens.Home.screen
        ),
        MenuItem(
            title = stringResource(R.string.item_my_adoptions),
            icon = Icons.Default.Favorite,
            route = NavigationScreens.MyAdoptions(userId = 1).screen
        ),
        MenuItem(
            title = stringResource(R.string.item_my_post),
            icon = Icons.Default.Star,
            route = NavigationScreens.MyPosts(userId = 1).screen
        ),
        MenuItem(
            title = stringResource(R.string.item_profile),
            icon = Icons.Default.AccountBox,
            route = NavigationScreens.Profile(userId = 1).screen
        ),
        MenuItem(
            title = stringResource(R.string.item_favorites),
            icon = Icons.Default.FavoriteBorder,
            route = NavigationScreens.Favorites(userId = 1).screen
        ),
        MenuItem(
            title = stringResource(R.string.item_categories),
            icon = Icons.Default.List,
            route = NavigationScreens.Categories(userId = 1).screen
        ),
        MenuItem(
            title = stringResource(R.string.item_more_services),
            icon = Icons.Default.AddCircle,
            route = NavigationScreens.MoreServices(userId = 1).screen
        ),
        MenuItem(
            title = stringResource(R.string.item_about_us),
            icon = Icons.Default.Info,
            route = NavigationScreens.AboutUs(userId = 1).screen
        ),
        MenuItem(
            title = stringResource(R.string.item_settings),
            icon = Icons.Default.Settings,
            route = NavigationScreens.Settings.screen
        ),
    )
    ModalDrawerSheet(modifier) {
        Column(modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .height(190.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary),
            ){
                Image(
                    painter = painterResource(id = R.drawable.logo_adoptapp_final),
                    contentDescription = "Logo",
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            LazyColumn {
                items(menu) { menuList ->
                    NavigationDrawerItem(
                        shape = MaterialTheme.shapes.small,
                        label = {
                            Text(
                                text = menuList.title,
                                style = MaterialTheme.typography.titleMedium,
                            )
                        },
                        //selected = menuList == menu[0],
                        selected = false,
                        icon = {
                            Icon(
                                imageVector = menuList.icon,
                                contentDescription = menuList.title,
                                tint = MaterialTheme.colorScheme.scrim,
                            )
                        },
                        onClick = {
                            onItemSelected.invoke(menuList.route)
                        },
                    )
                    if (menuList == menu[4] || menuList == menu[6]){
                        Divider()
                    }
                }
            }
        }
    }
    BackPressHandler {
        onBackPress()
    }
}

@Composable
fun BackPressHandler(enabled: Boolean = true, onBackPressed: () -> Unit) {
    val currentOnBackPressed by rememberUpdatedState(onBackPressed)
    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }
    SideEffect {
        backCallback.isEnabled = enabled
    }

    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, backDispatcher) {
        backDispatcher.addCallback(lifecycleOwner, backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}