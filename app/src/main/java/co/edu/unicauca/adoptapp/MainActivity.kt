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
        /**
         * @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
         * @Composable
         *
         * Una función componible que crea un cajón de navegación (Navigation Drawer) para la aplicación.
         * Este cajón de navegación contiene varias pantallas y permite al usuario navegar entre ellas.
         */
fun LearnNavDrawer() {
    // Controlador de navegación para manejar la navegación entre las pantallas.
    val navigationController = rememberNavController()

    // Estado del cajón de navegación. Inicialmente está cerrado.
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    // Estado del Snackbar, que se utiliza para mostrar mensajes breves.
    val snackbarHostState = remember { SnackbarHostState() }

    // Alcance de la corrutina para lanzar corrutinas.
    val scope = rememberCoroutineScope()

    // Contexto de la aplicación.
    val context = LocalContext.current.applicationContext

    // Cajón de navegación modal. Contiene el contenido del cajón y el contenido principal de la aplicación.
    ModalNavigationDrawer(
        // Proporciona el estado del cajón y si los gestos están habilitados o no.
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen || drawerState.isClosed,
        drawerContent = {
            // Contenido del cajón de navegación.
            MyDrawerContent(
                // Acción a realizar cuando se selecciona un elemento.
                onItemSelected = {route ->
                    // Cierra el cajón y navega a la ruta seleccionada.
                    scope.launch {
                        drawerState.close()
                    }
                    navigationController.navigate(route)
                },
                // Acción a realizar cuando se presiona el botón de retroceso.
                onBackPress = {
                    // Si el cajón está abierto, ciérralo.
                    if (drawerState.isOpen) {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                },
            )
        },
    ) {
        // Andamio de la aplicación. Contiene la barra superior y el host Snackbar.
        Scaffold(
            // Host Snackbar para mostrar mensajes.
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            // Barra superior de la aplicación.
            topBar = {
                MyTopBar(
                    isDarkTheme = false,
                    // Acción a realizar cuando se hace clic en el menú.
                    onMenuClick = {
                        // Abre el cajón de navegación.
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    // Acción a realizar cuando se cambia el interruptor.
                    onSwitchToggle = { }
                )
            },
        ) {
            // Host de navegación para las diferentes pantallas de la aplicación.
            NavHost(navController = navigationController,
                startDestination = NavigationScreens.Home.screen) {
                // Aquí se definen las diferentes pantallas de la aplicación.
                // Cada pantalla tiene una ruta asociada que se utiliza para la navegación.
            }
        }

        // Efecto lanzado para cerrar el Snackbar después de un retraso.
        LaunchedEffect(snackbarHostState.currentSnackbarData?.visuals?.duration) {
            delay(2000)
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }
}







