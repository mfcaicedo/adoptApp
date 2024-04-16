package co.edu.unicauca.adoptapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import co.edu.unicauca.adoptapp.ui.adoptions.AdoptPetScreen
import co.edu.unicauca.adoptapp.ui.adoptions.AdoptionsScreen
import co.edu.unicauca.adoptapp.ui.index.IndexScreen
import co.edu.unicauca.adoptapp.ui.navigation.LearnNavDrawer
import co.edu.unicauca.adoptapp.ui.navigation.MyDrawerContent
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







