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
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.adoptapp.R
import co.edu.unicauca.adoptapp.ui.adoptions.AdoptPetScreen
import co.edu.unicauca.adoptapp.ui.adoptions.AdoptionEvent
import co.edu.unicauca.adoptapp.ui.adoptions.AdoptionState
import co.edu.unicauca.adoptapp.ui.adoptions.AdoptionsScreen
import co.edu.unicauca.adoptapp.ui.publications.PublicationForm
import co.edu.unicauca.adoptapp.ui.index.IndexScreen
import co.edu.unicauca.adoptapp.ui.index.SearchBar
import co.edu.unicauca.adoptapp.ui.initial.InitialActivity
import co.edu.unicauca.adoptapp.ui.initial.InitialViewModel
import co.edu.unicauca.adoptapp.ui.login.LoginScreen
import co.edu.unicauca.adoptapp.ui.posts.DetailPostScreen
import co.edu.unicauca.adoptapp.ui.posts.MyPostsScreen
import co.edu.unicauca.adoptapp.ui.publications.PostEvent
import co.edu.unicauca.adoptapp.ui.publications.PostState
import co.edu.unicauca.adoptapp.ui.register_user.RegisterScreen
import co.edu.unicauca.adoptapp.ui.register_user.UserRegisterEvent
import co.edu.unicauca.adoptapp.ui.register_user.UserState
import co.edu.unicauca.adoptapp.ui.theme.outlineDark
import co.edu.unicauca.adoptapp.ui.theme.primaryDark
import co.edu.unicauca.adoptapp.ui.theme.primaryLight

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LearnNavDrawer(
    state: UserState,
    onEvent: (UserRegisterEvent) -> Unit,
    statePost: PostState,
    onEventPost: (PostEvent) -> Unit,
    stateAdoption: AdoptionState,
    onEventAdoption: (AdoptionEvent) -> Unit
) {
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
                    if (route == NavigationScreens.Login.screen) {
                        scope.launch {
                            onEvent(UserRegisterEvent.Logout)
                        }
                    }
                    //snackbarHostState.currentSnackbarData?.dismiss() //Cierra ventanas emergentes
                },
                onBackPress = {
                    if (drawerState.isOpen) {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                },
                userId = navigationController.currentBackStackEntry?.arguments?.getString("userId"),
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
                    RegisterScreen(state = state, onEvent = onEvent, navigationController = navigationController)
                }

                composable(NavigationScreens.Login.screen) {
                    LoginScreen(state = state, onEvent = onEvent, navigationController = navigationController)
                }

                composable(NavigationScreens.Home.screen) {
                    IndexScreen(
                        navigationController,
                        snackbarHostState,
                        scope,
                        drawerState,
                        statePost,
                        onEventPost
                    )
                }
                composable(NavigationScreens.MyAdoptions(1).screen) {
                    AdoptionsScreen()
                }

                composable(NavigationScreens.AdoptPet.screen) {
                        backStackEntry ->
                    AdoptPetScreen(
                        navigationController = navigationController,
                        state = stateAdoption,
                        onEvent = onEventAdoption,
                        postId = backStackEntry.arguments?.getString("postId"),
                        userId = backStackEntry.arguments?.getString("userId")
                    )
                }
                composable(NavigationScreens.MyPosts.screen) {
                    backStackEntry ->
                    MyPostsScreen(userId = backStackEntry.arguments?.getString("userId"),navigationController = navigationController)

                }
                composable(NavigationScreens.DetailPost.screen) { backStackEntry ->
                    DetailPostScreen(
                        navigationController = navigationController,
                        postId = backStackEntry.arguments?.getString("postId"),
                        userId = backStackEntry.arguments?.getString("userId"),
                        statePost,
                        onEventPost
                    )
                }
                composable(NavigationScreens.Profile(1).screen) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(R.string.item_profile))
                    }
                }
                composable(NavigationScreens.CreatePost.screen) {
                    navBackStackEntry ->
                    PublicationForm(
                        userId = navBackStackEntry.arguments?.getString("userId"),
                        state = statePost,
                        onEvent = onEventPost,
                        navigationController = navigationController
                    )
                }
                composable(NavigationScreens.Favorites(1).screen) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(R.string.item_favorites))
                    }
                }
                composable(NavigationScreens.Categories(1).screen) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(R.string.item_categories))
                    }
                }
                composable(NavigationScreens.MoreServices(1).screen) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(R.string.item_more_services))
                    }
                }
                composable(NavigationScreens.AboutUs(1).screen) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(R.string.item_about_us))
                    }
                }
                composable(NavigationScreens.Settings.screen) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(R.string.item_settings))
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
    userId: String?
) {
    var showDialog by remember { mutableStateOf(false) }
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
            route = NavigationScreens.MyPosts.passId(id = userId ?: "0")
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
        MenuItem(
            title = stringResource(R.string.item_logout),
            icon = Icons.AutoMirrored.Filled.ExitToApp,
            route = NavigationScreens.Login.screen
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
                            )
                        },
                        onClick = {
                            if (menuList.title == "Cerrar sesión") {
                                showDialog = true
                               // onItemSelected.invoke(menuList.route)
                            } else {
                                onItemSelected.invoke(menuList.route)
                            }
                        },

                    )
                    if (menuList == menu[4] || menuList == menu[6]){
                        Divider()
                    }
                    if (showDialog) {
                        SuccessDialog(
                            onDismissRequest = { showDialog = false },
                            onConfirm = {
                                showDialog = false
                                onItemSelected.invoke(menuList.route)
                            }
                        )
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
fun SuccessDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "¿Está seguro de cerrar la sesión?",
                color = Color.Black
            )
                },
        confirmButton = {
            Button(onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = primaryLight,
                    disabledBackgroundColor = primaryDark,
                    contentColor = Color.White,
                    disabledContentColor = Color.White
                )) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = outlineDark,
                    disabledBackgroundColor = outlineDark,
                    contentColor = Color.White,
                    disabledContentColor = Color.White
                )) {
                Text("Cancelar")
            }
        }
    )
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