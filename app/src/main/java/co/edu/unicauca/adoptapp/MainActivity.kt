package co.edu.unicauca.adoptapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.adoptapp.ui.adoptions.AdoptionsScreen
import co.edu.unicauca.adoptapp.ui.index.IndexScreen
import co.edu.unicauca.adoptapp.ui.navigation.MyDrawerContent
import co.edu.unicauca.adoptapp.ui.navigation.MyTopBar
import co.edu.unicauca.adoptapp.ui.navigation.NavigationScreens
import co.edu.unicauca.adoptapp.ui.posts.DetailPostScreen
import co.edu.unicauca.adoptapp.ui.posts.MyPostsScreen
import co.edu.unicauca.adoptapp.ui.theme.AdoptAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdoptAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    LearnNavDrawer()
                }
            }
        }
    }
}

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
            NavHost(navController = navigationController,
                    startDestination = NavigationScreens.Home.screen) {
                composable(NavigationScreens.Home.screen) {
                    IndexScreen(navigationController)
                }
                composable(NavigationScreens.MyAdoptions(1).screen) {
                    AdoptionsScreen()
                }
                composable(NavigationScreens.MyPosts(1).screen) {
                    MyPostsScreen(1)
                }
                composable(NavigationScreens.DetailPost(1).screen) {
                    DetailPostScreen(postId = 1)
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






