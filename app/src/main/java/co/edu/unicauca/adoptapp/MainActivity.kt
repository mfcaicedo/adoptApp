package co.edu.unicauca.adoptapp

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
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import co.edu.unicauca.adoptapp.ui.index.IndexScreen
import co.edu.unicauca.adoptapp.ui.sidebar.MyDrawerContent
import co.edu.unicauca.adoptapp.ui.sidebar.MyTopBar
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
                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val snackbarHostState = remember { SnackbarHostState() }
                    val scope = rememberCoroutineScope()
                    val context = LocalContext.current
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        gesturesEnabled = drawerState.isOpen || drawerState.isClosed,
                        drawerContent = {
                            MyDrawerContent(
                                onItemSelected = { title ->
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    snackbarHostState.currentSnackbarData?.dismiss()
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
                        ) { paddingValues ->
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(paddingValues),
                                contentAlignment = Alignment.Center,

                                ) {
                                IndexScreen()
                            }
                        }

                        LaunchedEffect(snackbarHostState.currentSnackbarData?.visuals?.duration) {
                            delay(2000)
                            snackbarHostState.currentSnackbarData?.dismiss()
                        }
                    }
                }
            }
        }
    }
}






